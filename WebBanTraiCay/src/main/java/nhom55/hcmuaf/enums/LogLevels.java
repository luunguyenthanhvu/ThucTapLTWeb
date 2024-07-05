package nhom55.hcmuaf.enums;

public enum LogLevels {
  INFO("INFO"),
  ALERT("ALERT"),
  WARNING("WARNING"),
  DANGER("DANGER");

  private final String level;

  LogLevels(String level) {
    this.level = level;
  }

  public String getLevel() {
    return level;
  }
}
