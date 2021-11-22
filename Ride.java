import java.util.ArrayList;

public class Ride implements Subject, Notifiable{
    Area source;
    Area destination;
    UserAccount user;
    ArrayList<Offer> offers;

    public Ride(Area source, Area destination, UserAccount user) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        source.notifyObservers(this);
        offers = new ArrayList<>();
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
        notifyObservers(offer);
    }

    public Area getSource() {
        return source;
    }

    public UserAccount getUser() {
        return user;
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(Object object) {
        user.update(object);
    }

    @Override
    public String toString() {
        return "Ride from " + source + " to " + destination;
    }
}
