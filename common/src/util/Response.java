package util;

import things.StudyGroup;

import java.io.Serializable;
import java.util.LinkedList;

public class Response implements Serializable {
    private final String messageToResponse;
    private final StudyGroup studyGroupToResponse;
    private final LinkedList<StudyGroup> collectionToResponse;

    public Response(ResponseBuilder responseBuilder) {
        this.messageToResponse = responseBuilder.getMessageToResponse();
        this.studyGroupToResponse = responseBuilder.getStudyGroupToResponse();
        this.collectionToResponse = responseBuilder.getCollectionToResponse();
    }

    public String getMessageToResponse() {
        return messageToResponse;
    }

    public StudyGroup getStudyGroupToResponse() {
        return studyGroupToResponse;
    }

    public LinkedList<StudyGroup> getCollectionToResponse() {
        return collectionToResponse;
    }

    public String getInfoAboutResponse() {
        return "Response contains:" + (messageToResponse == null ? "" : "message") +
                (studyGroupToResponse == null ? "" : ", studyGroup") +
                (collectionToResponse == null ? "" : ", collection");
    }

    @Override
    public String toString() {
        StringBuilder collection = new StringBuilder();
        if (!(collectionToResponse == null)) {
            if (collectionToResponse.isEmpty()) {
                collection.append("Коллекция пуста");
            } else {
                for (StudyGroup studyGroup : collectionToResponse) {
                    collection.append(studyGroup.toString()).append("\n");
                }
            }

            return String.valueOf(collection);
        }
        return (messageToResponse == null ? "" : messageToResponse) +
                (studyGroupToResponse == null ? "" : studyGroupToResponse) +
                "\n" + collection;
    }
}
