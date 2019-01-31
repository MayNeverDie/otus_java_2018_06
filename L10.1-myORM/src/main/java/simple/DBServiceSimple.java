package simple;

import base.DBService;
import executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceSimple implements DBService {

    private final Connection connection;
    private static final String CREATE_TABLE_USER = "create table if not exists user (id bigint(20) auto_increment, name varchar(256), age int(3), primary key (id))";
    private static final String DROP_TABLE_USER = "drop table user";
    private static final String INSERT_USER = "insert into user (name) values ('%s')";

    public DBServiceSimple() {
        this.connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                    "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + getConnection().getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void prepareTables() throws SQLException {
        Executor exec = new Executor(connection);
        exec.executeUpdate(CREATE_TABLE_USER);
        System.out.println("Tables created.");
    }

    @Override
    public void dropTables() throws SQLException {
        Executor exec = new Executor(connection);
        exec.executeUpdate(DROP_TABLE_USER);
        System.out.println("Table user dropped.");
    }

    @Override
    public void addUsers(String... names) throws SQLException {
        Executor executor = new Executor(getConnection());
        for (String name:names){
            int rows = executor.executeUpdate(String.format(INSERT_USER,name));
            System.out.println("Users added, changed "+rows+" rows");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed.");
    }

    protected Connection getConnection() {
        return connection;
    }
}
