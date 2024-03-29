package nhom55.hcmuaf.Log;

import java.time.LocalDateTime;
import nhom55.hcmuaf.enums.LogLevels;

public class Log<T> {
  private int id;
  private String ip;
  private LogLevels level;
  private String address;
  private String preValue;
  private String curValue;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;

  public Log(int id, String ip, LogLevels level, String address, String preValue, String curValue, LocalDateTime createAt, LocalDateTime updateAt) {
    this.id = id;
    this.ip = ip;
    this.level = level;
    this.address = address;
    this.preValue = preValue;
    this.curValue = curValue;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }

  public Log() {

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

  public String getPreValue() {
    return preValue;
  }

  public void setPreValue(String preValue) {
    this.preValue = preValue;
  }

  public String getCurValue() {
    return curValue;
  }

  public void setCurValue(String curValue) {
    this.curValue = curValue;
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
