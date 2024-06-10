package nhom55.hcmuaf.log;

public class RequestInfo {
  private String ip;
  private String address;
  private String nation;

  public RequestInfo(String ip, String address, String nation) {
    this.ip = ip;
    this.address = address;
    this.nation = nation;
  }

  public RequestInfo() {
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }
}
