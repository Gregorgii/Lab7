package things;

import java.io.Serializable;

/**
* The enum that include number of sem.
*/
public enum Semester implements Serializable {
    SECOND,
    THIRD,
    FIFTH,
    SEVENTH,
    EIGHTH;

    public static String show() {
        StringBuilder stringSemesterEnum = new StringBuilder();
        for (Semester i : values()) {
            stringSemesterEnum.append(i);
            stringSemesterEnum.append("\n");
        }
        return stringSemesterEnum.toString();
    }
}
