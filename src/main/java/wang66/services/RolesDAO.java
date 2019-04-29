package wang66.services;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.mappers.UserRolesMapper;
import wang66.tools.MD5Helper;

@Service
public class RolesDAO {
    @Autowired
    private SqlSessionTemplate template;
    public UserRolesMapper getMapper(){
        return template.getMapper(UserRolesMapper.class);
    }
    public Role getUserRoleByName(String name){
        UserRolesMapper mapper=getMapper();
        return mapper.findUserByName(name);
    }
    public void addUserRoleInMD5(Role role){
        UserRolesMapper mapper=getMapper();
        role.setPassword(MD5Helper.str2MD5WithSalt(role.getPassword(),role.getUsername()));
        mapper.addUserRole(role);
    }
    public void destroyRoleByName(String name){
        UserRolesMapper mapper=getMapper();
        mapper.delete(name);
    }
    public void insertNotRepeatable(Role role){
        UserRolesMapper mapper=getMapper();
        role.setPassword(MD5Helper.str2MD5WithSalt(role.getPassword(),role.getUsername()));
        mapper.insertNotRepeatable(role);
    }

}
