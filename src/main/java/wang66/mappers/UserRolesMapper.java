package wang66.mappers;

import org.apache.ibatis.annotations.*;
import wang66.POJO.Role;

public interface UserRolesMapper {
    @Select("select * from roles where username=#{name}")
    Role findUserByName(@Param("name") String name);
    @Insert("insert into roles(username,password,role)values(#{username},#{password},#{role});")
    int addUserRole(Role role);
    @Delete("delete from roles where username=#{name}")
    int delete(@Param("name")String name);
    @Insert("insert into roles(username,password,role) value(#{username},#{password},#{role})" +
            " on duplicate key update username=#{username};")
    int insertNotRepeatable(Role role);



}
