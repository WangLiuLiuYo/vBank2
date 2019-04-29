package wang66.services;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang66.POJO.Employee;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.mappers.EmployeeMapper;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeDAO {
    @Autowired
    private SqlSessionTemplate template;
    @Autowired
    private RolesDAO rolesDAO;
    private static final String defaultEmpPassword="000000";
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addEmployee(Employee employee){
        EmployeeMapper employeeMapper=template.getMapper(EmployeeMapper.class);
        employeeMapper.addEmployee(employee);
        Role role=new Role();
        role.setUsername(employee.getWorkId());
        role.setPassword(defaultEmpPassword);
        role.setRole(RoleList.employee);
        rolesDAO.addUserRoleInMD5(role);
    }
    public List<Employee> findAllEmp(){
        EmployeeMapper employeeMapper=template.getMapper(EmployeeMapper.class);
        return employeeMapper.findAllEmployees();
    }
    public void upSalaryTo(String workId, BigDecimal salary){
        EmployeeMapper mapper=template.getMapper(EmployeeMapper.class);
        mapper.resetSalary(salary,workId);
    }
}
