import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

enum EventType {
    PRICE_OFFER, ACCEPT_OFFER
}

public class RideEvent {
    String name;
    String description;
    Timestamp time;
    HashMap<String, String> eventProps;

    public RideEvent(String capName, long price) {
        name = "New price offer";
        initEvent();
        eventProps.put("Price", ""+price);
        eventProps.put("Captain Name", capName);
        description += ", Event Type: New price offer" + ", Captain Name: " + capName + ", Price: " + price;
    }

    public RideEvent(String userName) {
        name = "User accepted offer";
        initEvent();
        eventProps.put("User Name", userName);
        description += ", User Name: " + userName;
    }

    private void initEvent() {
        eventProps = new HashMap<>();
        Date date = new Date();
        time = new Timestamp(date.getTime());
        description = "Event Name: " + name + ", Time: " + time;
    }

    @Override
    public String toString() {
        return description;
    }
}
