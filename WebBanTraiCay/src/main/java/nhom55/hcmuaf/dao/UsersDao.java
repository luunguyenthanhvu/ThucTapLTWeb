package nhom55.hcmuaf.dao;

import java.time.LocalDateTime;
import nhom55.hcmuaf.beans.Users;

import java.util.Date;
import java.util.List;
import nhom55.hcmuaf.beans.Users;

public interface UsersDao {

  Users getUserByEmail(String email);

  String addNewUser(String username, String password, String hash, String email, String phoneNumber,
                    String address);

  String updateUserStatus(String email, String hash);

  String updateNewPassWord(String email, String password);


  String addNewUserOfAdmin(String username, String password, String hash, String email,
                           String phoneNumber, String address, Date dob, String gioiTinh, String img, int quyenHan);

  List<Users> showInfoUser();

  Users getUserById(int userId);

  boolean checkPassUser(int userId, String password);

  void updateProfile(int userId, String newUserName, String newEmail, String newAddress,
                     String newPhoneNumber, Date newDateOfBirth, String sexual, int status, int role);

  String updateProfileWithImage(int userId, String newUserName, String newEmail, String newAddress,
                                String newPhoneNumber, Date newDateOfBirth, String img, String sexual);

  String updatePassWordUser(int userId, String password);

  int countResultSearchingUser(String txtSearch);

  List<Users> search(String search, int index, int sizePage);

  List<Users> searchFilter(String sortBy, String order, String search, int index, int sizePage);

  List<Users> sortByFilter(int index, int quantityDefault, String sortBy, String order);

  int countTotalRowUserInDatabase();

  List<Users> get5UsersForEachPage(int index, int quantityDefault);

  void deleteUser(int id);

  int addNewGoogleUser(String username, String email, String img);

  String updateTimeStampUser(String email);

}


