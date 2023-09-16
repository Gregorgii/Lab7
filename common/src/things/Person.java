package things;

import java.io.Serializable;
import java.time.LocalDate;

/**
* The class Person th work w persons
*/

public class Person implements Serializable {

    private String personName; //Поле не может быть null, Строка не может быть пустой

    private LocalDate birthday; //Поле не может быть null

    private Long weight; //Значение поля должно быть больше 0

    private String passportID; //Строка не может быть пустой, Поле не может быть null


    public Person(String personName, LocalDate birthday, Long weight, String passportID){
        this.personName = personName;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
    }
    /**
     * @return The persons name
     */

    public Person(){

    }

    public String getPersonName(){
        return personName;
    }
    /**
     * @return The persons bday
     */

    public LocalDate getBirthday(){
        return birthday;
    }
    /**
     * @return The perss weight
     */

    public Long getWeight(){
        return weight;
    }

    /**
     * @return The perses passportID
     */

    public String getPassportID(){
        return passportID;
    }

    @Override
    public String toString(){
        return " Admin with name " + personName + " and with birthday " + birthday + "\n"
                + "and passport ID " + passportID + "\n";
    }
    
}
