package wang66.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordIdCreator {
    private static volatile int rid=1000000;//7
    private static synchronized String getRid(){
        int temp=rid;
        rid=rid+1==10000000?1000000:rid+1;
        return Integer.toString(temp);

    }
    private static String int2String(int k){
        return k<10?"0"+k:""+k;
    }
    public static String getRecordId(){
        Date d=new Date();
        StringBuilder head=new StringBuilder(8);
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        for(String s:df.format(d).split("/")){
            head.append(s);
        }
        return head.toString()+getRid();
    }
}
