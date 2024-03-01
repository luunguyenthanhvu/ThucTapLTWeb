package nhom55.hcmuaf.beans;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private String email;
    private String password;
    private String hashPassword;

    public LoginBean(String email, String password, String hashPassword) {
        this.email = email;
        this.password = password;
        this.hashPassword = hashPassword;
    }

    public LoginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}
