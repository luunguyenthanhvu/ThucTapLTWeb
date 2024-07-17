package nhom55.hcmuaf.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import nhom55.hcmuaf.websocket.entities.CartsEntityWebSocket;

public class UserCart {

  private static final Map<Integer, CartsEntityWebSocket> userCarts = new ConcurrentHashMap<>();

  public static CartsEntityWebSocket getUserCart(int userId) {
    return userCarts.get(userId);
  }

  public static void addUserCart(int userId, CartsEntityWebSocket cart) {
    userCarts.put(userId, cart);
  }

  public static void removeUserCart(int userId) {
    userCarts.remove(userId);
  }

}
