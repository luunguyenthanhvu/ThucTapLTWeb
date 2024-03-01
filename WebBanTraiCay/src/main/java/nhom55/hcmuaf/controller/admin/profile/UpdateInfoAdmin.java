package nhom55.hcmuaf.controller.admin.profile;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.UserService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
        1024 * 1024 * 100)
@WebServlet(name = "UpdateInfoAdmin", value = "/admin/profile/update-profile")
public class UpdateInfoAdmin extends HttpServlet {


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
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/admin/edit-admin-profile.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNum");
        String dateOfBirth = request.getParameter("dob");
        String gender = request.getParameter("gender");
        Part filePart = request.getPart("avatar");
        String filePartString = filePart.getSubmittedFileName();


        if (checkValidate(request, response, username, email, address, phoneNumber, dateOfBirth, filePartString, gender)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date myBirthDay = null;
            try {
                myBirthDay = dateFormat.parse(dateOfBirth);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String imgUser = "";

            String fileName = filePart.getSubmittedFileName();
            ServletContext servletContext = getServletContext();

            File root = new File(servletContext.getRealPath("/") + "/data");

            // create a new folder if not exists
            if (!root.exists()) {
                root.mkdirs();
            }
            // save img to data folder
            for (Part part : request.getParts()) {
                part.write(root.getAbsolutePath() + '/' + fileName);
                imgUser = "/data/" + fileName;
            }

            String result = UserService.getInstance().updateProfileWithImage(admin.getId(), username, email, address, phoneNumber, myBirthDay, imgUser, gender);

            // Nếu email được thay đổi
            if (!admin.getEmail().equals(email)) {
                if (result.equals("SUCCESS")) {
                    request.setAttribute("result", "Đổi email thành công. Vui lòng đăng nhập lại!");
                    // xoa session hien tai
                    MyUtils.removeLoginedUser(session);
                    MyUtils.removeCart(session);
                    RequestDispatcher dispatcher = this.getServletContext()
                            .getRequestDispatcher("/WEB-INF/login/login.jsp");
                    dispatcher.forward(request, response);
                }
            }
            request.setAttribute("result", "Cập nhật thành công");
            response.sendRedirect(request.getContextPath() + "/admin/profile");
            // không checkValidate
        } else {
            List<Users> listUser= UserService.getInstance().showInfoUser();
            for(Users u: listUser) {
                if(u.getId() == admin.getId()) {
                    admin =u;
                    break;
                }
            }
            request.setAttribute("admin", admin);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/admin/edit-admin-profile.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * check validate for form input
     *
     * @param userName
     * @param email
     * @param address
     * @param phoneNumber
     * @param dateOfBirth
     * @return
     */

    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                         String userName, String email, String address,
                                         String phoneNumber, String dateOfBirth, String img, String gender) {

        String checkName = UserValidator.validateTenNguoiDung(userName);
        String checkEmail = UserValidator.validateEmail(email);
        String checkAddress = UserValidator.validateDiaChi(address);
        String checkPhoneNumber = UserValidator.validateSDT(phoneNumber);
        String checkDateOfBirth = UserValidator.validateNgaySinh(dateOfBirth);
        String checkImg = UserValidator.validateUpFileAnh(img);
        String checkGender = UserValidator.validateGioiTinh(gender);
        // count for validate
        int count = 0;

        if (!checkName.isEmpty()) {
            count++;
            request.setAttribute("error_name", checkName);
        } else {
            request.setAttribute("name_user", userName);
        }

        if (!checkEmail.isEmpty()) {
            count++;
            request.setAttribute("error_email", checkEmail);
        } else {
            request.setAttribute("emailUser", email);
        }

        if (!checkAddress.isEmpty()) {
            count++;
            request.setAttribute("error_address", checkAddress);
        } else {
            request.setAttribute("address_user", address);
        }

        if (!checkPhoneNumber.isEmpty()) {
            count++;
            request.setAttribute("error_phoneNumber", checkPhoneNumber);
        } else {
            request.setAttribute("phoneNumber_user", phoneNumber);
        }

        if (!checkDateOfBirth.isEmpty()) {
            count++;
            request.setAttribute("error_dob", checkDateOfBirth);
        } else {
            request.setAttribute("dateOfBirth_user", dateOfBirth);
        }

        if (!checkImg.isEmpty()) {
            count++;
            request.setAttribute("file_anh_error", checkImg);
        }

        if (!checkGender.isEmpty()) {
            count++;
            request.setAttribute("error_gender", checkGender);
        } else {
            request.setAttribute("gender_user", gender);
        }

        if (count > 0) {
            return false;
        }
        return true;
    }
}
