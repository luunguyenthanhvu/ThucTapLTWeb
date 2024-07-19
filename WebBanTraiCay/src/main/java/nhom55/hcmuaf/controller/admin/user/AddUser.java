package nhom55.hcmuaf.controller.admin.user;

import nhom55.hcmuaf.beans.Role;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.RoleDAO;
import nhom55.hcmuaf.dao.daoimpl.RoleDAOImpl;
import nhom55.hcmuaf.dao.daoimpl.UsersDaoImpl;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.services.RegisterAccountServiceForAdmin;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.UserValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "AddUser", value = "/admin/user/add-user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
        1024 * 1024 * 100)
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        RoleDAO dao = new RoleDAOImpl();
        List<Role> listRole = dao.getAllRoles();
        request.setAttribute("listRole",listRole);
        request.setAttribute("admin", admin);
       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/add-user.jsp");
       dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         Users admin = MyUtils.getLoginedUser(session);
         String tenNguoiDung = request.getParameter("ten_nguoi_dung");
         String matKhau =  request.getParameter("mat_khau_nguoi_dung");
         String nhapLaiMK = request.getParameter("nhap_lai_mat_khau_nguoi_dung");
         String email = request.getParameter("email_nguoi_dung");
         String sdt = request.getParameter("sdt_nguoi_dung");
         String gioiTinh = request.getParameter("value_gioiTinh");
        System.out.println(gioiTinh);
        String ngaySinh = request.getParameter("ngay_sinh_user");
        String diaChi = request.getParameter("dia_chi_nguoi_dung");
        int role = Integer.valueOf(request.getParameter("selectedRoleID"));
        String hash = MyUtils.createHash();
        String encodePass = MyUtils.encodePass(matKhau);
        Part filePart = request.getPart("file");

        if(checkValidate(request,response,tenNguoiDung,matKhau,nhapLaiMK,email,sdt,ngaySinh,diaChi)) {
            String avatarUser ="";
            String fileName = filePart.getSubmittedFileName();
            ServletContext servletContext = getServletContext();
            File root = new File(servletContext.getRealPath("/") + "/data");
            if (!root.exists()) root.mkdirs();
            for (Part part : request.getParts()) {
                part.write(root.getAbsolutePath() + "/" + fileName);
                avatarUser ="/data/" + fileName;
            }
            String result = RegisterAccountServiceForAdmin.getInstance().RegisterUser(tenNguoiDung,hash,encodePass,email,diaChi,sdt,avatarUser,ngaySinh,gioiTinh,role);
            System.out.println(result);
            if(result.equals("SUCCESS")) {
                Log<Users> log = new Log<>();
                AbsDAO<Users> absDAO = new AbsDAO<>();
                RequestInfo requestInfo = new RequestInfo(request.getRemoteAddr(), "HCM", "VietNam");
                log.setLevel(LogLevels.ALERT);
                log.setIp(requestInfo.getIp());
                log.setAddress(requestInfo.getAddress());
                log.setNational(requestInfo.getNation());
                log.setNote("Người dùng "+admin.getUsername()+" đã thêm 1 tài khoản mới");
                Users newUser = new UsersDaoImpl().getUserByEmail(email);
                log.setPreValue("Không có dữ liệu");
                log.setCurrentValue(newUser.toString());
                log.setCreateAt(LocalDateTime.now());
                absDAO.insert(log);
                request.setAttribute("notify", "Đăng ký tài khoản thành công.");
                doGet(request,response);
            } else {
                request.setAttribute("notify", "Tài khoản email đã tồn tại, không đăng ký được");
                doGet(request,response);
            }
        }

    }



    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                         String userName, String matKhau, String nhapLaiMK,
                                         String email, String phoneNumber, String dob, String address
                                          ){
        // check validate
        String checkName = UserValidator.validateTenNguoiDung(userName);
        String checkMatKhau = UserValidator.validateMatKhau(matKhau);
        String checkNhapLaiMK = UserValidator.validateNhapLaiMatKhau(matKhau,nhapLaiMK);
        String checkEmail = UserValidator.validateEmail(email);
        String checkPhoneNumber = UserValidator.validateSDT(phoneNumber);
        String checkNgaySinh = UserValidator.validateNgaySinh(dob);
        String checkDiaChi = UserValidator.validateDiaChi(address);


        // count for validate
        int count = 0;

        if (!checkName.isEmpty()) {
            count++;
            request.setAttribute("ten_user_error", checkName);
        } else {
            request.setAttribute("ten_user", userName);
        }

        if (!checkMatKhau.isEmpty()) {
            count++;
            request.setAttribute("mat_khau_error", checkMatKhau);
        } else {
            request.setAttribute("mat_khau", matKhau);
        }

        if (!checkNhapLaiMK.isEmpty()) {
            count++;
            request.setAttribute("nhap_lai_MK_error", checkNhapLaiMK);
        } else {
            request.setAttribute("nhap_LaiMK", nhapLaiMK);
        }

        if (!checkEmail.isEmpty()) {
            count++;
            request.setAttribute("email_error", checkEmail);
        } else {
            request.setAttribute("email", email);
        }

        if (!checkPhoneNumber.isEmpty()) {
            count++;
            request.setAttribute("sdt_error", checkPhoneNumber);
        } else {
            request.setAttribute("sdt_user", phoneNumber);
        }

        if (!checkNgaySinh.isEmpty()) {
            count++;
            request.setAttribute("ngay_sinh_error", checkNgaySinh);
        } else {
            request.setAttribute("dob", dob);
        }

        if (!checkDiaChi.isEmpty()) {
            count++;
            request.setAttribute("dia_chi_error", checkDiaChi);
        } else {
            request.setAttribute("address", address);
        }



        if (count > 0) {
            return false;
        }
        return true;
    }
}
