package util;

import things.RequestType;
import things.StudyGroup;

import java.io.Serializable;
import java.time.LocalTime;

public class Request implements Serializable {
    private final String commandName;
    private String clientInfo;
    private LocalTime currentTime;
    private final Long longArgument;
    private final Integer integerArgument;
    private final Double doubleArgument;
    private final StudyGroup studyGroupArgument;
    private RequestType requestTypeArgument;

    public Request(RequestBuilder requestBuilder){
        this.commandName = requestBuilder.getCommandName();
        this.longArgument = requestBuilder.getLongArgument();
        this.integerArgument = requestBuilder.getIntegerArgument();
        this.doubleArgument = requestBuilder.getDoubleArgument();
        this.studyGroupArgument = requestBuilder.getStudyGroupArgument();
        this.requestTypeArgument = requestBuilder.getRequestTypeArgument();
    }

    public String getCommandName() {
        return commandName;
    }

    public Long getLongArgument() {
        return longArgument;
    }

    public Integer getIntegerArgument() {
        return integerArgument;
    }

    public Double getDoubleArgument() {
        return doubleArgument;
    }

    public StudyGroup getStudyGroupArgument() {
        return studyGroupArgument;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public String getClientInfo() {
        return clientInfo;
    }
    public RequestType getRequestTypeArgument() { return requestTypeArgument; }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public void setRequestTypeArgument(RequestType requestTypeArgument) { this.requestTypeArgument = requestTypeArgument; }

    @Override
    public String toString(){
        return null;
    }
}
