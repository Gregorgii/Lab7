package condition;

import things.StudyGroup;
import things.StudyGroupBuilder;

public class StudyGroupGenerator {
    private StudyGroup studyGroup = null;
    private Condition condition;
    public void changeCondition(Condition condition){this.condition = condition; }

    public void generateStudyGroup(){
        condition.generateStudyGroupFields();
        if (condition.isCorrect()){
            this.studyGroup = new StudyGroup(new StudyGroupBuilder()
                    .withGroupName(condition.getGroupName())
                    .withCoordinates(condition.getX(), condition.getY())
                    .withStudentsCount(condition.getStudentsCount())
                    .withShouldBeExpelled(condition.getShouldBeExpelled())
                    .withTransferredStudents(condition.getTransferredStudents())
                    .withSemesterEnum(condition.getSemesterEnum())
                    .withGroupAdmin(condition.getPersonName(), condition.getBirthday(), condition.getWeight(), condition.getPassportID()));
        } else{
            condition.errHandler();
            throw new IllegalArgumentException("Generation was unsuccessful");
        }
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }
}
