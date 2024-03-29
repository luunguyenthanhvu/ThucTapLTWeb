package nhom55.hcmuaf.enums;

public enum LogLevels {
  INFO("info"),
  ALERT("alert"),
  WARNING("warning"),
  DANGER("danger");

  private final String level;

  LogLevels(String level) {
    this.level = level;
  }

  public String getLevel() {
    return level;
  }
}
