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
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
        1024 * 1024 * 100)
@WebServlet(name = "updateInfoUser", value = "/page/user/update-info")
public class UpdateInfoUser extends HttpServlet {
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
        RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/user/chinh-sua-thong-tin-user.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = MyUtils.getLoginedUser(session);



//        Thông tin của người dùng sau khi muốn chỉnh sửa
        String username = request.getParameter("ten_nguoi_dung");
        String email = request.getParameter("email_nguoi_dung");
        String gender = request.getParameter("gender");
        String address = request.getParameter("dia_chi_nguoi_dung");
        String phoneNumber = request.getParameter("so_dien_thoai_nguoi_dung");
        String dateOfBirth = request.getParameter("dob");
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

            String result = UserService.getInstance().updateProfileWithImage(user.getId(), username, email, address, phoneNumber, myBirthDay, imgUser, gender);
            request.setAttribute("result", "Cập nhật thành công");

//            Ghi lại log
            AbsDAO<Users> absDAO = AbsDAO.getInstance();
            Log<Users> usersLog = new Log<>();
            RequestInfo requestInfo = new RequestInfo(request.getRemoteAddr(),"HCM", "VietNam");
            usersLog.setIp(requestInfo.getIp());
            usersLog.setLevel(LogLevels.INFO);
            usersLog.setAddress(requestInfo.getAddress());
            usersLog.setNational(requestInfo.getNation());
            usersLog.setNote("User "+user.getUsername()+" đã cập nhật thông tin cá nhân mới");
            usersLog.setPreValue(user.getUsername()+","+user.getEmail()+","+user.getSexual()+","+user.getAddress()+","+user.getAddress()+","+user.getPhoneNumber()+","+"Sinh nhật: "+user.getDateOfBirth());
            usersLog.setCurrentValue(username+","+email+","+gender+","+address+","+phoneNumber+","+dateOfBirth);
            usersLog.setCreateAt(user.getCreationTime());
            usersLog.setUpdateAt(LocalDateTime.now());
            absDAO.insert(usersLog);

            // Nếu người dùng thay đổi email
            if(!user.getEmail().equals(email)) {
                // Nếu email được thay đổi
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

            response.sendRedirect(request.getContextPath() + "/page/user/user-profile");

            // không checkValidate
        } else {
            List<Users> listUser= UserService.getInstance().showInfoUser();
            for(Users u: listUser) {
                if(u.getId() == user.getId()) {
                    user =u;
                    break;
                }
            }
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/user/chinh-sua-thong-tin-user.jsp");
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
