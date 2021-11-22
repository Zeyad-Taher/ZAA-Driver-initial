//public class Offer implements Subject, Notifiable {
//    long price;
//    Ride ride;
//    DriverAccount driver;
//
//    public Offer(long price, Ride ride, DriverAccount driver) {
//        this.price = price;
//        this.ride = ride;
//        this.driver = driver;
//    }
//
//    public long getPrice() {
//        return price;
//    }
//
//    public Ride getRide() {
//        return ride;
//    }
//
//    public DriverAccount getDriver() {
//        return driver;
//    }
//
//    public void accept(){
//        notifyObservers(this);
//    }
//
//    @Override
//    public String toString() {
//        return driver.getUsername() + " offers " + price + "LE";
//    }
//
//    @Override
//    public void registerObserver(Observer observer) {
//
//    }
//
//    @Override
//    public void removeObserver(Observer observer) {
//
//    }
//
//    @Override
//    public void notifyObservers(Object object) {
//        driver.update(object);
//    }
//}
