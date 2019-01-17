package simple;

import base.DBService;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceSimple implements DBService {

    private final Connection connection;

    public DBServiceSimple() {
        this.connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        return null;
    }

    @Override
    public void prepareTables() throws SQLException {

    }

    @Override
    public void dropTables() throws SQLException {

    }

    @Override
    public void close() throws Exception {

    }
}
