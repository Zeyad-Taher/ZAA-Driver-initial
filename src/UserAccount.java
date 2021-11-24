import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class UserAccount extends Account implements Observer {
    public UserAccount(String username,String password,String mobilePhone,String email) throws SQLException, ClassNotFoundException {
        super();
        setUsername(username);
        setPassword(password);
        setMobilePhone(mobilePhone);
        setEmail(email);
        active = true;
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
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        notifications.add(new Notification(offer.toString(), now, o));
    }

    public void rateDriver(DriverAccount driver) // this function in the UserAccount class
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter your rating in numbers: ");
        Rating rating = new Rating();
        int rate = keyboard.nextInt();
        rating.setRate(rate);
        rating.setUsername(this.getUsername());
        driver.insertAndUpdateAverageRating(rating);
    }
}