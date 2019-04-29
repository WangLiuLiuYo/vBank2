package wang66.controllers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.mappers.testMapper;
import wang66.services.RolesDAO;
import wang66.services.TestDAO;
import wang66.tools.MD5Helper;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("my")
public class testController {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    RolesDAO rolesDAO;
    @RequestMapping("hi")
    @ResponseBody
    public String hi(HttpServletRequest request){
        String s="TEST OK!";
        Principal principal=request.getUserPrincipal();
        s=s+"   userMessage:"+principal.getName();
        return s;
    }
    @RequestMapping("view")
    public String view(){
        return "welcomeTest";
    }
    @RequestMapping("emp")
    @ResponseBody
    public String emp(){
        return "come to employee page.";
    }



    @RequestMapping("user")
    @ResponseBody
    public String user(){
        return "Come to user page";
    }
    @RequestMapping("testMapper")
    @ResponseBody
    public String mapper(){
        SqlSession session=sqlSessionTemplate.getSqlSessionFactory().openSession();
        testMapper mapper=session.getMapper(testMapper.class);
        return "result :"+mapper.query();
    }
    @RequestMapping("insert")
    @ResponseBody
    public String insert(){
        Role r=new Role();
        String name="Candy";
        String pwd="123456";
        r.setUsername(name);
        r.setPassword(pwd);
        r.setRole(RoleList.user);
        rolesDAO.addUserRoleInMD5(r);
        return r.toString();

    }
    @Autowired
    TestDAO testDAO;
    @RequestMapping("trans")
    @ResponseBody
    public String dao(){
        testDAO.transTest();
        return "Test Dao Ok.";
    }
    @RequestMapping("del")
    @ResponseBody
    public String delete(@RequestParam("key") int key){
        int r=testDAO.delete(key);
        return "the "+r+" rows effected.";
    }
}
