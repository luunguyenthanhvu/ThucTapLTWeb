package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class Providers implements Serializable {
  private int id;
  private String providerName;
  private String addressOfProvider;

  public Providers() {
  }

  public Providers(int id, String providerName, String addressOfProvider) {
    this.id = id;
    this.providerName = providerName;
    this.addressOfProvider = addressOfProvider;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public String getAddressOfProvider() {
    return addressOfProvider;
  }

  public void setAddressOfProvider(String addressOfProvider) {
    this.addressOfProvider = addressOfProvider;
  }
}
