package things;

import java.io.Serializable;
import java.time.LocalDate;

public class StudyGroupBuilder implements Serializable {

    private Integer id;
    private LocalDate creationDate;
    private String groupName;
    private Coordinates coordinates;
    private Long studentsCount;
    private Integer shouldBeExpelled;
    private Integer transferredStudents;
    private Semester semesterEnum;
    private  Person groupAdmin;

    public StudyGroupBuilder withId(Integer id){
        this.id = id;
        return this;
    }

    public StudyGroupBuilder withCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
        return this;
    }

    public StudyGroupBuilder withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public StudyGroupBuilder withCoordinates(Double x, Float y){
        this.coordinates = new Coordinates(x, y);
        return this;
    }

    public StudyGroupBuilder withStudentsCount(Long studentsCount){
        this.studentsCount = studentsCount;
        return this;
    }

    public StudyGroupBuilder withShouldBeExpelled(Integer shouldBeExpelled){
        this.shouldBeExpelled = shouldBeExpelled;
        return this;
    }
    public StudyGroupBuilder withTransferredStudents(Integer transferredStudents){
        this.transferredStudents = transferredStudents;
        return this;
    }

    public StudyGroupBuilder withSemesterEnum(Semester semesterEnum){
        this.semesterEnum = semesterEnum;
        return this;
    }

    public StudyGroupBuilder withGroupAdmin(String personName, LocalDate birthday, Long weight, String passportID){
        this.groupAdmin = new Person(personName, birthday, weight, passportID);
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

}
