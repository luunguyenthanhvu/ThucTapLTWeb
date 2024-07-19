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
      cartItemList.stream()
          .filter(i -> i.getId().equals(cartItem.getId()))
          .findFirst()
          .ifPresent(i -> {
            int newQuantity = i.getQuantity() + cartItem.getQuantity();
            if (newQuantity == 0) {
              cartItemList.remove(i);
            } else if (newQuantity <= data.getQuantityStock()) {
              i.setQuantity(newQuantity);
            } else {
              i.setQuantity(data.getQuantityStock());
            }
          });
    } else {
      cartItemList.add(cartItem);
    }
  }

  public List<CartItem> getCartItemList() {
    return cartItemList;
  }

  public void removeCartItem(CartItem cartItem) {
    cartItemList.remove(cartItem);
  }

  public void deleteItem(Integer id) {
    cartItemList.removeIf(item -> item.getId().equals(id));
  }

  public void deleteItems(List<Integer> ids) {
    cartItemList.removeIf(item -> ids.contains(item.getId()));
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

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getImgPublicId() {
      return imgPublicId;
    }

    public void setImgPublicId(String imgPublicId) {
      this.imgPublicId = imgPublicId;
    }

    public String getProductName() {
      return productName;
    }

    public void setProductName(String productName) {
      this.productName = productName;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }
  }
}
