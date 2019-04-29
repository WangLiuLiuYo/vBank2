package wang66.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wang66.POJO.*;
import wang66.mappers.UserMessageMapper;
import wang66.services.CardDAO;
import wang66.services.UserDAO;
import wang66.tools.CardIdCreator;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmployeeController {
    private static final String bankCardId="00000000000000000000";
    private static final String rechargeComment="Recharge from virtual bank.";
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "work",method = RequestMethod.GET)
    public String workTable(){
        return "employeeService";
    }
    @RequestMapping(value = "addUser",method = RequestMethod.GET)
    public String addUser(){
        return "registerPage";
    }
    @RequestMapping(value = "registerUser",method = RequestMethod.POST)
    public String registerUser(UserMessageForm form, Model model){
       User user=new User();
       user.setUsername(form.getUsername());
       user.setUid(form.getUid());
       user.setEmail(form.getEmail());
       String userCardId=CardIdCreator.createId();
       user.setCardId(userCardId);
        CardBalance cardBalance=new CardBalance();
        cardBalance.setCardId(userCardId);
        cardBalance.setBalance(form.getMoney());
        userDAO.register(user,cardBalance);
       model.addAttribute("message",cardBalance);
       return "userRegisterOK";
    }
    @RequestMapping("recharge")
    public String recharge(@RequestParam("cardId")String cardId, @RequestParam("money")BigDecimal money,Model model){
        if(!userDAO.isCardIdExist(cardId)){
            model.addAttribute("cardId",cardId);
            return "cardNotExist";
        }
        Record record=new Record();
        record.setSelfCardId(cardId);
        record.setCardId(bankCardId);
        record.setComment(rechargeComment);
        record.setMoney(money);
        BigDecimal currentBalance=userDAO.recharge(record);
        model.addAttribute("current",currentBalance);
        return "rechargeOK";
    }
    @RequestMapping(value = "resetMessage",method = RequestMethod.POST)
    public String reset(UserMessageForm form,Model model){
        if(form.getUid()==null||!userDAO.isUserExist(form.getUid())){
            return "userNotFound";
        }
        userDAO.resetSomeMessage(form);
        model.addAttribute("message",form);
        return "messageResetOK";
    }
    @RequestMapping("query")
    public String queryPrefix(@RequestParam("cardIdPrefix")String cardIdPre,Model model){
        List<Card> cards=userDAO.getCards(cardIdPre);
        model.addAttribute("cardList",cards);
        return "cardList";
    }
    @Autowired
    CardDAO cardDAO;
    @RequestMapping("frozeCard")
    public String froze(@RequestParam("cardId")String cardId,Model model){
        cardDAO.frozeCard(cardId);
        model.addAttribute("message","冻结");
        return "frozeOrUnfroze";
    }
    @RequestMapping("unfrozeCard")
    public String unfroze(@RequestParam("cardId")String cardId,Model model){
        cardDAO.unfrozeCard(cardId);
        model.addAttribute("message","解除冻结");
        return "frozeOrUnfroze";
    }
    @RequestMapping(value = "destroy",method = RequestMethod.GET)
    public String destroyUser(){
          return "destroyUserPage";
    }
    @RequestMapping(value = "destroy",method = RequestMethod.POST)
    public String destroyUserHandle(@RequestParam("uid")String uid,@RequestParam("uid2")String uid2,Model model){
        int row=userDAO.destroyUserByUid(uid);
        model.addAttribute("deletedRow",row);
        model.addAttribute("uid",uid);
        return "deleteResult";
    }
}
