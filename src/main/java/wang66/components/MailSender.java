package wang66.components;

import org.apache.ibatis.io.Resources;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Component("mailSender")
public class MailSender {

    private JavaMailSenderImpl javaMailSender;
    public MailSender(){
        try{
            InputStream inputStream= Resources.getResourceAsStream("mail.properties");
            Properties properties=new Properties();
            properties.load(inputStream);
            javaMailSender=new JavaMailSenderImpl();
            javaMailSender.setHost(properties.getProperty("host"));
            javaMailSender.setUsername(properties.getProperty("username"));
            javaMailSender.setPassword(properties.getProperty("password"));
            javaMailSender.setDefaultEncoding(properties.getProperty("defaultEncoding"));

        }
        catch (IOException e){
           e.printStackTrace();
           javaMailSender=null;
        }
    }
    @Async
    public void sendMail(String text,String subject,String targetAddr){
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();

        try{
            mimeMessage.addRecipients(MimeMessage.RecipientType.CC,javaMailSender.getUsername());
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setFrom(javaMailSender.getUsername());
            mimeMessageHelper.setTo(targetAddr);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text,true);
            mimeMessage.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            javaMailSender.send(mimeMessage);
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Send mail fail!!!! please try again.");
        }
    }

}
