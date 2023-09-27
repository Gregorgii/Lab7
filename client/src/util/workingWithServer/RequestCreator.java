package util.workingWithServer;

import things.RequestType;
import things.StudyGroup;
import util.Request;
import util.RequestBuilder;
import util.Validator;
import util.workingWithCommand.AvailableCommands;
import util.workingWithCommand.CommandToSend;

public class RequestCreator {

    public Request createRequestOfCommand(CommandToSend command){
        String name = command.getCommandName();
        Request request;
        if (AvailableCommands.COMMANDS_WITHOUT_ARGS.contains(name)){
            request = createRequestWithoutArgs(command);
        } else if (AvailableCommands.COMMANDS_WITH_ID_ARG.contains(name)){
            request = createRequestWithID(command);
        } else if (AvailableCommands.COMMANDS_WITH_STUDY_GROUP_ARG.contains(name)){
            request = createRequestWithStudyGroup(command);
        } else if (AvailableCommands.COMMANDS_WITH_STUDY_GROUP_AND_ID_ARG.contains(name)){
            request = createRequestWithStudyGroupAndId(command);
        } else if (AvailableCommands.COMMANDS_WITH_TRANSFERRED_ARG.contains(name)){
            request = createRequestWithTransferred(command);
        } else if (AvailableCommands.COMMANDS_WITH_EXPELLED_ARG.contains(name)){
            request = createRequestWithExpelled(command);
        } else {
            throw new IllegalArgumentException("Ur command isn't real. Print help");
        }
        request.setRequestTypeArgument(RequestType.COMMAND);
        return request;
    }

    private Request createRequestWithoutArgs(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
        return new Request(new RequestBuilder()
                .withName(command.getCommandName()));
    }

    private Request createRequestWithID(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Integer id = new Validator<Integer>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt, "value of id must be a number")
                .withCheckingPredicate(arg -> (int) arg > 0, "id must be greater than 0")
                .getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withIntegerArgument(id));
    }

    private Request createRequestWithStudyGroup(CommandToSend command){
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
        StudyGroup.getStudyGroupGenerator().generateStudyGroup();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withStudyGroupArgument(StudyGroup
                        .getStudyGroupGenerator()
                        .getStudyGroup()));
    }

    private Request createRequestWithStudyGroupAndId(CommandToSend command) {

        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Integer id = new Validator<Integer>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt, "id must be a number")
                .withCheckingPredicate(arg -> (Integer) arg > 0, "id must be greater than 0")
                .getValue();
        StudyGroup.getStudyGroupGenerator().generateStudyGroup();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withIntegerArgument(id)
                .withStudyGroupArgument(StudyGroup
                        .getStudyGroupGenerator()
                        .getStudyGroup()));
    }

    private Request createRequestWithTransferred(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Integer transferred = new Validator<Integer>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt,
                        " count of transferred students must be a number")
                .getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withIntegerArgument(transferred));
    }

    private Request createRequestWithExpelled(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Integer expelled = new Validator<Integer>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt,
                        " count of should be expelled students must be a number")
                .getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withIntegerArgument(expelled));
    }


}
