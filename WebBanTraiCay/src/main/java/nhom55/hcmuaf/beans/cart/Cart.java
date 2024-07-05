package nhom55.hcmuaf.beans.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.services.ProductService;

public class Cart {

  private Map<Integer, CartProduct> data = new HashMap<>();

  public String add(int add) {
    return add(add, 1);
  }

  public Map<Integer, CartProduct> getData() {
    return data;
  }

  public String add(int add, int quantity) {
//    Products p = ProductService.getInstance().getById(add);
//    if (p == null) {
//      return "Product does not exist";
//    }
//    CartProduct cartProduct = null;
//    if (data.containsKey(add)) {
//      cartProduct = data.get(add);
//      if (cartProduct.getQuantity() + quantity <= cartProduct.getProducts().getWeight()) {
//        cartProduct.increQuantity(quantity);
//      } else {
//        return "Out of quantity";
//      }
//    } else {
//      cartProduct = new CartProduct(quantity, p);
//    }
//    data.put(add, cartProduct);
    return "Success";
  }

  public String remove(int id) {
    return remove(id, 1);
  }

  public String remove(int id, int quantity) {
    String result = "";
    Products p = ProductService.getInstance().getById(id);
    if (p == null) {
      return "Product does not exist";
    }
    CartProduct cartProduct = null;
    if (data.containsKey(id)) {
      cartProduct = data.get(id);
      if (cartProduct.getQuantity() == 1) {
        data.remove(id);
        result = "Removed from cart";

      } else {
        cartProduct.decreQuantity(quantity);
        data.put(id, cartProduct);
        result = "Success";
      }
    } else {
      return "Product does not exist";
    }
    return result;
  }

  public void deleteProduct(int id) {
    if (data.containsKey(id)) {
      data.remove(id);
    }
  }

  public int getTotal() {
    return data.size();
  }

  public Collection<CartProduct> getCartProduct() {
    return data.values();
  }

  public List<CartProduct> getSelectedProducts(List<String> selectedProductIds) {
    List<CartProduct> result = new ArrayList<>();
    for (String id : selectedProductIds) {
      if (data.containsKey(Integer.parseInt(id))) {
        result.add(data.get(Integer.parseInt(id)));
      }
    }
    return result;
  }

  public Cart mergeCarts(Map<Integer, CartProduct> otherData) {
    Cart mergedCart = new Cart();

    // Sao chép dữ liệu từ Cart hiện tại vào mergedCart
    for (Map.Entry<Integer, CartProduct> entry : data.entrySet()) {
      int productId = entry.getKey();
      CartProduct currentCartProduct = entry.getValue();
      mergedCart.getData().put(productId,
              new CartProduct(currentCartProduct.getQuantity(), currentCartProduct.getProducts()));
    }

    // Merge dữ liệu từ Cart khác vào mergedCart
    for (Map.Entry<Integer, CartProduct> entry : otherData.entrySet()) {
      int productId = entry.getKey();
      CartProduct otherCartProduct = entry.getValue();

      // Kiểm tra xem trong mergedCart đã có sản phẩm với cùng ID chưa
      if (mergedCart.getData().containsKey(productId)) {
        CartProduct currentCartProduct = mergedCart.getData().get(productId);

        // Tăng số lượng trong mergedCart
        currentCartProduct.increQuantity(otherCartProduct.getQuantity());
      } else {
        // Nếu chưa có, thêm CartProduct mới vào mergedCart
        mergedCart.getData().put(productId, otherCartProduct);
      }
    }

    return mergedCart;
  }


}
