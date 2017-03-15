/**
 * Created by BigBlackFace on 17/3/14.
 */

import java.util.logging.Logger;

public class Cal {

    private String exp;



    public static void main(String[] args) {

        if (args.length != 1){
            Logger logger = Logger.getLogger("Main");
            logger.info("There is wrong input");
            return;
        }

        String str = args[0];

    }
}
