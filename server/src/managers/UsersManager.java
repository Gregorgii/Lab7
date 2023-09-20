package managers;

import DB.DBManager;
import util.Request;
import util.Response;
import util.ResponseBuilder;

import java.sql.SQLException;

public class UsersManager {

    private final DBManager dbManager;

    public UsersManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Response registerNewUser(Request request) {
        try {
            if (!dbManager.checkUsersExistence(request.getUsername())) {
                dbManager.addUser(request.getUsername(), request.getPassword());
                return new Response(new ResponseBuilder()
                        .withMessageToResponse("Registration successful"));
            } else {
                return new Response(new ResponseBuilder()
                        .withMessageToResponse("This name already exists")
                        .withSuccessToResponse(false));
            }
        } catch (SQLException e) {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse("Error in working with db")
                    .withSuccessToResponse(false));
        }
    }

    public Response loginUser(Request request) {
        try {
            dbManager.validateUser(request.getUsername(), request.getPassword());
            return new Response(new ResponseBuilder().withMessageToResponse("U r inside"));
        } catch (IllegalArgumentException e) {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage())
                    .withSuccessToResponse(false));
        } catch (SQLException e) {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse("Error in working with db")
                    .withSuccessToResponse(false));
        }
    }
}