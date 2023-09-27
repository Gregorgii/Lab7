package DBInterface;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLFunction<T, R> {
    R apply(T t) throws SQLException, InterruptedException;
}