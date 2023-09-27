package generators;

import condition.Condition;
import things.Coordinates;
import things.Semester;
import util.Parser;
import util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class    ScriptStudyGroupGenerator extends Condition {
    private static final int QUANTITY_OF_ARGS = 11;
    private String[] stringStudyGroup;
    public ScriptStudyGroupGenerator(Scanner scanner){super(scanner);}

    public void setGroupName(){
        try{
            super.setGroupName(new Validator<String>(stringStudyGroup[0])
                    .withCheckingNull(false)
                    .getValue());
        } catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }

    public void setCoordinates(){
        try {
            super.setX(new Validator<Double>(stringStudyGroup[1])
                    .withCheckingNull(false)
                    .withCheckingFunction(Double::parseDouble, "type of x must be Double")
                    .withCheckingPredicate(arg -> (int) arg > Coordinates.X_MIN,
                            "value of x must be greater than " + Coordinates.X_MIN)
                    .getValue());
        } catch (IllegalArgumentException e) {
            super.getErrs().add(e.getMessage());
        }
        try {
            super.setY(new Validator<Float>(stringStudyGroup[2])
                    .withCheckingNull(false)
                    .withCheckingFunction(Float::parseFloat, "type of y must be Float")
                    .getValue());
        } catch (IllegalArgumentException e) {
            super.getErrs().add(e.getMessage());
        }
    }

    public void setStudentsCount(){
        try{
            super.setStudentsCount(new Validator<Long>(stringStudyGroup[3])
                    .withCheckingNull(false)
                    .withCheckingFunction(Long::parseLong, "Count of students must be Long")
                    .withCheckingPredicate(arg -> (int) arg > 0,
                            "Value of students count must be greater than 0")
                    .getValue());
        } catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }

    public void setShouldBeExpelled(){
        try{
            super.setShouldBeExpelled(new Validator<Integer>(stringStudyGroup[4])
                    .withCheckingNull(true)
                    .withCheckingFunction(Integer::parseInt, "Count of should be expelled must be Integer or null")
                    .withCheckingPredicate(arg -> (int) arg > 0,
                            "Value of should be expelled must be greater than 0")
                    .getValue());
        } catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }

    public void setTransferredStudents(){
        try {
            super.setTransferredStudents(new Validator<Integer>(stringStudyGroup[5])
                    .withCheckingNull(true)
                    .withCheckingFunction(Integer::parseInt, "Count of transferred students must be Integer or null")
                    .withCheckingPredicate(arg -> (int) arg > 0,
                            "Value of transferred students must be greater than 0")
                    .getValue());
        } catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }

    public void setSemesterEnum(){
        try {
            super.setSemesterEnum(new Validator<Semester>(stringStudyGroup[6])
                    .withCheckingNull(false)
                    .withCheckingFunction(Semester::valueOf,
                            "semester must be from: \n" + Semester.values() + "with right register")
                    .getValue());
        }catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }


    public void setGroupAdmin(){
        try {
            super.setPersonName(new Validator<String>(stringStudyGroup[7])
                    .withCheckingNull(false)
                    .getValue());
            super.setBirthday(new Validator<LocalDate>(stringStudyGroup[8])
                    .withCheckingNull(false)
                    .withCheckingFunction(LocalDate::parse, "Birthday must be local date with format 'yyyy-MM-dd'")
                    .getValue());
            super.setWeight(new Validator<Long>(stringStudyGroup[9])
                    .withCheckingNull(true)
                    .withCheckingFunction(Long::parseLong, "Weight must be Long or null")
                    .withCheckingPredicate(arg -> (int) arg > 0,
                            "Value of weight must be greater than 0")
                    .getValue());
            super.setPassportID(new Validator<String>(stringStudyGroup[10])
                    .withCheckingNull(false)
                    .getValue());
        } catch (IllegalArgumentException|DateTimeParseException e){
            super.getErrs().add(e.getMessage());
        }
    }

    public void errorHandler() {
        while (!getErrs().isEmpty()) {
            System.out.println(getErrs().remove());
        }
    }



    @Override
    public void generateStudyGroupFields() {
        try{
            stringStudyGroup = Parser.stringToStudyGroupStringParserFromFile(super.getScanner());
            Validator.validateQuantityOfArgs(stringStudyGroup, QUANTITY_OF_ARGS);
            setGroupName();
            setCoordinates();
            setStudentsCount();
            setTransferredStudents();
            setShouldBeExpelled();
            setSemesterEnum();
            setGroupAdmin();
        } catch (IllegalArgumentException e){
            super.getErrs().add(e.getMessage());
        }
    }

    @Override
    public boolean isCorrect(){
        return getErrs().isEmpty();
    }
}
