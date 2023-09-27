package DB;

import DBInterface.SQLConsumer;
import DBInterface.SQLFunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connector {

    private final String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    Properties info = new Properties();


        public Connector() {
            try{
                FileInputStream conf = new FileInputStream("C:/Users/mavri/Documents/GitHub/Lab7/db.cfg");
                info.load(conf);
                Class.forName("org.postgresql.Driver");
                initializeDB();
            } catch (ClassNotFoundException e) {
                System.out.println("No DB driver!");
                System.exit(1);
            } catch (SQLException e) {
                System.out.println("Error occurred during initializing tables!" + e.getMessage() + e.getErrorCode());
                e.printStackTrace();
                System.exit(1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void handleQuery(SQLConsumer<Connection> queryBody) throws SQLException {
            try (Connection connection = DriverManager.getConnection(dbUrl, info)) {
                queryBody.accept(connection);
            }
        }

        public <T> T handleQuery(SQLFunction<Connection, T> queryBody) throws SQLException {
            try (Connection connection = DriverManager.getConnection(dbUrl, info)) {
                return queryBody.apply(connection);
            }
        }


        private void initializeDB() throws SQLException {

            Connection connection = DriverManager.getConnection(dbUrl, info);
            Statement statement = connection.createStatement();
            statement.execute("CREATE SEQUENCE IF NOT EXISTS study_group_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647");

            statement.execute("CREATE SEQUENCE IF NOT EXISTS users_id_seq INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647");

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
                    + "x DOUBLE PRECISION NOT NULL CHECK(x > -771),"
                    + "y FLOAT NOT NULL,"
                    + "studentsCount DOUBLE PRECISION NOT NULL,"
                    + "shouldBeExpelled INT,"
                    + " transferredStudents INT,"
                    + "semesterEnum varchar(7) NOT NULL CHECK(semesterEnum = 'SECOND' "
                    + "OR semesterEnum = 'THIRD' "
                    + "OR semesterEnum = 'FIFTH' "
                    + "OR semesterEnum = 'SEVENTH' "
                    + "OR semesterEnum = 'EIGHTH'),"
                    + "personName VARCHAR(50) NOT NULL CHECK(personName <> ''),"
                    + "birthday DATE NOT NULL,"
                    + "weight DOUBLE PRECISION CHECK(weight > 0),"
                    + "passportID VARCHAR(50) NOT NULL CHECK(passportID <> ''),"
                    + "owner_id BIGINT NOT NULL REFERENCES users (id)"
                    + ");");
            connection.close();
        }
}

