package util;

import things.StudyGroup;

import java.util.Scanner;

public class Parser {
    private static final int QUANTITY_OF_ARGS = 11;
    private Parser(){
    }

    public static String[] stringToStudyGroupStringParserFromScript(Scanner scanner){
        String[] stringStudyGroup = new String[QUANTITY_OF_ARGS];
        for (int i = 0; i < QUANTITY_OF_ARGS; i++){
            if (scanner.hasNextLine()){
                stringStudyGroup[i] = scanner.nextLine();
            } else {
                throw new IllegalArgumentException("Wrong count of args, this command wants 11 args");
            }
        }
        return stringStudyGroup;
    }

    public static String[] stringToStudyGroupStringParserFromFile(Scanner scanner){
        String string = scanner.nextLine();
        String[] stringStudyGroup = string.split(",");
        for (int i = 0; i < stringStudyGroup.length; i++){
            stringStudyGroup[i] = stringStudyGroup[i].trim();
        }
        return stringStudyGroup;
    }

    public static String studyGroupToStringParser(StudyGroup studyGroup){
        String string = "";
        string += studyGroup.getGroupName() + "," + studyGroup.getCoordinates().getX() + ","
                + studyGroup.getCoordinates().getY() + "," + studyGroup.getStudentsCount() + ","
                + (studyGroup.getShouldBeExpelled() == null ? "null" : studyGroup.getShouldBeExpelled()) + ","
                + (studyGroup.getTransferredStudents() == null ? "null" : studyGroup.getTransferredStudents()) + ","
                + studyGroup.getSemesterEnum() + "," + studyGroup.getGroupAdmin().getPersonName() + ","
                + studyGroup.getGroupAdmin().getBirthday() + "," + studyGroup.getGroupAdmin().getWeight() + ","
                + studyGroup.getGroupAdmin().getPassportID() + "\n";
        return string;
    }
}
