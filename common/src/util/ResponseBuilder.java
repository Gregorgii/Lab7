package util;

import things.StudyGroup;

import java.util.LinkedList;

public class ResponseBuilder {
    private String messageToResponse;
    private StudyGroup studyGroupToResponse;
    private LinkedList<StudyGroup> collectionToResponse;

    public ResponseBuilder withMessageToResponse(String messageToResponse){
        this.messageToResponse = messageToResponse;
        return this;
    }

    public ResponseBuilder withStudyGroupToResponse(StudyGroup studyGroupToResponse){
        this.studyGroupToResponse = studyGroupToResponse;
        return this;
    }

    public ResponseBuilder withCollectionToResponse(LinkedList<StudyGroup> collectionToResponse){
        this.collectionToResponse = collectionToResponse;
        return this;
    }

    public String getMessageToResponse() {return messageToResponse; }

    public StudyGroup getStudyGroupToResponse() {return studyGroupToResponse; }

    public LinkedList<StudyGroup> getCollectionToResponse() {return collectionToResponse; }
}
