package nhom55.hcmuaf.controller.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userProfile", value = "/page/user/user-profile")
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = MyUtils.getLoginedUser(session);

        List<Users> listUser= UserService.getInstance().showInfoUser();
        for(Users u: listUser) {
            if(u.getId() == user.getId()) {
                user =u;
                break;
            }
        }
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/user/user-profile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
