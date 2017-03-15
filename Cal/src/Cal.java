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
    private Logger logger;

    //constructor
    public Cal (String exp){
        this.exp = "";
        for (int i = 0; i < exp.length(); ++i){
            if (exp.charAt(i) != ' '){
                this.exp = this.exp + exp.charAt(i);
                //System.out.println(this.exp);
            }
        }


        //log here
        logger = Logger.getLogger("calculator");

        //hash map for different scope look up
        map = new HashMap<String, Integer>();
    }

    //help function 0:
    //check if the () is paired

    //help function:
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
        return str.length();
    }


    //initial call
    public int eval(){
        //System.out.println("1");
        //System.out.println(exp);
        //System.out.println("2");
        return eval(exp);
    }

    //evaluate the value of variables
    public int eval(String str){

        //System.out.println(str);

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
            //System.out.println(midIndex);
            int left = eval(str.substring(0, midIndex));
            int right = eval(str.substring(midIndex + 1, str.length()));
            return left + right;
        }else if (str.startsWith("sub(")) {
            str = str.substring(4, str.length() - 1);
            int midIndex = middleIndex(str);
            //System.out.println(midIndex);
            int left = eval(str.substring(0, midIndex));
            int right = eval(str.substring(midIndex + 1, str.length()));
            return left - right;
        }else if (str.startsWith("mult")) {
            str = str.substring(5, str.length() - 1);
            int midIndex = middleIndex(str);
            //System.out.println(midIndex);
            int left = eval(str.substring(0, midIndex));
            int right = eval(str.substring(midIndex + 1, str.length()));
            return left * right;
        }else if (str.startsWith("div(")) {
            str = str.substring(4, str.length() - 1);
            int midIndex = middleIndex(str);
            //System.out.println(midIndex);
            int left = eval(str.substring(0, midIndex));
            int right = eval(str.substring(midIndex + 1, str.length()));
            //can not div by 0
            if (right == 0) {
                logger.info("Invalid operation: divider is 0");
                System.exit(1);
            }
            return left / right;
        }else if (str.startsWith("let(")) {
            str = str.substring(4, str.length() - 1);
            int firstCommoa = middleIndex(str);
            String key = str.substring(0, firstCommoa);
            str = str.substring(firstCommoa + 1, str.length());
            firstCommoa = middleIndex(str);
            String valueStr = str.substring(0, firstCommoa);
            int value = eval(valueStr);
            map.put(key, value);
            return eval(str.substring(firstCommoa + 1, str.length()));
        }else {
            logger.warning("Invalid Input");
            System.exit(1);
        }

        return 0;
    }



    public static void main(String[] args) {

        //check if the input valid
        if (args.length != 1){
            Logger logger = Logger.getLogger("Cal");
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
