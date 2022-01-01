import java.util.ArrayList;
import java.util.Date;

public class Ride implements Subject, Notifiable{
    Area source;
    Area destination;
    UserAccount user;
    ArrayList<Offer> offers;
    Date startTime;
    Date endTime;

    public Ride(Area source, Area destination, UserAccount user) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        source.notifyObservers(this);
        offers = new ArrayList<>();
        startTime=null;
        endTime=null;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
        notifyObservers(offer);
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public void setSource(Area source) {
        this.source = source;
    }

    public void setDestination(Area destination) {
        this.destination = destination;
    }

    public Area getSource() {
        return source;
    }

    public Area getDestination() {
        return destination;
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

    public void startRide(Offer offer){
        startTime=new Date();
        System.out.println("Ride started at "+startTime);
    }

    public void endRide(Offer offer){
        endTime=new Date();
        offer.getDriver().updateBalance(offer.getDriverPrice());
        System.out.println("Ride ended at "+endTime);
    }
}
