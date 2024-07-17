package nhom55.hcmuaf.websocket.observer.impl;

import java.io.IOException;
import javax.websocket.Session;
import nhom55.hcmuaf.websocket.observer.Observer;

public class WebSocketObserver implements Observer {

  private final Session session;

  public WebSocketObserver(Session session) {
    this.session = session;
  }

  @Override
  public void update(String message) {
    try {
      session.getBasicRemote().sendText(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
