package nhom55.hcmuaf.websocket.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dto.request.CartWebSocketRequestDTO;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.websocket.config.HttpSessionConfigurator;
import nhom55.hcmuaf.websocket.entities.CartsEntityWebSocket;

@ServerEndpoint(value = "/websocket/cart-socket", configurator = HttpSessionConfigurator.class)
public class CartUserWebsocket {

  private static final Map<Integer, List<Session>> userSession = new HashMap<>();
  private static final Map<Integer, CartsEntityWebSocket> userCart = new HashMap<>();

  @OnOpen
  public void onOpen(Session session) throws IOException {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    String userId = null;
    if (users == null) {
      userId = httpSession.getId();
    }
    userId = users.getId()+"";
    userSession.computeIfAbsent(users.getId(), k -> new ArrayList<>()).add(session);

    // sent current cart data
    // Send current cart data to the client upon connection
    CartsEntityWebSocket cart = userCart.get(users.getId());
    if (cart != null) {
      String cartData = MyUtils.convertToJson(cart.getTotal());
      session.getBasicRemote().sendText(cartData);
    }
  }

  @OnMessage
  public void handleMessage(String message, Session session) throws IOException {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    if (users == null) {
      session.close();
      return;
    }

    CartWebSocketRequestDTO requestDTO = MyUtils.convertJsonToObject(message,
        CartWebSocketRequestDTO.class);
    CartsEntityWebSocket cart = userCart.computeIfAbsent(users.getId(),
        k -> new CartsEntityWebSocket());
    cart.addCartItem(
        new CartsEntityWebSocket.CartItem(requestDTO.getProductId(), requestDTO.getQuantity()));

    String totalJson = MyUtils.convertToJson(cart.getTotal());
    for (Session s : userSession.get(users.getId())) {
      s.getBasicRemote().sendText(totalJson);
    }
  }

  @OnClose
  public void handleClose(Session session) {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    if (users == null) {

    }
  }

  @OnError
  public void handleError(Throwable t) {
    t.printStackTrace();
  }
}
