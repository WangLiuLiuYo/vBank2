package wang66.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wang66.Exceptions.BalanceNotEnoughException;
import wang66.Exceptions.CardFrozenException;
import wang66.Exceptions.NameCardNotMatchException;
import wang66.POJO.Record;
import wang66.services.RecordDAO;
import wang66.services.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    RecordDAO recordDAO;

    private boolean afterSetCardId=false;
    private void setCardIdForSession(HttpSession session,HttpServletRequest request){
        if(afterSetCardId)
            return;
        Principal principal=request.getUserPrincipal();
        String email=principal.getName();
        String cardId=userDAO.findCardIdByEmail(email);
        session.setAttribute("cardId",cardId);
        afterSetCardId=true;
        System.out.println("set cardId to session is ok.");
    }

    @RequestMapping(value = "service",method = RequestMethod.GET)
    public String gotoServicePage(HttpSession session,HttpServletRequest request){
        setCardIdForSession(session,request);
        return "userService";
    }
    @RequestMapping("queryBalance")
    public String queryBalance(HttpSession session, Model model, HttpServletRequest request){

        String cardId=(String)session.getAttribute("cardId");
       String date=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        BigDecimal balance=recordDAO.queryBalance(cardId);
        model.addAttribute("balance",balance);
        model.addAttribute("current",date);
        return "queryBalanceResult";
    }
    @RequestMapping("queryRecord")
    public String queryRecord(@RequestParam("gap") String gap,HttpSession session,Model model){
        List<Record> records;
        String cardId=(String)session.getAttribute("cardId");
        if(gap.equals("1")){
            model.addAttribute("title","本月");
            records=recordDAO.queryByCondition(cardId,"month(`date`)=month(curdate())");
        }
        else if(gap.equals("12")){
            model.addAttribute("title","本年度");
            records=recordDAO.queryByCondition(cardId,"year(`date`)=month(curdate())");
        }
        else{
            model.addAttribute("title","全部");
            records=recordDAO.queryByCondition(cardId,"1=1");
        }
        model.addAttribute("records",records);
        return "recordList";
    }
    @RequestMapping(value = "transfer",method = RequestMethod.GET)
    public String transfer(@RequestParam("username")String username,@RequestParam("cardId")String cardId,
                            @RequestParam("money")BigDecimal money,@RequestParam("comment")String comment,
                            HttpSession session,Model model){
        String myCardId=(String)session.getAttribute("cardId");
        try{
            userDAO.transferAccount(myCardId,cardId,username,money,comment);
        }
        catch (NameCardNotMatchException nceme){
            nceme.printStackTrace();
            model.addAttribute("errorMessage",nceme.getMessage());
        }
        catch (BalanceNotEnoughException bnee){
            bnee.printStackTrace();
            model.addAttribute("errorMessage",bnee.getMessage());
        }
        catch (CardFrozenException cfe){
            cfe.printStackTrace();
            model.addAttribute("errorMessage",cfe.getMessage());
        }
        model.addAttribute("cardId",myCardId);
        return "transferResultPage";
    }

}
