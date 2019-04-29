package wang66.services;

//import jdk.vm.ci.meta.ExceptionHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang66.Exceptions.BalanceNotEnoughException;
import wang66.Exceptions.CardFrozenException;
import wang66.Exceptions.NameCardNotMatchException;
import wang66.POJO.*;
import wang66.RoleList;
import wang66.components.MailSender;
import wang66.mappers.*;
import wang66.tools.MD5Helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserDAO {
    private static final String defaultPassword="123456";
    @Autowired
    SqlSessionTemplate template;
    @Autowired
    RolesDAO rolesDAO;
    @Autowired
    MailSender mailSender;
     private static final boolean openMailService=false;
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void register(User user, CardBalance cardBalance){
        UserMessageMapper userMessageMapper=template.getMapper(UserMessageMapper.class);
        //UserRolesMapper userRolesMapper=template.getMapper(UserRolesMapper.class);
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        userMessageMapper.registerUser(user);
        //权限控制表写入
        Role role=new Role();
        role.setRole(RoleList.user);
        role.setPassword(defaultPassword);
        role.setUsername(user.getEmail());//邮箱做权限控制登录名
        rolesDAO.addUserRoleInMD5(role);

        cardBalanceMapper.insertBalance(cardBalance);
        userMessageMapper.createRecordTable(user);
    }
    public boolean isCardIdExist(String cardId){
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        CardBalance cardBalance=cardBalanceMapper.queryCardByCardId(cardId);
        return cardBalance!=null;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public BigDecimal recharge(Record record){
       CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        RecordMapper recordMapper=template.getMapper(RecordMapper.class);
       CardBalance currentCardBalance=cardBalanceMapper.queryCardByCardId(record.getSelfCardId());
       currentCardBalance.setBalance(record.getMoney().add(currentCardBalance.getBalance()));
       cardBalanceMapper.resetBalance(currentCardBalance);
       recordMapper.insertRecord(record);
       return currentCardBalance.getBalance();
    }
    public boolean isUserExist(String uid){
        UserMessageMapper userMessageMapper=template.getMapper(UserMessageMapper.class);
        User user=userMessageMapper.findUserByUid(uid);
        return user!=null;
    }
    public void resetSomeMessage(UserMessageForm form){
        UserMessageMapper userMessageMapper=template.getMapper(UserMessageMapper.class);
        userMessageMapper.resetSomeMessage("email",form.getEmail(),form.getUid());
        userMessageMapper.resetSomeMessage("username",form.getUsername(),form.getUid());
    }
    public boolean isFrozenCard(String cardId){
        FrozenCardMapper frozenCardMapper=template.getMapper(FrozenCardMapper.class);

        String tempCardId=frozenCardMapper.query(cardId);
        return tempCardId!=null&&!tempCardId.equals("");
    }
    public List<Card> getCards(String prefix){
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        return cardBalanceMapper.getCards(prefix+"%");
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int destroyUserByUid(String uid){
        UserMessageMapper userMessageMapper=template.getMapper(UserMessageMapper.class);
        User user=userMessageMapper.findUserByUid(uid);
        if(user==null){
            throw new UsernameNotFoundException("Not found userID:"+uid);
        }
        int row=userMessageMapper.deleteUserByUid(user.getUid());
        if(row==0)
            return row;
        //此处多表删除，实际上可以利用级联删除
        //最后记得修改
        userMessageMapper.dropRecordTable(user);
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        cardBalanceMapper.deleteCardBalanceByCardId(user.getCardId());
        FrozenCardMapper frozenCardMapper=template.getMapper(FrozenCardMapper.class);
        frozenCardMapper.delete(user.getCardId());
        rolesDAO.destroyRoleByName(user.getEmail());
        return row;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isCardFrozen(String cardId){
        FrozenCardMapper frozenCardMapper=template.getMapper(FrozenCardMapper.class);
        String card=frozenCardMapper.query(cardId);
        return card==null||card.equals("");
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void transferAccount(String myCardId,String cardId,String username,BigDecimal money,String comment) throws NameCardNotMatchException,BalanceNotEnoughException,CardFrozenException {
        if(isFrozenCard(myCardId)){
            throw new CardFrozenException("Card (id="+myCardId+") has been frozen.");
        }
        UserMessageMapper messageMapper=template.getMapper(UserMessageMapper.class);
        User user=messageMapper.findUserByCardIdAndName(username,cardId);//user指对方
        if(user==null){
            throw new NameCardNotMatchException("Can't find match user : cardId-"+cardId+" username-"+username);
        }
        CardBalanceMapper cardBalanceMapper=template.getMapper(CardBalanceMapper.class);
        CardBalance myBalance=cardBalanceMapper.queryCardByCardId(myCardId);
        if(myBalance.getBalance().compareTo(money)<0){
            throw new BalanceNotEnoughException("Balance not enough :"+myBalance.getBalance().toString());
        }
        CardBalance yourBalance=cardBalanceMapper.queryCardByCardId(cardId);
        myBalance.setBalance(myBalance.getBalance().subtract(money));
        yourBalance.setBalance(yourBalance.getBalance().add(money));
        cardBalanceMapper.resetBalance(myBalance);
        cardBalanceMapper.resetBalance(yourBalance);
        Record myRecord=new Record(),yourRecord=new Record();

        myRecord.setSelfCardId(myCardId);
        myRecord.setCardId(cardId);
        myRecord.setMoney(money.abs().multiply(new BigDecimal(-1)));
        myRecord.setComment("TRANSFORM OUT.");

        yourRecord.setSelfCardId(cardId);
        yourRecord.setCardId(myCardId);
        yourRecord.setMoney(money.abs());
        yourRecord.setComment(comment);

        RecordMapper recordMapper=template.getMapper(RecordMapper.class);
        recordMapper.insertRecord(myRecord);
        recordMapper.insertRecord(yourRecord);
        if(openMailService){
            User me=messageMapper.findUserByCardId(myCardId);
            User your=user;
            SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
            String current_time=df.format(new Date()).toString();
            mailSender.sendMail("Transfer out money "+money+" at :"+current_time,"vbank mail",me.getEmail());
            mailSender.sendMail("Income money "+money+" at :"+current_time,"vbank mail",your.getEmail());

        }
    }

    public String findCardIdByEmail(String email){
        UserMessageMapper userMessageMapper=template.getMapper(UserMessageMapper.class);
        return userMessageMapper.findCardIdByEmail(email);
    }
}
