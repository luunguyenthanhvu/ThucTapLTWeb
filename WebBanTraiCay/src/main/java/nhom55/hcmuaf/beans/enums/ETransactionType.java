package nhom55.hcmuaf.beans.enums;

public enum ETransactionType {
  INCREASE("INCREASE"), DECREASE("DECREASE");
  private final String text;

  ETransactionType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
