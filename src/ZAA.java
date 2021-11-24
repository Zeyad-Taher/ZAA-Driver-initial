import java.sql.SQLException;

public class ZAA {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Load.loadInit();
        UserInterface ui = new UserInterface();
        ui.chooseEntityMenu();
    }
}
