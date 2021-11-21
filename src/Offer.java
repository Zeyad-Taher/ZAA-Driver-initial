public class Offer {
    long price;
    Ride ride;
    DriverAccount driver;

    public Offer(long price, Ride ride, DriverAccount driver){
        this.price = price;
        this.ride = ride;
        this.driver = driver;
    }

    public long getPrice() {
        return price;
    }

    public Ride getRide() {
        return ride;
    }

    public DriverAccount getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "";
    }
}
