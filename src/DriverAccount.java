import java.util.ArrayList;

public class DriverAccount extends Account implements Observer{
    String username;
    ArrayList<Area> favAreas;

    public DriverAccount(String username) {
        this.username = username;
        favAreas = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Area> getFavAreas() {
        return favAreas;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void addFavArea(Area area) {
        if(favAreas.add(area)) {
            area.registerObserver(this);
        }
    }

    public void makeOffer(long price, Ride ride) {
        ride.addOffer(new Offer(price, ride, this));
    }

    @Override
    public void update() {
        System.out.println("New ride to one of ur favs");
    }
}
