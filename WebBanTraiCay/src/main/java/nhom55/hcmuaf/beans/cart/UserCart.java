package nhom55.hcmuaf.beans.cart;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class UserCart {

  private static Map<Integer, Cart> userCart = new HashMap<>();

  public static Cart getUserCart(int userId) {
    if (userCart.containsKey(userId)) {
      return userCart.get(userId);
    } else {
      return null;
    }
  }

  public static void updateCart(int userId, HttpSession session) {
    // Cập nhật giỏ hàng trong map
//    Cart cart = null;
//    if (UserCart.getUserCart(userId) != null) {
//      CartsEntityWebSocket homeCart = MyUtils.getCart(session);
//      Cart userCart = UserCart.getUserCart(userId);
//      if (homeCart != null) {
//        cart = homeCart.mergeCarts(userCart.getData());
//      } else {
//        cart = userCart;
//      }
//    } else {
//      cart = MyUtils.getCart(session);
  }
//    userCart.put(userId, cart);
//    MyUtils.storeCart(session, cart);
}
