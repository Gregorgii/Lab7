package condition;

import things.Semester;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Scanner;

public abstract class Condition {
    private String groupName;
    private Double x;
    private Float y;
    private Long studentsCount;
    private Integer shouldBeExpelled;
    private Integer transferredStudents;
    private Semester semesterEnum;
    private String personName;
    private LocalDate birthday;
    private Long weight;
    private String passportID;
    private Scanner scanner;
    private ArrayDeque<String> errs = new ArrayDeque<>();

    public Condition(Scanner scanner) { this.scanner = scanner; }

    public abstract void generateStudyGroupFields();

    public boolean isCorrect() { return errs.isEmpty(); }

    public void errHandler() {
        while (!errs.isEmpty()){
            System.out.println(errs.remove());
        }
    }
    public String getGroupName() { return groupName; }

    public Double getX() {return x; }

    public Float getY() {return y; }

    public Long getStudentsCount() {return studentsCount; }

    public Integer getShouldBeExpelled() {return shouldBeExpelled; }

    public Integer getTransferredStudents() {return transferredStudents; }

    public Semester getSemesterEnum() {return semesterEnum; }

    public String getPersonName() {return personName; }

    public LocalDate getBirthday() {return birthday; }

    public Long getWeight() {return weight; }

    public String getPassportID() {return passportID; }

    public void setGroupName(String groupName) {this.groupName = groupName; }

    public void setX(Double x) {this.x = x; }

    public void setY(Float y) {this.y = y; }

    public void setStudentsCount(Long studentsCount) {this.studentsCount = studentsCount; }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {this.shouldBeExpelled = shouldBeExpelled; }

    public void setTransferredStudents(Integer transferredStudents) {this.transferredStudents = transferredStudents; }

    public void setSemesterEnum(Semester semesterEnum) {this.semesterEnum = semesterEnum; }

    public void setPersonName(String personName) {this.personName = personName; }

    public void setBirthday(LocalDate birthday) {this.birthday = birthday; }

    public void setWeight(Long weight) {this.weight = weight; }

    public void setPassportID(String passportID) {this.passportID = passportID; }

    public Scanner getScanner() {return scanner; }

    public ArrayDeque<String> getErrs() {return errs; }

    public void setErrs(ArrayDeque<String> errs) {this.errs = errs; }

    public void setScanner(Scanner scanner) {this.scanner = scanner; }
}
