package base;

import java.sql.SQLException;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void prepareTables() throws SQLException;

    void dropTables() throws SQLException;
}
