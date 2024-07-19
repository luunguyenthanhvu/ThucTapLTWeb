package nhom55.hcmuaf.websocket.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nhom55.hcmuaf.dto.request.CartWebSocketRequestDTO;
import nhom55.hcmuaf.dto.response.ListProductShopResponseDTO;

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
      cartItemList.stream().filter(i -> i.getId().equals(cartItem.getId()))
          .findFirst().ifPresent(i -> i.setQuantity(i.getQuantity() + cartItem.getQuantity()));
    } else {
      cartItemList.add(cartItem);
    }
  }

  public void addToCart(ListProductShopResponseDTO data, CartWebSocketRequestDTO requestDTO) {
    CartItem cartItem = new CartItem(data.getId(), data.getImgPublicId(), data.getProductName(),
        data.getPrice(), requestDTO.getQuantity());
    if (cartItemList.contains(cartItem)) {
      cartItemList.stream().filter(i -> i.getId().equals(cartItem.getId()))
          .findFirst().ifPresent(i -> {
            if (i.getQuantity() + cartItem.getQuantity() <= data.getQuantityStock()) {
              i.setQuantity(i.getQuantity() + cartItem.getQuantity());
            } else {
              i.setQuantity(data.getQuantityStock());
            }
          });
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

    Integer id;
    String imgPublicId;
    String productName;
    Double price;
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
      return id != null && id.equals(cartItem.id);
    }

    @Override
    public int hashCode() {
      return id != null ? id.hashCode() : 0;
    }
  }
}
