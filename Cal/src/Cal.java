/**
 * Created by BigBlackFace on 17/3/14.
 */

import java.util.logging.Logger;
import java.util.Map;



public class Cal {

    //private variable

    private Map<String, Integer> map;

    //help function 1:
    //check if the str is a number
    private boolean isInteger(String str){
        try {
            Integer.valueOf(str);
            return true;
        }catch (Exception e){
            if (map.get(str) == null){
                return false;
            }
            return false;
        }
    }

    //help function 2:
    //middleIndex


    public static int eval(String str){

        if (str.startsWith("add(")){
            str = str.substring(4, str.length() - 1);
            System.out.println(str);
        }

        return 0;
    }



    public static void main(String[] args) {

        //check if the input valid
        if (args.length != 1){
            Logger logger = Logger.getLogger("Main");
            logger.info("Invalid Input");
            return;
        }

        String str = args[0];

        int x = eval(str);


    }
}
