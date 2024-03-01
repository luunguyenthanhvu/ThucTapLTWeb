package nhom55.hcmuaf.util;

public class ProviderValidator {
    public static String validateTenNCC(String providerName) {
        if (providerName == null || providerName.trim().isEmpty()) {
            return "Vui lòng nhập vào tên nhà cung cấp";
        }

        // Adjust the regular expression to match your requirements
        if (!providerName.matches("^[\\p{L}0-9\\s']*")) {
            return "Tên nhà cung cấp chỉ chứa ký tự chữ cái, chữ số, khoảng trắng ";
        }

        return "";
    }

    public static String validateDiaChiNhaCungCap(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Vui lòng nhập địa chỉ nhà cung cấp.";
        }

        // Adjust the regular expression to match your requirements
        if (!address.matches("^[\\s\\S]*$")) {
            return "Địa chỉ nhà cung cấp chỉ chứa chữ cái, chữ số.";
        }

        return "";
    }
}
