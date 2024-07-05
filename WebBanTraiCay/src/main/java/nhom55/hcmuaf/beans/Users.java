package nhom55.hcmuaf.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import nhom55.hcmuaf.log.IModel;
import nhom55.hcmuaf.log.Log;

public class Users<T> extends Log<Users> implements Serializable, IModel {

  private int id;
  private String username;
  private String password;
  private String hash;
  private String email;
  private String address;
  private String phoneNumber;
  private int status;
  private String img;
  private LocalDate dateOfBirth;
  private String sexual;
  private int role;
  private LocalDateTime creationTime;

  public Users(String username, String password, String hash, String email, String address,
      String phoneNumber) {
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public Users(int id, String username, String password, String hash, String email, String address,
      String phoneNumber, int status, String img, LocalDate dateOfBirth, String sexual, int role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.img = img;
    this.dateOfBirth = dateOfBirth;
    this.sexual = sexual;
    this.role = role;
  }

  public Users(int id, String username, String password, String hash, String email, String address,
      String phoneNumber, int status, String img, int role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.img = img;
    this.role = role;
  }

  public Users(String username, String password, String email, String address, String phoneNum,
      int status, String img, LocalDate date, String sexual, int role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNum;
    this.status = status;
    this.img = img;
    this.dateOfBirth = date;
    this.sexual = sexual;
    this.role = role;
  }

  public Users(int id, String username, String email, String address, String phoneNumber,
      int status, String img, LocalDate dateOfBirth, String sexual, int role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.img = img;
    this.dateOfBirth = dateOfBirth;
    this.sexual = sexual;
    this.role = role;
  }

  public Users(String username, String password, String hash, String email, String address,
      String phoneNum, int status) {
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNum;
    this.status = status;
  }

  public Users(int id, String username, String email, int status, String img, int role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.status = status;
    this.img = img;
    this.role = role;
  }

  public Users(String username, String email, String address, String phoneNumber, int status) {
    this.username = username;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.creationTime = LocalDateTime.now();
  }

  public Users(String email, String hash, int status) {
    this.hash = hash;
    this.email = email;
    this.status = status;
  }

  public Users(int id, String password) {
    this.id = id;
    this.password = password;
  }

  public Users() {
  }

  public Users(int id, String username, String email, String address, String phoneNumber,
      int status, LocalDate dateOfBirth, String gender, int role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.role = role;
    this.dateOfBirth = dateOfBirth;
    this.sexual = gender;
  }

  public Users(int id, String username, String password, String hash, String email, String address,
      String phoneNumber, int status, String img, LocalDate dateOfBirth, String sexual, int role,
      LocalDateTime creationTime) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.img = img;
    this.dateOfBirth = dateOfBirth;
    this.sexual = sexual;
    this.role = role;
    this.creationTime = creationTime;
  }

  public Users(String username, String password, String hash, String email, String address,
      String phoneNumber, String address1, int i, LocalDateTime now) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.hash = hash;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.img = img;
    this.dateOfBirth = dateOfBirth;
    this.sexual = sexual;
    this.role = role;
    this.creationTime = creationTime;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public void setPhoneNumber(String phoneNum) {
    this.phoneNumber = phoneNum;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate date) {
    this.dateOfBirth = date;
  }

  public String getSexual() {
    return sexual;
  }

  public void setSexual(String sexual) {
    this.sexual = sexual;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Users{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", hash='" + hash + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", status=" + status +
        ", img='" + img + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", sexual='" + sexual + '\'' +
        ", role=" + role +
        '}';
  }

  @Override
  public String getTable() {
    return "Users";
  }

  @Override
  public String getBeforeData() {
    return super.getPreValue();
  }

  @Override
  public String GetAfterData() {
    return super.getCurrentValue();
  }
}
