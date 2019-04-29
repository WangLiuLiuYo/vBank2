package wang66.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import wang66.RoleList;
import wang66.services.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;
@Component("successfulHandle")
public class LoginSuccessfulHandle implements AuthenticationSuccessHandler {
    @Autowired
    UserDAO userDAO;

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles= AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains(RoleList.employee)){
            //HttpSession session=httpServletRequest.getSession();

            httpServletResponse.sendRedirect("/emp/work");
        }
        else if(roles.contains(RoleList.user)){

            httpServletResponse.sendRedirect("/user/service");
        }
        else if(roles.contains(RoleList.boss)){
            httpServletResponse.sendRedirect("/boss/service");
        }
    }
}
