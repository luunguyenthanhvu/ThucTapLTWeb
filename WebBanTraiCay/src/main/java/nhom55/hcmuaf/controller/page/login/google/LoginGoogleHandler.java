package nhom55.hcmuaf.controller.page.login.google;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.cart.UserCart;
import nhom55.hcmuaf.dao.daoimpl.LoginDao;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;


@WebServlet(name = "LoginGoogleHandler", value = "/LoginGoogleHandler")
public class LoginGoogleHandler extends HttpServlet {

  private LoginDao loginDao = new LoginDao();

  public LoginGoogleHandler() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String code = request.getParameter("code");
    if (code == null || code.isEmpty()) {
      RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
      dis.forward(request, response);
    } else {
      request.removeAttribute("result");
      String accessToken = GoogleUtils.getToken(code);
      GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
      Users userDB = UserService.getInstance().getUserByEmail(googlePojo.getEmail());
      if(userDB == null) {
        String username = googlePojo.getName();
        if(username == null) {
          username = extractUsernameFromEmail(googlePojo.getEmail());
        }
        UserService.getInstance().addNewGoogleUser(username, googlePojo.getEmail(), googlePojo.getPicture());
      }
      Users user = UserService.getInstance().getUserByEmail(googlePojo.getEmail());
      MyUtils.storeLoginedUser(request.getSession(), user);
      UserCart.updateCart(user.getId(), request.getSession());
      HttpSession session = request.getSession();
      String result = loginDao.authorizeLoginGoogle(googlePojo.getEmail());
      if (result.equals("ADMIN")) {
        // redirect to admin page
        MyUtils.setUserRole(session, "Admin");
        response.sendRedirect(request.getContextPath() + "/admin/profile");
      } else if (result.equals("USER")) {
        // redirect to home
        MyUtils.setUserRole(session, "User");
        response.sendRedirect(request.getContextPath() + "/page/home");
      }  else if (result.equals("Manager")) {
        // redirect to home
        MyUtils.setUserRole(session, "MANAGER");
        response.sendRedirect(request.getContextPath() + "/admin/profile");
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }
  private static String extractUsernameFromEmail(String email) {
    // Split the email address by '@'
    String[] parts = email.split("@");

    // Take the first part as the username
    if (parts.length > 0) {
      return parts[0];
    } else {
      // Handle the case when the email format is not as expected
      System.err.println("Invalid email format");
      return null;
    }
  }

}
