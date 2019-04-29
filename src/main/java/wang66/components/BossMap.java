package wang66.components;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.services.RolesDAO;
import wang66.tools.MD5Helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BossMap {


    public BossMap(){
       try{
           InputStream inputStream= Resources.getResourceAsStream("boss.properties");
           Properties properties=new Properties();
           properties.load(inputStream);
           int bossNumber=Integer.parseInt(properties.getProperty("bossNumber"));
           for(int i=1;i<=bossNumber;i++){
               String bossName=properties.getProperty("bossName"+i);
               String bossPassword=properties.getProperty("bossPwd"+i);
               Role role=new Role();
               role.setUsername(bossName);
               role.setPassword(bossPassword);
               role.setRole(RoleList.boss);
               System.out.println(role.getUsername()+"   "+role.getPassword()+"   "+role.getRole());
               //rolesDAO.insertNotRepeatable(role);
           }
       }
       catch (IOException e){
           e.printStackTrace();

       }
    }
    public void doNoting(){}

}
