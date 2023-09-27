package things;


import condition.StudyGroupGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;


/**
* The hugest class that include all info about students and calls Study group
*/

public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private static final StudyGroupGenerator STUDY_GROUP_GENERATOR = new StudyGroupGenerator();

    private Integer id;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String groupName; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null

    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически


    private Long studentsCount; //Значение поля должно быть больше 0

    private Integer shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null

    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле может быть null

    private Semester semesterEnum; //Поле не может быть null

    private Person groupAdmin; //Поле не может быть null

    public StudyGroup(StudyGroupBuilder studyGroupBuilder){
        this.id = studyGroupBuilder.getId();
        this.creationDate = studyGroupBuilder.getCreationDate();
        this.groupName = studyGroupBuilder.getGroupName();
        this.coordinates = studyGroupBuilder.getCoordinates();
        this.studentsCount = studyGroupBuilder.getStudentsCount();
        this.shouldBeExpelled = studyGroupBuilder.getShouldBeExpelled();
        this.transferredStudents = studyGroupBuilder.getTransferredStudents();
        this.semesterEnum = studyGroupBuilder.getSemesterEnum();
        this.groupAdmin = studyGroupBuilder.getGroupAdmin();
    }


    public void setId(Integer id) {this.id = id;}

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
    * @return The info about Study Group (id)
    */

    public Integer getId(){
        return id;
    }
    /**
    * @return The info about Study Group (name)
    */
    public String getGroupName(){
        return groupName;
    }
    /**
    * @return The info about Study Group (coordinates)
    */

    public Coordinates getCoordinates(){
        return coordinates;
    }
    /**
     * @return The info about Study Group (creation date)
     */

    public ZonedDateTime getCreationDate(){
        return creationDate;
    }

    /**
    * @return The info about Study Group (count of studs)
    */

    public Long getStudentsCount(){
        return studentsCount;
    }
    /**
    * @return The info about Study Group (how much should be expelled)
    */
    

    public Integer getShouldBeExpelled(){
        return shouldBeExpelled;
    }
    /**
    * @return The info about Study Group (how much was transferred)
    */

    public Integer getTransferredStudents(){
        return transferredStudents;
    }
    /**
    * @return The info about Study Group (number of sem)
    */

    public Semester getSemesterEnum(){
        return semesterEnum;
    }
    /**
    * @return The info about Study Group (groups admin)
    */

    public Person getGroupAdmin(){
        return groupAdmin;
    }
    public static StudyGroupGenerator getStudyGroupGenerator(){return STUDY_GROUP_GENERATOR; }


    @Override
    public int compareTo(StudyGroup o) {
        return groupName.compareTo(o.getGroupName());
    }

    @Override
    public String toString(){
        return "Name of group " + groupName + " with id: " + id + "\n"
                + "with count of students " + studentsCount +" and should be expelled " + shouldBeExpelled + "\n"
                + "with transferred students " + transferredStudents + " and semester " + semesterEnum + "\n"
                + "and with group admin " + groupAdmin.toString();
    }
}