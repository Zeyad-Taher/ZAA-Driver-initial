import java.io.IOException;
import java.sql.SQLException;

public class ZAA {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Load.loadInit();
        UserInterface ui = new UserInterface();
        ui.chooseEntityMenu();
    }
}
