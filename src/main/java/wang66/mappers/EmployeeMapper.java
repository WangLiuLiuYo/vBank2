package wang66.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import wang66.POJO.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {
    @Insert("insert into employees(workId,name,salary,university) values(#{workId},#{name},#{salary},#{university});")
    int addEmployee(Employee emp);
    @Select("select * from employees")
    List<Employee> findAllEmployees();
    @Update("update employees set salary=#{salary} where workId=#{workId}")
    int resetSalary(@Param("salary")BigDecimal salary,@Param("workId")String workId);
}
