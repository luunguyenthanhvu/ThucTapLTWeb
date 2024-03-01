package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class RegisterBean implements Serializable {
    private String username;
    private String password;
    private String hash;
    private String email;
    private String address;
    private String phoneNumber;

    public RegisterBean(String username, String password, String hash, String email, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.hash = hash;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public RegisterBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
