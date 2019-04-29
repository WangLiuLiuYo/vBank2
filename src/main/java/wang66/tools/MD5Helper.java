package wang66.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
    private static final int mask=0xf0;
    public static String str2MD5(String str){
        MessageDigest md5=null;
        try{
            md5=MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        char[] chars=str.toCharArray();
        byte[] bytes=new byte[chars.length];
        for(int i=0;i<bytes.length;i++){
            bytes[i]=(byte)chars[i];
        }
        byte[] md5bytes=md5.digest(bytes);
        StringBuilder sb=new StringBuilder();
        for(byte b:md5bytes){
            int k=(int)b;
            sb.append(k^mask);
        }
        return sb.toString();
    }
    public static String str2MD5WithSalt(String str,String salt){
        return str2MD5(str+salt);
    }
}
