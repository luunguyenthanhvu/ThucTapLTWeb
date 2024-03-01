package nhom55.hcmuaf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class OrderValidator {
    public static String validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return "Vui lòng nhập vào tên của bạn";
        }

        // Adjust the regular expression to match your requirements
        if (!firstName.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên sản phẩm chỉ chứa ký tự chữ cái, khoảng trắng.";
        }
        return "";
    }

    public static String validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            return "Vui lòng nhập vào họ của bạn";
        }

        // Adjust the regular expression to match your requirements
        if (!lastName.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên sản phẩm chỉ chứa ký tự chữ cái, khoảng trắng.";
        }
        return "";
    }
    public static String validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            return "Vui lòng nhập vào họ của bạn";
        }

        // Adjust the regular expression to match your requirements
        if (!city.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên thành phố chỉ chứa ký tự chữ cái, khoảng trắng.";
        }
        return "";
    }

    public static String validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Vui lòng nhập địa chỉ của bạn.";
        }

        // Adjust the regular expression to match your requirements
        if (!address.matches("^[\\s\\S]*$")) {
            return "Địa chỉ chứa chữ cái, chữ số ,/ và ,.";
        }

        return "";
    }
    public static String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Vui lòng nhập số điện thoại";
        }

        String phoneRegex = "^[0-9]{10}$";

        if (!Pattern.matches(phoneRegex, phoneNumber.replaceAll("[\\s\\-()]+", ""))) {
            return "Số điện thoại không hợp lệ";
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
}
