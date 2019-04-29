package wang66.tools;

import java.util.Date;
import java.util.Random;

public class CardIdCreator {
    private static volatile long init_id=1000000000L;//10
    private static Random random=new Random();
    public static synchronized String createId(){
        long head=init_id++;
        int year=new Date().getYear();//4
        int month=new Date().getMonth()+10;//2
        int r=random.nextInt(8990)+1000;//4
        return Long.toString(head)+Integer.toString(year)+Integer.toString(month)+Integer.toString(r);
    }
}
