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
import nhom55.hcmuaf.dto.response.ListProductShopResponseDTO;
import nhom55.hcmuaf.my_handle_exception.MyHandleException;
import nhom55.hcmuaf.services_remaster.ProductService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.websocket.config.HttpSessionConfigurator;
import nhom55.hcmuaf.websocket.entities.CartsEntityWebSocket;

@ServerEndpoint(value = "/websocket/cart-socket", configurator = HttpSessionConfigurator.class)
public class CartUserWebsocket {

  private static final Map<String, List<Session>> userSession = new HashMap<>();
  private static final Map<String, CartsEntityWebSocket> userCart = new HashMap<>();
  private ProductService productService;

  @OnOpen
  public void onOpen(Session session) throws IOException {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    String userId = users == null ? httpSession.getId() : String.valueOf(users.getId());

    userSession.computeIfAbsent(userId, k -> new ArrayList<>()).add(session);

    // Send current cart data
    CartsEntityWebSocket cart = userCart.get(userId);
    if (cart != null) {
      String cartData = MyUtils.convertToJson(cart);
      sendMessage(session, cartData);
    }
  }

  @OnMessage
  public void handleMessage(String message, Session session) throws IOException, MyHandleException {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    String userId = users == null ? httpSession.getId() : String.valueOf(users.getId());

    CartWebSocketRequestDTO requestDTO = MyUtils.convertJsonToObject(message,
        CartWebSocketRequestDTO.class);
    CartsEntityWebSocket cart = userCart.computeIfAbsent(userId, k -> new CartsEntityWebSocket());

    try {
      productService = new ProductService();
      if ("add".equals(requestDTO.getAction())) {
        productService.begin();
        ListProductShopResponseDTO data = productService.findProductShopResponseById(
            requestDTO.getId());
        cart.addToCart(data, requestDTO);
        productService.save();
      } else if ("delete".equals(requestDTO.getAction())) {
        cart.deleteItem(requestDTO.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new MyHandleException("error", 500);
    }
    MyUtils.storeCart(httpSession, cart);
    String totalJson = MyUtils.convertToJson(cart);
    for (Session s : userSession.get(userId)) {
      sendMessage(s, totalJson);
    }
  }

  @OnClose
  public void handleClose(Session session) {
    HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
    Users users = MyUtils.getLoginedUser(httpSession);
    String userId = users == null ? httpSession.getId() : String.valueOf(users.getId());

    CartsEntityWebSocket cart = userCart.computeIfAbsent(userId, k -> new CartsEntityWebSocket());
    MyUtils.storeCart(httpSession, cart);

    List<Session> sessions = userSession.get(userId);
    if (sessions != null) {
      sessions.remove(session);
      if (sessions.isEmpty()) {
        userSession.remove(userId);
      }
    }
  }

  @OnError
  public void handleError(Throwable t) {
    t.printStackTrace();
  }

  private void sendMessage(Session session, String message) throws IOException {
    if (session.isOpen()) {
      session.getBasicRemote().sendText(message);
    }
  }

  public void transferCart(HttpSession session) {
    Users users = MyUtils.getLoginedUser(session);
    if (users != null) {
      String tempUserId = session.getId();
      String userId = String.valueOf(users.getId());

      CartsEntityWebSocket tempCart = userCart.remove(tempUserId);
      CartsEntityWebSocket userCartEntity = userCart.computeIfAbsent(userId,
          k -> new CartsEntityWebSocket());

      if (tempCart != null) {
        for (CartsEntityWebSocket.CartItem item : tempCart.getCartItemList()) {
          userCartEntity.addCartItem(item);
        }
        MyUtils.storeCart(session, userCartEntity);
      }

      List<Session> tempSessions = userSession.remove(tempUserId);
      if (tempSessions != null) {
        for (Session tempSession : tempSessions) {
          userSession.computeIfAbsent(userId, k -> new ArrayList<>()).add(tempSession);
        }
      }
    }
  }
}
