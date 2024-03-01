package nhom55.hcmuaf.controller.admin.profile;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatePasswordAdmin", value = "/admin/profile/update-pass")
public class UpdatePasswordAdmin extends HttpServlet {

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

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/admin/update-admin-password.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);

        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String retypePassword = request.getParameter("retype-password");

        if (checkValidate(request, response, oldPassword, newPassword, retypePassword)) {
            // Kiểm tra mật khẩu cũ
            if (UserService.getInstance().checkPassUser(admin.getId(), MyUtils.encodePass(oldPassword))) {
                String result = UserService.getInstance().changePass(admin.getId(), newPassword);
                if (result.equals("SUCCESS")) {
                    request.setAttribute("result", "Đổi mật khẩu thành công");
                    // xoa session hien tai
                    MyUtils.removeLoginedUser(session);
                    MyUtils.removeCart(session);
                    RequestDispatcher dispatcher = this.getServletContext()
                            .getRequestDispatcher("/WEB-INF/login/login.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("result", "Đổi mật khẩu không thành công");
                    RequestDispatcher dispatcher = this.getServletContext()
                            .getRequestDispatcher("/WEB-INF/admin/update-admin-password.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("result", "Mật khẩu cũ không đúng");
                RequestDispatcher dispatcher = this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/admin/update-admin-password.jsp");
                dispatcher.forward(request, response);
            }
            // không checkValidate
        } else {
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/admin/update-admin-password.jsp");
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
