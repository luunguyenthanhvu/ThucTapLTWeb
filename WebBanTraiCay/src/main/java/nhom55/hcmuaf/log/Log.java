package nhom55.hcmuaf.log;

import java.time.LocalDateTime;
import nhom55.hcmuaf.enums.LogLevels;

public class Log<T> {
  private int id;
  private String ip;
  private LogLevels level;
  private String address;
  private String national;
  private String note;
  private String preValue;
  private String currentValue;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  public Log() {}

  public Log(int id, String ip, LogLevels level, String address, String national, String note, String preValue, String currentValue, LocalDateTime createAt, LocalDateTime updateAt) {
    this.id = id;
    this.ip = ip;
    this.level = level;
    this.address = address;
    this.national = national;
    this.note = note;
    this.preValue = preValue;
    this.currentValue = currentValue;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public Log(String preValue, String currentValue, LocalDateTime updateAt) {
    this.preValue = preValue;
    this.currentValue = currentValue;
    this.createAt = LocalDateTime.now();
    this.updateAt = updateAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public LogLevels getLevel() {
    return level;
  }

  public void setLevel(LogLevels level) {
    this.level = level;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getNational() {
    return national;
  }

  public void setNational(String national) {
    this.national = national;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getPreValue() {
    return preValue;
  }

  public void setPreValue(String preValue) {
    this.preValue = preValue;
  }

  public String getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(String currentValue) {
    this.currentValue = currentValue;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public LocalDateTime getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(LocalDateTime updateAt) {
    this.updateAt = updateAt;
  }
}
