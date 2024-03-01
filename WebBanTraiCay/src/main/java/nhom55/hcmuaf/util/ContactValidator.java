package nhom55.hcmuaf.util;

public class ContactValidator {
    public static String validateName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return "Vui lòng nhập vào tên của bạn";
        }

        // Adjust the regular expression to match your requirements
        if (!firstName.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên sản phẩm chỉ chứa ký tự chữ cái, khoảng trắng.";
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
    public static String validateTopic(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Vui lòng nhập địa chỉ của bạn.";
        }

        // Adjust the regular expression to match your requirements
        if (!address.matches("^[\\s\\S]*$")) {
            return "Địa chỉ chứa chữ cái, chữ số ,/ và ,.";
        }

        return "";
    }

    public static String validateText(String address) {
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
