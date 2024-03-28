package nhom55.hcmuaf.controller.admin.profile;


import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProfile", value = "/admin/profile")
public class AdminProfile extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Users admin = MyUtils.getLoginedUser(session);

    List<Users> listUser= UserService.getInstance().showInfoUser();
    for(Users u: listUser) {
      if(u.getId() == admin.getId()) {
        admin =u;
        break;
      }
    }

    request.setAttribute("admin", admin);
    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/admin/admin-profile.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
