package nhom55.hcmuaf.enums;

public enum ShipmentTransactionEnums {
  INCREASE("increase"),

  DECREASE("decrease");

  private final String text;

  ShipmentTransactionEnums(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
