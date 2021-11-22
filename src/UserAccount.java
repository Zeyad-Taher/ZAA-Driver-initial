import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class UserAccount extends Account implements Observer{
    String username;
    String password;
    ArrayList<Notification> notifications;
    //    static Connection con = Database.getInstance();
    public UserAccount(String username,  String password) {
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
//        String query = "insert into zaa.user(username, password) values(?, ?)";
//        PreparedStatement ps = null;
//        try {
//            ps = con.prepareStatement(query);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ps.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }


    public Ride requestRide(Area source, Area destination) {
        Ride ride = new Ride(source, destination, this);
        return ride;
    }

    public void acceptOffer(Offer offer) {
        offer.accept();
    }

    @Override
    public void update(Object offer) {
        Offer o = (Offer) offer;
        java.util.Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        notifications.add(new Notification(offer.toString(), now, o));
    }

    public static void main(String[] args) throws SQLException {
        UserAccount user = new UserAccount("Ammar", "123");
        DriverAccount driver = new DriverAccount("Omar", "123");
        Area haram = new Area("Haram");
        Area dokki = new Area("Dokki");
        driver.addFavArea(haram);
        Ride userRide = user.requestRide(haram, dokki);
        System.out.println(driver.getNotifications());
        Offer offer = driver.makeOffer(12, userRide);
        System.out.println(user.getNotifications());
        user.acceptOffer(offer);
        System.out.println(driver.getNotifications());

//        driver.makeOffer(10, userRide);
//        Connection con = Database.getInstance();
//        UserAccount user = new UserAccount("zeyad", "123");
//        ResultSet rs = con.createStatement().executeQuery("select * from zaa.user");
//        while (rs.next()) {
//            System.out.println(rs.getString("username"));
//        }
    }
}
