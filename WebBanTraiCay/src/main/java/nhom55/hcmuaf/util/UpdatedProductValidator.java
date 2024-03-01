package nhom55.hcmuaf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdatedProductValidator {
    public static String validateTenSP(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return "Vui lòng nhập vào tên sản phẩm";
        }

        // Adjust the regular expression to match your requirements
        if (!productName.matches("^\\p{L}[\\p{L}\\s']*")) {
            return "Tên sản phẩm chỉ chứa ký tự chữ cái, khoảng trắng.";
        }

        return "";
    }

    public static String validateNhaCC(String provider) {
        if (provider == null || provider.trim().isEmpty()) {
            return "Vui lòng chọn nhà cung cấp";
        }

        return "";
    }
    public static String validateNgayNhapHang(String importedDay) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ngayNhapHang = dateFormat.parse(importedDay);
            Date currentDate = new Date();

            if (ngayNhapHang.after(currentDate)) {
                return "Ngày nhập hàng phải trước ngày hiện tại";
            }
        } catch (ParseException e) {
            return "Vui lòng chọn ngày hết hạn.";
        }

        return "";
    }
    public static String validateNgayHetHan(String expirationDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expirationDate = dateFormat.parse(expirationDateString);
            Date currentDate = new Date();

            if (expirationDate.before(currentDate)) {
                return "Ngày hết hạn phải sau ngày hiện tại.";
            }
        } catch (ParseException e) {
            return "Vui lòng chọn ngày hết hạn.";
        }

        return "";
    }



    public static String validateKhoiLuongSP(String weightQuantity) {
        if (weightQuantity == null || weightQuantity.trim().isEmpty()) {
            return "Vui lòng nhập vào khối lượng nhập hàng.";
        }

        try {
            double quantity = Double.parseDouble(weightQuantity);
            if (quantity <= 0) {
                return "Khối lượng nhập hàng chỉ chứa chữ số, không âm.";
            }
        } catch (NumberFormatException e) {
            return "Khối lượng nhập hàng chỉ chứa chữ số, không âm.";
        }

        return "";
    }

    public static String validateKgMacDinhSP(String weightDefault) {
        if (weightDefault == null || weightDefault.trim().isEmpty()) {
            return "Vui lòng nhập vào khối lượng mặc định.";
        }

        try {
            double defaultWeight = Double.parseDouble(weightDefault);
            if (defaultWeight <= 0) {
                return "Khối lượng mặc định chỉ chứa chữ số, không âm.";
            }
        } catch (NumberFormatException e) {
            return "Khối lượng mặc định chỉ chứa chữ số, không âm.";
        }

        return "";
    }

    public static String validateGiaTienSP(String price) {
        if (price == null || price.trim().isEmpty()) {
            return "Vui lòng nhập vào giá tiền sản phẩm.";
        }

        try {
            double productPrice = Double.parseDouble(price);
            if (productPrice <= 0) {
                return "Giá tiền sản phẩm chỉ chứa chữ số, không âm.";
            }
        } catch (NumberFormatException e) {
            return "Giá tiền sản phẩm chỉ chứa chữ số, không âm.";
        }

        return "";
    }

    public static String validateMoTaSP(String description) {
        if (description == null || description.trim().isEmpty()) {
            return "Vui lòng nhập vào mô tả sản phẩm.";
        }

        // Adjust the regular expression to match your requirements
        if (!description.matches("^[\\s\\S]*$")) {
            return "Mô tả sản phẩm chỉ chứa chữ cái, chữ số.";
        }

        return "";
    }
}
