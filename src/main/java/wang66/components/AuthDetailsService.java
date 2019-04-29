package wang66.components;

import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import wang66.POJO.Role;
import wang66.RoleList;
import wang66.mappers.UserRolesMapper;
import wang66.services.RolesDAO;

import java.util.ArrayList;
import java.util.List;
@Component
public class AuthDetailsService implements UserDetailsService {
    @Autowired
    private RolesDAO rolesDAO;
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Role role=rolesDAO.getUserRoleByName(s);
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        if(role==null){
            throw new UsernameNotFoundException("Not found Username --"+s);
        }


        authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return new User(role.getUsername(),role.getPassword(),
                true,true,true,true,authorities);

    }

}
