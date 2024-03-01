package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.DeleteProductServiceForAdmin;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/admin/user/delete-user")

public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users loginedUser = MyUtils.getLoginedUser(session);

        int id = Integer.valueOf(request.getParameter("id"));
        if(loginedUser.getId()==id) {
            UserService.getInstance().deleteUser(id);
            MyUtils.removeLoginedUser(session);
            response.sendRedirect(request.getContextPath() + "/login");
        }
        UserService.getInstance().deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/userList");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
