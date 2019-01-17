package simple;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    static Connection getConnection() {

        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://" +       //db type
                    "localhost:" +               //host name
                    "3306/" +                    //port
                    "db_example?" +               //db name
                    "user=mayneverdie&" +              //login
                    "password=mayneverdie&" +          //password
                    "useSSL=false";              //do not use Secure Sockets Layer;
            return DriverManager.getConnection(url);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
