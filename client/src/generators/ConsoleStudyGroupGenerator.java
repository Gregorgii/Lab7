package generators;

import condition.Condition;
import things.Coordinates;
import things.Semester;
import util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleStudyGroupGenerator extends Condition {
    public ConsoleStudyGroupGenerator(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void generateStudyGroupFields() {
        setValue("Set name of study group", this::setGroupName);
        setValue("Set coordinate X, min value is" + Coordinates.X_MIN , this::setX);
        setValue("Set coordinate Y", this::setY);
        setValue("Set students count in study group", this::setStudentsCount);
        setValue("Set count of transferred students in the group", this::setTransferredStudents);
        setValue("Set count of students who should be expelled", this::setShouldBeExpelled);
        setValue("Set semester enum. Choose from " + Semester.show(), this::setSemesterEnum);
        setValue("Set group admin ", this::generateGroupAdminFields);

    }

    public void generateGroupAdminFields() {
        setValue("Set name of admin", this::setPersonName);
        setValue("Set birthday in format yyyy-MM-dd", this::setBirthday);
        setValue("Set weight", this::setWeight);
        setValue("Set passport id", this::setPassportId);

    }

    public void setValue(String message, Runnable runnable) {
        System.out.println(message);
        boolean isRunning = true;
        while (isRunning) {
            try {
                runnable.run();
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n Repeat input");
            }
        }
    }

    public void setGroupName() throws IllegalArgumentException{

            super.setGroupName(new Validator<String>(super.getScanner())
                    .withCheckingNull(true)
                    .getValue());

    }

    public void setX() throws IllegalArgumentException {
        super.setX(new Validator<Double>(super.getScanner())
                .withCheckingNull(false)
                .withCheckingFunction(Double::parseDouble, "type of x must be Double")
                .withCheckingPredicate(arg -> (double) arg > Coordinates.X_MIN,
                        "value of x must be greater than " + Coordinates.X_MIN)
                .getValue());

    }

    public void setY() throws IllegalArgumentException{
        super.setY(new Validator<Float>(super.getScanner())
                .withCheckingNull(false)
                .withCheckingFunction(Float::parseFloat, "type of y must be Float")
                .getValue());
    }

    public void setStudentsCount() throws IllegalArgumentException{
            super.setStudentsCount(new Validator<Long>(super.getScanner())
                    .withCheckingNull(false)
                    .withCheckingFunction(Long::parseLong, "Count of students must be Long")
                    .withCheckingPredicate(arg -> (long) arg > 0,
                            "Value of students count must be greater than 0")
                    .getValue());
        }

    public void setShouldBeExpelled() throws IllegalArgumentException{
            super.setShouldBeExpelled(new Validator<Integer>(super.getScanner())
                    .withCheckingNull(true)
                    .withCheckingFunction(Integer::parseInt, "Count of should be expelled must be Integer or null")
                    .withCheckingPredicate(arg -> (int) arg > 0,
                            "Value of should be expelled must be greater than 0")
                    .getValue());
        }

    public void setTransferredStudents() throws IllegalArgumentException {
        super.setTransferredStudents(new Validator<Integer>(super.getScanner())
                .withCheckingNull(true)
                .withCheckingFunction(Integer::parseInt, "Count of transferred students must be Integer or null")
                .withCheckingPredicate(arg -> (int) arg > 0,
                        "Value of transferred students must be greater than 0")
                .getValue());
    }


    public void setSemesterEnum() throws IllegalArgumentException{
            super.setSemesterEnum(new Validator<Semester>(super.getScanner())
                    .withCheckingNull(false)
                    .withCheckingFunction(Semester::valueOf,
                            "semester must be from: \n" + Semester.show() + "with right register")
                    .getValue());
        }

    public void setPersonName() throws IllegalArgumentException {
        super.setPersonName(new Validator<String>(super.getScanner())
                .withCheckingNull(false)
                .getValue());
    }
    public void setBirthday() throws IllegalArgumentException, DateTimeParseException {
        super.setBirthday(new Validator<LocalDate>(super.getScanner())
                .withCheckingNull(false)
                .withCheckingFunction(LocalDate::parse, "Birthday must be local date with format 'yyyy-MM-dd'")
                .getValue());
    }
    public void setWeight() throws IllegalArgumentException {
        super.setWeight(new Validator<Long>(super.getScanner())
                .withCheckingNull(true)
                .withCheckingFunction(Long::parseLong, "Weight must be Long or null")
                .withCheckingPredicate(arg -> (Long) arg > 0,
                        "Value of weight must be greater than 0")
                .getValue());
    }
    public void setPassportId() throws IllegalArgumentException{
        super.setPassportID(new Validator<String>(super.getScanner())
                .withCheckingNull(false)
                .getValue());
    }



}