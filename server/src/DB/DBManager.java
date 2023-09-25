package DB;

import things.Semester;
import things.StudyGroup;
import things.StudyGroupBuilder;
import util.StringEncryptor;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBManager {
    private final Connector connector;

    public DBManager(Connector connector) {
        this.connector = connector;
    }

    public LinkedList<StudyGroup> loadCollection() throws SQLException {
        LinkedList<StudyGroup> groupCollection = new LinkedList<>();
        return connector.handleQuery((Connection connection) -> {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM study_group");
            while (result.next()) {
                String stringSemesterEnum = result
                        .getString("semester_enum");
                Semester semesterEnum = Semester.valueOf(stringSemesterEnum);
                StudyGroup studyGroup = new StudyGroup(new StudyGroupBuilder()
                        .withId(result.getInt("id"))
                        .withCreationDate(result.getDate("creation_date").toLocalDate())
                        .withGroupName(result.getString("name"))
                        .withCoordinates(result.getDouble("x"), result.getFloat("y"))
                        .withStudentsCount(result.getLong("students_count"))
                        .withShouldBeExpelled(result.getInt("should_be_expelled"))
                        .withTransferredStudents(result.getInt("transferred_students"))
                        .withSemesterEnum(semesterEnum)
                        .withGroupAdmin(result.getString("person_name"),
                                result.getDate("birthday").toLocalDate(),
                                result.getLong("weight"),
                                result.getString("passport_ID")));
                groupCollection.add(studyGroup);
            }
            return groupCollection;
        });

    }

    public Integer add(StudyGroup studyGroup, String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String addElementQuery = "INSERT INTO study_group "
                    + "(creationDate, name, x, y, students_count, should_be_expelled, transferred_students, "
                    + "semester_enum, person_name, birthday, weight, passport_ID) "
                    + "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, id FROM users WHERE users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(addElementQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, studyGroup.getGroupName());
            preparedStatement.setDate(2, Date.valueOf(studyGroup.getCreationDate().toString()));
            preparedStatement.setDouble(3, studyGroup.getCoordinates().getX());
            preparedStatement.setFloat(4, studyGroup.getCoordinates().getY());
            preparedStatement.setLong(5, studyGroup.getStudentsCount());
            preparedStatement.setInt(6, studyGroup.getShouldBeExpelled());
            preparedStatement.setInt(7, studyGroup.getTransferredStudents());
            preparedStatement.setString(8, studyGroup.getSemesterEnum().toString());
            preparedStatement.setString(9, studyGroup.getGroupAdmin().getPersonName());
            preparedStatement.setString(10, studyGroup.getGroupAdmin().getBirthday().toString());
            preparedStatement.setLong(11, studyGroup.getGroupAdmin().getWeight());
            preparedStatement.setString(12, studyGroup.getGroupAdmin().getPassportID());
            preparedStatement.setString(13, username);
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            result.next();
            return result.getInt(1);
        });
    }

    public List<Integer> clear(String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String clearQuery = "DELETE FROM study_group USING users "
                    + "WHERE study_group.owner_id = users.id AND users.login = ? "
                    + "RETURNING study_group.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(clearQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("id"));
            }
            return idList;
        });
    }

    public List<Integer> removeAnyByShouldBeExpelled(int shouldBeExpelled, String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String removeQuery = "DELETE FROM study_group USING users "
                    + "WHERE study_group.shouldBeExpelled = ? "
                    + "AND study_group.owner_id = users.id AND users.login = ? "
                    + "RETURNING study_group.id;";
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setInt(1, shouldBeExpelled);
            preparedStatement.setString(2, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("id"));
            }
            return idList;
        });
    }


    public List<Integer> getFieldDescendingSemester() throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String returnQuery = "SELECT FROM study_group USING users "
                    + "RETURNING study_group.semester_enum;";
            PreparedStatement preparedStatement = connection.prepareStatement(returnQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("id"));
            }
            return idList;
        });
    }
    public void removeById(Integer id, String username) throws SQLException {
        connector.handleQuery((Connection connection) -> {
            String removeQuery = "DELETE FROM study_group USING users "
                    + "WHERE study_group.id = ? "
                    + "AND study_group.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        });
    }

    public void update(Integer id, StudyGroup studyGroup, String username) throws SQLException {
        connector.handleQuery((Connection connection) -> {
            connection.createStatement().executeUpdate("BEGIN TRANSACTION;");
            String updateQuery = "UPDATE study_group "
                    + "SET name = ?, "
                    + "x = ?, "
                    + "y = ?, "
                    + "students_count = ?, "
                    + "should_be_expelled = ?, "
                    + "transferred_students = ?, "
                    + "semester_enum = ?, "
                    + "person_name = ?, "
                    + "birthday = ?, "
                    + "weight = ?, "
                    + "passport_ID = ? "
                    + "FROM users WHERE study_group.id = ? "
                    + "AND study_group.owner_id = users.id "
                    + "AND users.login = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, studyGroup.getGroupName());
            preparedStatement.setDate(2, Date.valueOf(studyGroup.getCreationDate().toString()));
            preparedStatement.setDouble(3, studyGroup.getCoordinates().getX());
            preparedStatement.setFloat(4, studyGroup.getCoordinates().getY());
            preparedStatement.setLong(5, studyGroup.getStudentsCount());
            preparedStatement.setInt(6, studyGroup.getShouldBeExpelled());
            preparedStatement.setInt(7, studyGroup.getTransferredStudents());
            preparedStatement.setString(8, studyGroup.getSemesterEnum().toString());
            preparedStatement.setString(9, studyGroup.getGroupAdmin().getPersonName());
            preparedStatement.setString(10, studyGroup.getGroupAdmin().getBirthday().toString());
            preparedStatement.setLong(11, studyGroup.getGroupAdmin().getWeight());
            preparedStatement.setString(12, studyGroup.getGroupAdmin().getPassportID());
            preparedStatement.setString(13, username);
            preparedStatement.executeUpdate();
            connection.createStatement().execute("COMMIT;");
        });
    }

    public void addUser(String username, String password) throws SQLException {
        connector.handleQuery((Connection connection) -> {
            String addUserQuery = "INSERT INTO users (login, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, StringEncryptor.encryptString(password));
            preparedStatement.executeUpdate();
        });
    }

    public String getPassword(String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String getPasswordQuery = "SELECT (password) "
                    + "FROM users WHERE users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(getPasswordQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            return null;
        });
    }

    public void validateUser(String username, String password) throws SQLException, IllegalArgumentException {
        if (!connector.handleQuery((Connection connection) ->
                StringEncryptor.encryptString(password).equals(getPassword(username)))) {
            throw new IllegalArgumentException("User doesn't exist");
        }
    }

    public boolean checkUsersExistence(String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String existenceQuery = "SELECT COUNT (*) "
                    + "FROM users "
                    + "WHERE users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(existenceQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count") > 0;
        });
    }

    public void checkStudyGroupExistence(Integer id, String username) throws SQLException {
        connector.handleQuery((Connection connection) -> {
            String existenceQuery = "SELECT COUNT (*) "
                    + "FROM study_group, users "
                    + "WHERE study_group.id = ? "
                    + "AND study_group.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(existenceQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            if (result.getInt("count") < 1) {
                throw new IllegalArgumentException("Access denied: id doesn't exist or you don't have access");
            }
        });
    }

    public List<Integer> getIdsOfUsersElements(String username) throws SQLException {
        return connector.handleQuery((Connection connection) -> {
            String existenceQuery = "SELECT study_group.id "
                    + "FROM study_group, users "
                    + "WHERE study_group.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(existenceQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getInt("id"));
            }
            return idList;
        });
    }

    public void removeByIds(List<Integer> idListLover, String username) throws SQLException {
        connector.handleQuery((Connection connection) -> {
            connection.createStatement().executeUpdate("BEGIN TRANSACTION;");
            String removeQuery = "DELETE FROM study_group USING users "
                    + "WHERE study_group.id = ? "
                    + "AND study_group.owner_id = users.id AND users.login = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setString(2, username);
            for (Integer id : idListLover) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            connection.createStatement().execute("COMMIT;");
        });


    }
}
