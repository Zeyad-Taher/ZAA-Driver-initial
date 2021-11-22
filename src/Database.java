import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection connection = null;

    private Database () {

    }

    public static Connection getInstance() {
        if(connection == null){
            String url = "jdbc:mysql://localhost:3306/zaa";
            try{
                connection = DriverManager.getConnection(url, "root", "12345678");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}