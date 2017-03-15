/**
 * Created by BigBlackFace on 17/3/14.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;



public class Cal {

    //private variable

    private Map<String, Integer> map;
    private String exp;

    //constructor
    public Cal (String exp){
        this.exp = "";
        for (int i = 0; i < exp.length(); ++i){
            if (exp.charAt(i) != ' '){
                this.exp = this.exp + exp.charAt(i);
                System.out.println(this.exp);
            }
        }
        //log here

        //hash map for different scope look up
        map = new HashMap<String, Integer>();
    }

    //help function 0:
    //check if the () if paired


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
    private int middleIndex(String str){
        int left = 0;
        for (int i = 0; i < str.length(); ++i){
            if (str.charAt(i) == '('){
                left += 1;
            }else if (str.charAt(i) == ')'){
                left -= 1;
            }
            if (str.charAt(i) == ',' && left == 0){
                return i;
            }
        }
        System.out.println(str.length());
        return str.length();
    }


    //initial call
    public int eval(){
        System.out.println("1");
        return eval(exp);
    }

    //evaluate the value of variables
    public int eval(String str){

        System.out.println(str);

        try {
            int n = Integer.valueOf(str);
            return n;
        }catch (Exception e){
            if (map.get(str) != null){
                return map.get(str);
            }
        }

        if (str.startsWith("add(")){
            str = str.substring(4, str.length() - 1);
            int midIndex = middleIndex(str);
            System.out.println(midIndex);
            int left = eval(str.substring(0, midIndex));
            int right = eval(str.substring(midIndex + 1, str.length()));
            return left + right;
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

        //create new object
        Cal calculator = new Cal(str);

        int x = calculator.eval();
        System.out.println(x);



    }
}
