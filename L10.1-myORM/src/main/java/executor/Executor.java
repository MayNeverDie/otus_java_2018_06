package executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public int executeUpdate(String update) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(update);
            return statement.getUpdateCount();
        }
    }
}
