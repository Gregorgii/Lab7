package util;

import things.StudyGroup;

import java.util.LinkedList;

public class ResponseBuilder {
    private String messageToResponse;
    private StudyGroup studyGroupToResponse;
    private LinkedList<StudyGroup> usersElementsToResponse;
    private LinkedList<StudyGroup> alienElementsToResponse;
    private boolean successToResponse = true;

    public ResponseBuilder withMessageToResponse(String messageToResponse) {
        this.messageToResponse = messageToResponse;
        return this;
    }

    public ResponseBuilder withStudyGroupToResponse(StudyGroup studyGroupToResponse) {
        this.studyGroupToResponse = studyGroupToResponse;
        return this;
    }

    public ResponseBuilder withUsersCollectionToResponse(LinkedList<StudyGroup> usersElementsToResponse) {
        this.usersElementsToResponse = usersElementsToResponse;
        return this;
    }

    public ResponseBuilder withAlienCollectionToResponse(LinkedList<StudyGroup> alienElementsToResponse) {
        this.alienElementsToResponse = alienElementsToResponse;
        return this;
    }

    public ResponseBuilder withSuccessToResponse(boolean successToResponse) {
        this.successToResponse = successToResponse;
        return this;
    }

    public String getMessageToResponse() {
        return messageToResponse;
    }

    public StudyGroup getStudyGroupToResponse() {
        return studyGroupToResponse;
    }

    public LinkedList<StudyGroup> getUsersElementsToResponse() {
        return usersElementsToResponse;
    }

    public LinkedList<StudyGroup> getAlienElementsToResponse() {
        return alienElementsToResponse;
    }

    public boolean isSuccess() {
        return successToResponse;
    }
}
