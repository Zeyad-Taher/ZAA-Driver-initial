import java.util.ArrayList;

public class Ride implements Subject{
    Area source;
    Area destination;
    UserAccount user;
    ArrayList<Offer> offers;

    public Ride(Area source, Area destination, UserAccount user){
        this.source = source;
        this.destination = destination;
        this.user = user;
        offers = new ArrayList<>();
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {
        user.update();
    }
}
