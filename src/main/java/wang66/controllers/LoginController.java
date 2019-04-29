package wang66.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("check")
public class LoginController {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String gotoLogPage(){
        return "logPage";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String handleLogIn(){
        return "";
    }
    @RequestMapping(value = "fail",method = RequestMethod.GET)
    public String fail(){
        return "logFail";
    }
}
