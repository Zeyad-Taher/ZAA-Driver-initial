import java.sql.Driver;

public class UserAccount extends Account implements Observer{
    String username;
    public UserAccount(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User name: " + username;
    }


    public Ride requestRide(Area source, Area destination) {
        Ride ride = new Ride(source, destination, this);
        source.notifyObservers();
        return ride;
    }

    @Override
    public void update(Object o) {

    }

    public static void main(String[] args) {
        UserAccount user = new UserAccount("Ammar");
        DriverAccount driver = new DriverAccount("Omar");
        Area haram = new Area("Haram");
        Area dokki = new Area("Dokki");
        driver.addFavArea(haram);
        Ride userRide = user.requestRide(haram, dokki);
        System.out.println(haram);
//        driver.makeOffer(10, userRide);
    }
}
