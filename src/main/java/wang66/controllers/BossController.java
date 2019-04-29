package wang66.controllers;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wang66.POJO.Employee;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.services.EmployeeDAO;
import wang66.services.RolesDAO;
import wang66.tools.WorkIdCreator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("boss")
public class BossController {
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    RolesDAO rolesDAO;

    private boolean afterInit=false;
    @RequestMapping("init")
    public String initBossRole(){
        if(!afterInit){
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
                    rolesDAO.insertNotRepeatable(role);
                }
            }
            catch (IOException e){
                e.printStackTrace();

            }
            afterInit=true;
        }
        return "redirect:/boss/service";
    }
    @RequestMapping(value = "service",method = RequestMethod.GET)
    public String service(){
        return "bossService";
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addEmp(){
        return "addEmployeePage";
    }
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String empRegister(Employee employee, Model model){
        employee.setWorkId(WorkIdCreator.makeWorkId());
        employeeDAO.addEmployee(employee);
        model.addAttribute("name",employee.getName());
        model.addAttribute("workId",employee.getWorkId());
        return "addEmployeeResult";
    }

    @RequestMapping("manage")
    public String manage(Model model){
        List<Employee> employees=employeeDAO.findAllEmp();
        model.addAttribute("employees",employees);
        return "employeesManagePage";
    }

    @RequestMapping("upSalary")
    public String upSalary(@RequestParam("workId")String workId, @RequestParam("salary")BigDecimal currentSalary){
        employeeDAO.upSalaryTo(workId,currentSalary.multiply(new BigDecimal("1.2")));
        return "forward:manage";
    }
}
