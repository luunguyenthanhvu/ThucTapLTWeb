package nhom55.hcmuaf.dao.daoimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.database.JDBIConnector;

public class UsersDaoImpl implements UsersDao {

  /**
   * @param email
   * @return user
   */
  @Override
  public Users getUserByEmail(String email) {
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT * FROM users WHERE email = ?")
            .bind(0, email)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );

    if (users.isEmpty()) {
      return null;
    }

    return users.get(0);
  }


  /**
   * @param username
   * @param password
   * @param hash
   * @param email
   * @param phoneNumber
   * @param address
   * @return String result
   */
  @Override
  public String addNewUser(String username, String password, String hash, String email,
      String phoneNumber, String address) {
    // add new user
    return JDBIConnector.get().withHandle(handle -> {
      handle.createUpdate(
              "INSERT INTO users (username, password, hash, email, phoneNumber, address, status, creationTime) VALUES (:username, :password, :hash, :email, :phoneNumber, :address, :status, :creationTime)")
          .bind("username", username)
          .bind("password", password)
          .bind("hash", hash)
          .bind("email", email)
          .bind("phoneNumber", phoneNumber)
          .bind("address", address)
          .bind("status", 0)
          .bind("creationTime", LocalDateTime.now())
          .execute();
      return "SUCCESS";
    });
  }
  @Override
  public int addNewGoogleUser(String username, String email, String img) {
    // check if exist
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT id,username,email,phoneNumber,address,status,img,dateOfBirth,sexual FROM users WHERE email = ?")
            .bind(0, email)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
    if (!users.isEmpty()) {
      return 0;
    }

    // add new user
     JDBIConnector.get().withHandle(handle -> {
         return handle.createUpdate(
                 "INSERT INTO users (username, email, status, img) VALUES (:username, :email, :status, :img)")
             .bind("username", username)
             .bind("email", email)
             .bind("status", 1)
             .bind("img", img)
             .execute();

    });
    return JDBIConnector.get().withHandle(handle -> {
        return  handle.createQuery("SELECT id FROM users WHERE email = :email")
                .bind("email", email)
                .mapTo(Integer.class).one();
    });
  }

  @Override
  public String updateTimeStampUser(String email) {
    // check is exit
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT email, hash, status  FROM users WHERE email = :email AND status = 0")
            .bind("email", email)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
    if (users.isEmpty()) {
      return "FAIL";
    }
    Users user = users.get(0);
    return JDBIConnector.get().withHandle(handle -> {
      handle.createUpdate("update users set creationTime = :creationTime where email = :email ")
          .bind("creationTime", LocalDateTime.now())
          .bind("email", email)
          .execute();
      return "SUCCESS";
    });
  }

  /**
   * @param email
   * @param hash
   * @return String result
   */
  @Override
  public String updateUserStatus(String email, String hash) {
    // check is exit
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT email, hash, status  FROM users WHERE email = :email AND hash = :hash AND status = 0")
            .bind("email", email)
            .bind("hash", hash)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
    if (users.isEmpty()) {
      return "FAIL";
    }
    Users user = users.get(0);
    return JDBIConnector.get().withHandle(handle -> {
      handle.createUpdate("update users set status = 1 where email = :email AND hash = :hash ")
          .bind("hash", hash)
          .bind("email", email)
          .execute();
      return "SUCCESS";
    });
  }

  /**
   * update new password for user
   *
   * @param email
   * @param password
   */
  @Override
  public String updateNewPassWord(String email, String password) {
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT email, hash, status  FROM users WHERE email = :email")
            .bind("email", email)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
    if (users.isEmpty()) {
      return "FAIL";
    }
    Users user = users.get(0);
    return JDBIConnector.get().withHandle(handle -> {
      handle.createUpdate("update users set password = :password where email = :email")
          .bind("password", password)
          .bind("email", email)
          .execute();
      return "SUCCESS";
    });
  }

  @Override
  public String addNewUserOfAdmin(String username, String password, String hash, String email,
      String phoneNumber, String address, java.util.Date dob, String gioiTinh, String img,
      int quyenHan) {
    // check if exist
    List<Users> users = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT id,username,email,phoneNumber,address,status,img,dateOfBirth,sexual FROM users WHERE email = ?")
            .bind(0, email)
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
    if (!users.isEmpty()) {
      return "FAIL";
    }

    // add new user
    return JDBIConnector.get().withHandle(handle -> {
      handle.createUpdate(
              "INSERT INTO users (username, password, hash, email, phoneNumber, address, status,img,dateOfBirth,sexual,role) VALUES (:username, :password, :hash, :email, :phoneNumber, :address, :status, :img, :dateOfBirth, :sexual, :role)")
          .bind("username", username)
          .bind("password", password)
          .bind("hash", hash)
          .bind("email", email)
          .bind("phoneNumber", phoneNumber)
          .bind("address", address)
          .bind("status", 1)
          .bind("img", img)
          .bind("sexual", gioiTinh)
          .bind("dateOfBirth", dob)
          .bind("role", quyenHan)
          .execute();
      return "SUCCESS";
    });
  }


  /**
   * show List user
   *
   * @return id , username, hash ,password, email, address, phoneNumber, dateOfBirth, img , status,
   * role
   */
  @Override
  public List<Users> showInfoUser() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT * FROM users")
            .mapToBean(Users.class)
            .stream()
            .collect(Collectors.toList())
    );
  }


  /**
   * get User show profile
   *
   * @return id , username, password, email, address, phoneNumber, dateOfBirth, img , status, role
   */
  @Override
  public Users getUserById(int userId) {
    return JDBIConnector.get().withHandle(handle ->
        handle.createQuery("SELECT * FROM users where id = :id")
            .bind("id", userId)
            .mapToBean(Users.class)
            .findOne()
            .orElse(null)
    );
  }

  @Override
  public boolean checkPassUser(int id, String password) {
    Users user = JDBIConnector.get().withHandle(handle ->
        handle.createQuery("SELECT * FROM users WHERE id = :id AND password = :password")
            .bind("id", id)
            .bind("password", password)
            .mapToBean(Users.class)
            .findOne()
            .orElse(null));
    return user != null;
  }

  /**
   * update profile: change one or more than
   *
   * @return username, email, address, phoneNumber, datOfBirth
   */
  public void updateProfile(int userId, String newUserName, String newEmail, String newAddress,
      String newPhoneNumber, java.util.Date newDateOfBirth, String newSexual, int newStatus,
      int newRole) {
    JDBIConnector.get().withHandle(handle ->
        handle.createUpdate(
                "UPDATE users SET username = :username, email = :email, address = :address, phoneNumber = :phoneNumber, dateOfBirth = :dateOfBirth, sexual = :sexual, status = :status, role = :role WHERE id = :id")
            .bind("id", userId)
            .bind("username", newUserName)
            .bind("email", newEmail)
            .bind("address", newAddress)
            .bind("phoneNumber", newPhoneNumber)
            .bind("dateOfBirth", newDateOfBirth)
            .bind("sexual", newSexual)
            .bind("status", newStatus)
            .bind("role", newRole)
            .execute()
    );
  }


  /**
   * update profile: change one or more than
   *
   * @return username, email, address, phoneNumber, datOfBirth, img
   */
  @Override
  public String updateProfileWithImage(int userId, String newUserName, String newEmail,
      String newAddress, String newPhoneNumber, java.util.Date newDateOfBirth, String img,
      String newSexual) {
    boolean updateSuccess = JDBIConnector.get().withHandle(handle -> {
      int updateResult = handle.createUpdate(
              "UPDATE users SET username = :username, email = :email, address = :address, phoneNumber = :phoneNumber, dateOfBirth = :dateOfBirth , img = :img, sexual = :sexual WHERE id = :id")
          .bind("id", userId)
          .bind("username", newUserName)
          .bind("email", newEmail)
          .bind("address", newAddress)
          .bind("phoneNumber", newPhoneNumber)
          .bind("dateOfBirth", newDateOfBirth)
          .bind("img", img)
          .bind("sexual", newSexual)
          .execute();
      return updateResult > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
    });
    return updateSuccess ? "SUCCESS" : "FAIL";
  }

  /**
   * update new password for user in user-profile
   *
   * @return id, password
   */
  public String updatePassWordUser(int id, String password) {
    Users user = JDBIConnector.get().withHandle(handle ->
        handle.createQuery("SELECT id FROM users WHERE id = :id")
            .bind("id", id)
            .mapToBean(Users.class)
            .findOne()
            .orElse(null));
    if (user == null) {
      return "FAIL"; // User not found
    }
    int rowsUpdated = JDBIConnector.get().withHandle(handle ->
        handle.createUpdate("UPDATE Users SET password = :password WHERE id = :id")
            .bind("password", password)
            .bind("id", id)
            .execute());
    if (rowsUpdated > 0) {
      return "SUCCESS";
    }
    return "FAIL";
  }

  //    Đếm số người dùng tìm được
  @Override
  public int countResultSearchingUser(String txtSearch) {
    return JDBIConnector.get().withHandle(h ->
        h.select("SELECT count(*)  FROM users where username like ?", "%" + txtSearch + "%")
            .mapTo(Integer.class)
            .one()
    );
  }

  //   tìm kiếm của trang danh sách người dùng
  @Override
  public List<Users> search(String search, int index, int sizePage) {
    List<Users> result = JDBIConnector.get().withHandle(handle -> {
      // Mở kết nối đến cơ sở dữ liệu
      handle.begin();
      try {
        // Thực hiện câu lệnh SQL với giá trị của index và sizePage thay thế trực tiếp
        List<Users> resultList = handle.createQuery(
                "with testThu as (select ROW_NUMBER() over (order by id asc) as r, id, username, hash, email, phoneNumber, address, status, img, dateOfBirth, sexual, role from users where userName LIKE ? or id = ?)\n"
                    +
                    "select * FROM testThu where r between " + (index * sizePage - 4) + " and " + (index
                    * sizePage))
            .bind(0, "%" + search + "%")
            .bind(1, search) // Bind giá trị cho tham số thứ hai
            .mapToBean(Users.class)
            .list();

        // Commit kết nối
        handle.commit();
        return resultList;
      } catch (Exception e) {
        // Xử lý ngoại lệ và rollback kết nối nếu có lỗi
        handle.rollback();
        throw e;
      }
    });
    return result;
  }

  @Override
  public List<Users> searchFilter(String sortBy, String order, String search, int index,
      int sizePage) {
    List<Users> resultList = JDBIConnector.get().withHandle(h ->
        h.createQuery("with testThu as (select ROW_NUMBER() over (order by " + sortBy + " " + order
                + ") as r, id,username, hash, email,phoneNumber,address,status,img,dateOfBirth,sexual, role from users where username like :search)\n"
                +
                "\n" +
                "select * FROM testThu where r between :startIndex and :endIndex")
            .bind("search", "%" + search + "%")
            .bind("startIndex", (index * sizePage - 4))
            .bind("endIndex", (index * sizePage))
            .mapToBean(Users.class)
            .list());

    return resultList;
  }

  //    Lấy 5 người dùng cho mỗi trang
  @Override
  public List<Users> get5UsersForEachPage(int index, int quantityDefault) {
    List<Users> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    result = JDBIConnector.get().withHandle(h ->
        h.createQuery(
                "SELECT * FROM users ORDER BY id ASC LIMIT :start, :quantityDefault")
            .bind("start", start)
            .bind("quantityDefault", quantityDefault)
            .mapToBean(Users.class)
            .list()
    );

    return result;
  }

  //    Đếm Số dòng record của tất cả sản phẩm trong database
  @Override
  public int countTotalRowUserInDatabase() {
    return JDBIConnector.get().withHandle(h ->
        h.createQuery("SELECT COUNT(id) FROM users").mapTo(Integer.class).one()
    );
  }

  //    Filter
//    Sắp xếp theo điều kiện filter (option: id, tên, ngày sinh, vai trò, filter:asc)
  @Override
  public List<Users> sortByFilter(int index, int quantityDefault, String sortBy, String order) {
    List<Users> result = new ArrayList<>();
    int start = (index - 1) * quantityDefault;

    String orderByClause = "";
    switch (sortBy) {
      case "id":
      case "username":
      case "role":

        orderByClause = String.format("ORDER BY %s %s", sortBy, order);
        break;

    }

    String query = String.format("SELECT * FROM users %s LIMIT :start, :quantityDefault",
        orderByClause);

    result = JDBIConnector.get().withHandle(h ->
        h.createQuery(query)
            .bind("start", start)
            .bind("quantityDefault", quantityDefault)
            .mapToBean(Users.class)
            .list()
    );

    return result;
  }

  @Override
  public void deleteUser(int id) {
    JDBIConnector.get().withHandle(h -> {
      return h.createUpdate("DELETE from users where id = :id")
          .bind("id", id)
          .execute();
    });
  }
}
