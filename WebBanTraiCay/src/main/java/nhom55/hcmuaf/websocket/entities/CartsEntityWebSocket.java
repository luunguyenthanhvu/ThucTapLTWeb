package nhom55.hcmuaf.websocket.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartsEntityWebSocket {

  List<CartItem> cartItemList = new ArrayList<>();

  public Integer getTotal() {
    return cartItemList.size();
  }

  public void addCartItem(CartItem cartItem) {
    if (cartItemList.contains(cartItem)) {
      cartItemList.stream().filter(i -> i.getProductId().equals(cartItem.getProductId()))
          .findFirst().ifPresent(i -> i.setQuantity(i.getQuantity() + cartItem.getQuantity()));
    } else {
      cartItemList.add(cartItem);
    }
  }

  public void removeCartItem(CartItem cartItem) {
    cartItemList.remove(cartItem);
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CartItem {

    Integer productId;
    Integer quantity;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      CartItem cartItem = (CartItem) o;
      return productId != null && productId.equals(cartItem.productId);
    }

    @Override
    public int hashCode() {
      return productId != null ? productId.hashCode() : 0;
    }
  }
}
