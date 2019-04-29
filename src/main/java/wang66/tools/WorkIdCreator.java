package wang66.tools;

import java.util.Random;

public class WorkIdCreator {
    private static Random random=new Random();
    private static int CURRENT_NUMBER=16600000;
    public synchronized static String makeWorkId(){
        CURRENT_NUMBER+=random.nextInt(12)+6;
        int temp=CURRENT_NUMBER++;
        return ""+temp;
    }
}
