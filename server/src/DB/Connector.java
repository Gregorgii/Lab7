package DB;

import DBInterface.SQLConsumer;
import DBInterface.SQLFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

        private final String dbUrl = "jdbc:postgresql://localhost:5432/studs";
        private final String user = "postgres";
        private final String pass = "368462";
        public Connector() {
            try {
                Class.forName("org.postgresql.Driver");
                initializeDB();
            } catch (ClassNotFoundException e) {
                System.out.println("No DB driver!");
                System.exit(1);
            } catch (SQLException e) {
                System.out.println("Error occurred during initializing tables!" + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void handleQuery(SQLConsumer<Connection> queryBody) throws SQLException {
            try (Connection connection = DriverManager.getConnection(dbUrl, user, pass)) {
                queryBody.accept(connection);
            }
        }

        public <T> T handleQuery(SQLFunction<Connection, T> queryBody) throws SQLException {
            try (Connection connection = DriverManager.getConnection(dbUrl, user, pass)) {
                return queryBody.apply(connection);
            }
        }


        private void initializeDB() throws SQLException {

            Connection connection = DriverManager.getConnection(dbUrl, user, pass);

            Statement statement = connection.createStatement();

            statement.execute("CREATE SEQUENCE IF NOT EXISTS study_group_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1");

            statement.execute("CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1");

            statement.execute("CREATE TABLE IF NOT EXISTS users "
                    + "("
                    + "login varchar(255) NOT NULL UNIQUE CHECK(login<>''),"
                    + "password varchar(255) NOT NULL CHECK(password<>''),"
                    + "id BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq')"
                    + ");");

            statement.execute("CREATE TABLE IF NOT EXISTS study_group "
                    + "("
                    + "id BIGINT PRIMARY KEY DEFAULT nextval('study_group_id_seq'),"
                    + "creationDate date NOT NULL,"
                    + "name VARCHAR(50) NOT NULL CHECK(name<>''),"
                    + "x DOUBLE NOT NULL CHECK(x > -771),"
                    + "y FLOAT NOT NULL,"
                    + "studentsCount LONG NOT NULL,"
                    + "shouldBeExpelled INT,"
                    + " transferredStudents INT,"
                    + "semester varchar(7) NOT NULL CHECK(semesterEnum = 'SECOND' "
                    + "OR semesterEnum = 'THIRD' "
                    + "OR semesterEnum = 'FIFTH' "
                    + "OR semesterEnum = 'SEVENTH' "
                    + "OR semesterEnum = 'EIGHTH'),"
                    + "personName VARCHAR(50) NOT NULL CHECK(personName <> ''),"
                    + "birthday DATE NOT NULL,"
                    + "weight LONG CHECK(weight > 0),"
                    + "passportID VARCHAR(50) NOT NULL CHECK(passportID <> ''),"
                    + "owner_id BIGINT NOT NULL REFERENCES users (id)"
                    + ");");
            connection.close();
        }
    }

}
