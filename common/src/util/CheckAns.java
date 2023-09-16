package util;

import java.util.Locale;

public class CheckAns {
    private CheckAns(){
    }

    public static boolean checkAns(String string){
        String bool = string.toLowerCase(Locale.ROOT);
        if ("yes".equals(bool) || "true".equals(bool) || "y".equals(bool)){
            return true;
        } else if ("no".equals(bool) || "false".equals(bool) || "n".equals(bool)){
            return false;
        } else{
            throw new IllegalArgumentException("Value must be 'yes' or 'no' only");
        }
    }
}
