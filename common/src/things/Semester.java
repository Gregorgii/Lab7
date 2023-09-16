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
        StringBuilder stringWeaponType = new StringBuilder();
        for (Semester i : values()) {
            stringWeaponType.append(i);
            stringWeaponType.append("\n");
        }
        return stringWeaponType.toString();
    }
}
