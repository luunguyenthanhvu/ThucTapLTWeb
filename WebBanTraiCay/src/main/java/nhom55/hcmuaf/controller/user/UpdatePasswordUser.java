package nhom55.hcmuaf.controller.user;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "updatePasswordUser", value = "/page/user/update-pass")
public class UpdatePasswordUser extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/user/update-user-password.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    Users user = MyUtils.getLoginedUser(session);

    String oldPassword = request.getParameter("old-password");
    String newPassword = request.getParameter("new-password");
    String retypePassword = request.getParameter("retype-password");

    if (checkValidate(request, response, oldPassword, newPassword, retypePassword)) {
      // Kiểm tra mật khẩu cũ
      if (UserService.getInstance().checkPassUser(user.getId(), MyUtils.encodePass(oldPassword))) {
        String result = UserService.getInstance().changePass(user.getId(), newPassword);
        if (result.equals("SUCCESS")) {
          request.setAttribute("result", "Đổi mật khẩu thành công");
          // xoa session hien tai
          MyUtils.removeLoginedUser(session);
          MyUtils.removeCart(session);
          Log<Users> log = new Log<>();
          AbsDAO<Users> absDAO = new AbsDAO<>();
          RequestInfo requestInfo= new RequestInfo(request.getRemoteAddr(),"HCM", "VietNam");
          log.setLevel(LogLevels.INFO);
          log.setIp(requestInfo.getIp());
          log.setAddress(requestInfo.getAddress());
          log.setNational(requestInfo.getNation());
          log.setNote("Người dùng "+user.getUsername()+" đổi mật khẩu mới");
          log.setPreValue(user.getPassword());
          log.setCurrentValue(MyUtils.encodePass(newPassword));
          log.setCreateAt(user.getCreationTime());
          log.setUpdateAt(LocalDateTime.now());
          absDAO.insert(log);
          RequestDispatcher dispatcher = this.getServletContext()
                  .getRequestDispatcher("/WEB-INF/login/login.jsp");
          dispatcher.forward(request, response);
        } else {
          request.setAttribute("result", "Có lỗi xảy ra khi đổi mật khẩu");
          RequestDispatcher dispatcher = this.getServletContext()
                  .getRequestDispatcher("/WEB-INF/user/update-user-password.jsp");
          dispatcher.forward(request, response);
        }
      } else {
        request.setAttribute("result", "Mật khẩu cũ không trùng khớp");
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/user/update-user-password.jsp");
        dispatcher.forward(request, response);

      }

      // không checkValidate
    } else {
      RequestDispatcher dispatcher = this.getServletContext()
              .getRequestDispatcher("/WEB-INF/user/update-user-password.jsp");
      dispatcher.forward(request, response);
    }
  }

  /**
   * check validate for form input
   *
   * @param oldPassword
   * @param newPassword
   * @param retypePassword
   * @return
   */

  private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                       String oldPassword, String newPassword, String retypePassword) {

    String checkOldPassword = UserValidator.validateMatKhau(oldPassword);
    String checkNewPassword = UserValidator.validateMatKhau(newPassword);
    String checkOldAndNewPass = UserValidator.validateOldAndNewPass(oldPassword, newPassword);
    String checkNewAndRetypePass = UserValidator.validateNhapLaiMatKhau(newPassword, retypePassword);

    // count for validate
    int count = 0;

    if (!checkOldPassword.isEmpty()) {
      count++;
      request.setAttribute("error_oldPassword", checkOldPassword);
    } else {
      request.setAttribute("oldPass", oldPassword);
    }

    if (!checkNewPassword.isEmpty()) {
      count++;
      request.setAttribute("error_newPassword", checkNewPassword);
    } else {
      request.setAttribute("newPass", newPassword);
    }

    if (!checkOldAndNewPass.isEmpty()) {
      count++;
      request.setAttribute("error_checkOldAndNewPass", checkOldAndNewPass);
    }

    if (!checkNewAndRetypePass.isEmpty()) {
      count++;
      request.setAttribute("error_checkNewAndRetypePass", checkNewAndRetypePass);
    }

    if (count > 0) {
      return false;
    }
    return true;
  }
}
