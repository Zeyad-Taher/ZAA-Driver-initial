//import java.util.ArrayList;
//
//public class Area implements Subject {
//    String name;
//    ArrayList<Observer> observers;
//
//    public Area(String name) {
//        this.name = name;
//        observers = new ArrayList<>();
//    }
//
//    @Override
//    public void registerObserver(Observer observer) {
//        observers.add(observer);
//    }
//
//    @Override
//    public void removeObserver(Observer observer) {
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers(Object o) {
//        for(Observer observer : observers){
//            observer.update(o);
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return name;
//    }
//}
