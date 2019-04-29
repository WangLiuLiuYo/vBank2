package wang66.components;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;
import wang66.tools.MD5Helper;


public class MD5Encoder extends MessageDigestPasswordEncoder {
    //private static MD5Helper md5Helper=new MD5Helper();
    public MD5Encoder(String algorithm){
        super(algorithm);
    }
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {

         String userSalt=salt.toString();
         String tempPass=MD5Helper.str2MD5WithSalt(rawPass,userSalt);
         System.out.println(rawPass+"|"+salt.toString());
         System.out.println(encPass);
         System.out.println(tempPass);
         return encPass.equals(tempPass);

    }
}
