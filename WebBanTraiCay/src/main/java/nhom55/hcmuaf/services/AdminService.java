package nhom55.hcmuaf.services;

import nhom55.hcmuaf.dao.UsersDao;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.log.AbsDAO;

public class AdminService extends AbsDAO {
    private static AdminService instance;
    private UsersDao usersDao;

    public AdminService() {
        usersDao = new UsersDaoImpl ();
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }
    public String updateUserStatus(String email, String hash) {
        return usersDao.updateUserStatus (email,hash);
    }

}
