package util;

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

    public Request(RequestBuilder requestBuilder){
        this.commandName = requestBuilder.getCommandName();
        this.longArgument = requestBuilder.getLongArgument();
        this.integerArgument = requestBuilder.getIntegerArgument();
        this.doubleArgument = requestBuilder.getDoubleArgument();
        this.studyGroupArgument = requestBuilder.getStudyGroupArgument();
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

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString(){
        return null;
    }
}
