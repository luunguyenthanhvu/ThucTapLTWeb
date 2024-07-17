package nhom55.hcmuaf.websocket.observer;

public interface Subject {

  void addObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers(String message);
}
