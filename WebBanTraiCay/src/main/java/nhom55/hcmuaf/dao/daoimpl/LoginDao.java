package nhom55.hcmuaf.dao.daoimpl;

import nhom55.hcmuaf.beans.LoginBean;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.database.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class LoginDao {
    public String authorizeLogin(LoginBean loginBean) {
        String email = loginBean.getEmail ();
        String password = loginBean.getPassword ();
        String hashPassword = loginBean.getHashPassword ();

        // check if exist
        List<Users> users = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT username,password,email,phoneNumber,address,status,img,dateOfBirth,sexual,role FROM Users WHERE email = :email AND password = :password")
                        .bind("email", email)
                        .bind ("password", hashPassword)
                        .mapToBean(Users.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        if (!users.isEmpty()) {
            Users user = users.get(0);
            if (user.getStatus() == 0) {
                return "ACCOUNT INACTIVE";
            }
            if (user.getEmail().equals(email) && user.getPassword ().equals(hashPassword)) {
                if (user.getRole() == 1) {
                    return "ADMIN";
                } else if (user.getRole() == 2) {
                    return "USER";
                } else if (user.getRole() == 3) {
                    return "MANAGER";
                }
            }
        }
        return "FAIL";
    }
    public String authorizeLoginGoogle(String email) {

        // check if exist
        List<Users> users = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT id,username,email,status,img,role FROM Users WHERE email = :email")
                        .bind("email", email)
                        .mapToBean(Users.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        if (!users.isEmpty()) {
            Users user = users.get(0);
            if (user.getStatus() == 0) {
                return "ACCOUNT INACTIVE";
            }
            if (user.getEmail().equals(email)) {
                if (user.getRole() == 1) {
                    return "ADMIN";
                } else if (user.getRole() == 2) {
                    return "USER";
                } else if (user.getRole() == 3) {
                    return "MANAGER";
                }
            }
        }
        return "FAIL";
    }
}
