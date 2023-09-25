package util;

import things.StudyGroup;

import java.io.Serializable;
import java.util.LinkedList;

public class Response implements Serializable {
    private final String messageToResponse;
    private final StudyGroup studyGroupToResponse;
    private final LinkedList<StudyGroup> usersElementsToResponse;
    private final LinkedList<StudyGroup> alienElementsToResponse;
    private boolean successToResponse = true;

    public Response(ResponseBuilder responseBuilder) {
        this.messageToResponse = responseBuilder.getMessageToResponse();
        this.studyGroupToResponse = responseBuilder.getStudyGroupToResponse();
        this.usersElementsToResponse = responseBuilder.getUsersElementsToResponse();
        this.alienElementsToResponse = responseBuilder.getAlienElementsToResponse();
        this.successToResponse = responseBuilder.isSuccess();
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

    public String getInfoAboutResponse() {
        return "Response contains: " + (messageToResponse == null ? "" : "message")
                + (studyGroupToResponse == null ? "" : ", studyGroup")
                + (usersElementsToResponse == null ? "" : ", collection");
    }

    @Override
    public String toString() {
        StringBuilder collection = new StringBuilder();
        if (!(usersElementsToResponse == null)) {
            if (usersElementsToResponse.isEmpty()) {
                collection.append("you haven't elements in collection");
            } else {
                for (StudyGroup studyGroup : usersElementsToResponse) {
                    collection.append(studyGroup.toString()).append("\n");
                }
            }
        }
        if (!(alienElementsToResponse == null)) {
            if (alienElementsToResponse.isEmpty()) {
                collection.append("Collection hasn't other users elements");
            } else {
                for (StudyGroup studyGroup : alienElementsToResponse) {
                    collection.append(studyGroup.toString()).append("\n");
                }
            }
            collection = new StringBuilder(collection.substring(0, collection.length() - 1));
            return String.valueOf(collection);
        }
        return (messageToResponse == null ? "" : messageToResponse)
                + (studyGroupToResponse == null ? "" : "\n" + studyGroupToResponse)
                + (usersElementsToResponse == null ? "" : "\n" + collection);
    }
}
