import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;

public class Database {
    private static Database uniqueInstance = null;
    private static Connection con;

    private Database () throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:zaa.db");
        System.out.println("SQLite connected");
    }

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if(uniqueInstance==null){
            uniqueInstance= new Database();
        }
        return con;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = Database.getInstance();
        Statement stat = con.createStatement();
//        String sql = "CREATE TABLE users (" +
//                "  username TEXT NOT NULL PRIMARY KEY," +
//                "  password TEXT NOT NULL," +
//                "  mobile_number TEXT NOT NULL," +
//                "  email TEXT NOT NULL," +
//                "  is_driver TINYINT NULL," +
//                "  natID TEXT NULL," +
//                "  drivingLicense TEXT NULL," +
//                "  areaSubscriptionID TEXT NULL);";
//        String sql = "INSERT INTO users" +
//                "(username," +
//                "password," +
//                "mobile_number," +
//                "email," +
//                "is_driver," +
//                "natID," +
//                "drivingLicense," +
//                "areaSubscriptionID)" +
//                "VALUES" +
//                "('ammar'," +
//                "1234," +
//                "'010'," +
//                "'a@a.com'," +
//                "0," +
//                "'id'," +
//                "'lc'," +
//                "null);";

        String sql = "SELECT * FROM users;";

//        String sql = "DROP TABLE users;";


        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("username"));
        }

        stat.close();
    }
}