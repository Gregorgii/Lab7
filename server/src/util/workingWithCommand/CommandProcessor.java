package util.workingWithCommand;

import DB.DBManager;
import commands.clientCommands.AbstractClientCommand;
import managers.CollectionManager;
import things.StudyGroup;
import util.Request;
import util.Response;
import util.ResponseBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class CommandProcessor {
    private final DBManager dbManager;
    private final CollectionManager collectionManager;

    public CommandProcessor(DBManager dbManager, CollectionManager collectionManager){
        this.dbManager = dbManager;
        this.collectionManager = collectionManager;
    }

    public Response add(Request request){
        try{
            dbManager.validateUser(request.getUsername(), request.getPassword());
            StudyGroup studyGroup = request.getStudyGroupArgument();
            Integer id = dbManager.add(studyGroup, request.getUsername());
            studyGroup.setId(id);
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager.add(studyGroup)+ "It's id = " + id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Response clear(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            List<Integer> idList = dbManager.clear(request.getUsername());
            String result;
            if (idList.isEmpty()) {
                result = "Collection is already empty";
            } else {
                idList.forEach(collectionManager::removeById);
                result = "Collection cleared successfully, deleted elements ids: " + idList;
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response help(Request request, HashMap<String, AbstractClientCommand> availableCommands) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());

            StringBuilder stringBuilder = new StringBuilder();
            availableCommands.values().forEach(command -> stringBuilder.append(command.toString()).append("\n"));
            String result = "Available commands:\n" + stringBuilder.substring(0, stringBuilder.length() - 1);
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response info(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager.info()));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response removeById(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            dbManager.checkStudyGroupExistence(request.getIntegerArgument(), request.getUsername());
            dbManager.removeById(request.getIntegerArgument(), request.getUsername());
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager.removeById(request.getIntegerArgument())));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response removeFirst(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
            Integer id = collectionManager.getFirstId(idList);
            String result;
            if (id == null) {
                result = "Ваша коллекция пуста";
            } else {
                dbManager.removeById(id, request.getUsername());
                result = collectionManager.removeById(id);
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response removeGreater(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            StudyGroup studyGroup = request.getStudyGroupArgument();
            List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
            List<Integer> idListGreater = collectionManager.returnIdsOfGreater(studyGroup, idList);
            String result;
            if (collectionManager.getGroupCollection().isEmpty()) {
                result = "Коллекция пуста";
            } else if (idListGreater.isEmpty()) {
                result = "Элементов, больших, чем заданный, не найдено";
            } else {
                dbManager.removeByIds(idListGreater, request.getUsername());
                idListGreater.forEach(collectionManager::removeById);
                result = "Элементы, большие, чем заданный, успешно удалены"
                        + ", id удаленных элементов: " + idList.toString();
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response countGreaterThanTransferredStudents(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            Integer transferredStudents = request.getIntegerArgument();
            List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
            Long countGreater = collectionManager.countGreaterThanTransferred(transferredStudents, idList);
            String result;
            if (collectionManager.getGroupCollection().isEmpty()) {
                result = "Коллекция пуста";
            } else if (countGreater == 0 ) {
                result = "Элементов, больших, чем заданный, не найдено";
            } else {
                result = "Количество элементов, больших, чем заданный: "
                        + countGreater;
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response addIfMax(Request request){
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            StudyGroup studyGroup = request.getStudyGroupArgument();
            List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
            String result;
            if (collectionManager.getGroupCollection().isEmpty()) {
                Integer id = dbManager.add(studyGroup, request.getUsername());
                studyGroup.setId(id);
                return new Response(new ResponseBuilder()
                        .withMessageToResponse(collectionManager.add(studyGroup) + "It's id = " + id));
            } else if (collectionManager.isMax(studyGroup, idList)) {
                Integer id = dbManager.add(studyGroup, request.getUsername());
                studyGroup.setId(id);
                return new Response(new ResponseBuilder()
                        .withMessageToResponse(collectionManager.add(studyGroup) + "It's id = " + id));
            } else {
                return new Response(new ResponseBuilder().withMessageToResponse("These group isn't max. It wasn't added to collection"));
            }
        } catch (SQLException | IllegalArgumentException e){
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

        public Response update(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            dbManager.checkStudyGroupExistence(request.getIntegerArgument(), request.getUsername());
            dbManager.update(request.getIntegerArgument(), request.getStudyGroupArgument(), request.getUsername());
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(collectionManager.update(request.getIntegerArgument(), request.getStudyGroupArgument())));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response printFieldDescendingSemester(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            StringBuilder result = new StringBuilder(" ");
            for (StudyGroup sg : collectionManager.getGroupCollection()) {
                result.append(sg.getSemesterEnum().toString()).append(" ");
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result.toString()));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }



    public Response removeAnyByShouldBeExpelled(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            StudyGroup studyGroup = request.getStudyGroupArgument();
            List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
            List<Integer> idListGreater = collectionManager.returnIdsOfShouldBeExpelled(studyGroup, idList);
            String result;
            if (collectionManager.getGroupCollection().isEmpty()) {
                result = "Коллекция пуста";
            } else if (idListGreater.isEmpty()) {
                result = "Элементов, таких же как введенный не найдено";
            } else {
                dbManager.removeByIds(idListGreater, request.getUsername());
                idListGreater.forEach(collectionManager::removeById);
                result = "Элементы успешно удалены"
                        + ", id удаленных элементов: " + idList.toString();
            }
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(result));
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }

    public Response show(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            if (collectionManager.getGroupCollection().isEmpty()) {
                return new Response(new ResponseBuilder()
                        .withMessageToResponse("Коллекция пуста"));
            } else {
                List<Integer> idList = dbManager.getIdsOfUsersElements(request.getUsername());
                return new Response(new ResponseBuilder()
                        .withUsersCollectionToResponse(collectionManager.getUsersElements(idList))
                        .withAlienCollectionToResponse(collectionManager.getAlienElements(idList)));
            }
        } catch (SQLException | IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }
}
