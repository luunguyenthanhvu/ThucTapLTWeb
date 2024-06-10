package nhom55.hcmuaf.log;

import java.time.LocalDateTime;
import nhom55.hcmuaf.enums.LogLevels;

public class Log<T> {
  private int id;
  private LogLevels level;
  private String note;
  private String preValue;
  private String curValue;
  private LocalDateTime createAt;
  private LocalDateTime updateAt;
  private RequestInfo requestInfo;


  public Log() {

  }

  public Log(int id, LogLevels level, String preValue, String curValue, LocalDateTime createAt,
      LocalDateTime updateAt, RequestInfo requestInfo) {
    this.id = id;
    this.level = level;
    this.preValue = preValue;
    this.curValue = curValue;
    this.createAt = createAt;
    this.updateAt = updateAt;
    this.requestInfo = requestInfo;
  }

  public Log(String preValue, String curValue,
     LocalDateTime updateAt) {
    this.preValue = preValue;
    this.curValue = curValue;
    this.createAt = LocalDateTime.now();
    this.updateAt = updateAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LogLevels getLevel() {
    return level;
  }

  public void setLevel(LogLevels level) {
    this.level = level;
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

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }


  public void setRequestInfo(RequestInfo requestInfo) {
    this.requestInfo = requestInfo;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
