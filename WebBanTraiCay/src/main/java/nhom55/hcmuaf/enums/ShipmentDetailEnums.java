package nhom55.hcmuaf.enums;

public enum ShipmentDetailEnums {
  ADD_NEW_SHIPMENT("Thêm lô hàng mới"),
  OUT_DATE("Sản phẩm hết hạn"),
  ADD_MORE_PRODUCT("Thêm sản phẩm");
  private final String text;

  ShipmentDetailEnums(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
