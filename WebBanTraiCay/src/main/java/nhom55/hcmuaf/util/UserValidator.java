
package nhom55.hcmuaf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

public class UserValidator {

    // Của Tuấn
    public static String validateUpFileAnh(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return "Vui lòng chọn file ảnh.";
        }
        return "";
    }

    public static String validateGioiTinh(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            return "Vui lòng chọn giới tính";
        }
        String lowerCaseGender = gender.toLowerCase();

        // Thêm kiểm tra cho giới tính là nam hoặc nữ
        if (!lowerCaseGender.equals("nam") && !lowerCaseGender.equals("nữ")) {
            return "Giới tính không hợp lệ. Vui lòng chọn 'nam' hoặc 'nữ'.";
        }

        return "";
    }

    public static String validateOldAndNewPass(String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return "Mật khẩu mới không được giống mật khẩu cũ.";
        }
        return ""; // Mật khẩu nhập lại hợp lệ
    }

// Của Tú
    public static String validateTenNguoiDung(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return "Vui lòng nhập tên người dùng";
        }

        // Adjust the regular expression to match your requirements
        if (!userName.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên người dùng chỉ chứa ký tự chữ cái, khoảng trắng.";
        }

        return "";
    }
    public static String validateNgaySinh(String dob) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ngaySinh = dateFormat.parse(dob);
            Date currentDate = new Date();

            if (ngaySinh.after(currentDate)) {
                return "Ngày tháng năm sinh không được là ngày ở tương lai.";
            }
        } catch (ParseException e) {
            return "Vui lòng chọn ngày sinh của người dùng.";
        }

        return "";
    }



    public static String validateMatKhau(String matKhau) {
        String regex = "^.{6,}$";
        if (matKhau == null || matKhau.isEmpty()) {
            return "Vui lòng nhập mật khẩu.";
        }
        if(!(matKhau.matches(regex))) {
            return "Độ dài mật khẩu có ít nhất 6 kí tự";
        }
        return "";
    }

    public static String validateNhapLaiMatKhau(String matKhau, String nhapLaiMK) {
        if (nhapLaiMK == null || nhapLaiMK.isEmpty()) {
            return "Vui lòng nhập mật khẩu.";
        }
        if(!(nhapLaiMK.equals(matKhau))) {
            return "Mật khẩu bạn nhập không giống ở trên. Vui lòng nhập lại";
        }
        return "";
    }

    public static String validateEmail(String email) {
        String regex = "^(([^<>()[\\\\]\\.,;:\\s@\\\"]+(\\.[^<>()[\\\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (email == null || email.trim().isEmpty()) {
            return "Vui lòng nhập email.";
        }
        if(!(email.matches(regex))) {
            return "Email của bạn không đúng định dạng abc@xyz.abc . Vui lòng nhập lại.";
        }
        return "";
    }

    public static String validateSDT(String phoneNumber) {
        String regex = "^(0[1-9]|84[1-9])(\\d{8,9})$";
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Vui lòng nhập số điện thoại của bạn.";
        }
        if(!(phoneNumber.matches(regex))) {
            return "Không đúng định dạng số điện thoại. Vui lòng nhập lại (Vd: 0982407940).";
        }
        return "";
    }


    public static String validateDiaChi(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Vui lòng nhập địa chỉ của bạn.";
        }

        // Adjust the regular expression to match your requirements
        if (!address.matches("^[\\s\\S]*$")) {
            return "Địa chỉ chứa chữ cái, chữ số ,/ và ,.";

        }
        return "";
    }

}




