package nhom55.hcmuaf.controller.admin.product;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.ProviderDao;
import nhom55.hcmuaf.dao.daoimpl.ProviderDaoImpl;
import nhom55.hcmuaf.services.ShopService;
import nhom55.hcmuaf.services.UpdateProductServiceForAdmin;
import nhom55.hcmuaf.util.UpdatedProductValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
        1024 * 1024 * 100)
@WebServlet(name = "UpdateProductController", value = "/admin/product/update-controller")
public class UpdateProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/admin/product/product-list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("loginedUser");
        int idSanPham = Integer.valueOf(request.getParameter("id_san_pham"));
        String tenSanPham = request.getParameter("ten_san_pham");
        String moTa = request.getParameter("mo_ta_san_pham");
        String price = request.getParameter("gia_tien_san_pham");
        String seasonalFruit = request.getParameter("selectedSeasonalFruit");
        String sourceImport = request.getParameter("selectedSourceImport");
        String driedFruit = request.getParameter("selectedDriedFruit");
        String khoiLuong = request.getParameter("khoi_luong_san_pham");
        String soKGMacDinh = request.getParameter("so_kg_mac_dinh");
        String ngayNhapHang = request.getParameter("ngay_nhap_hang");
        String ngayHetHan = request.getParameter("ngay_het_han");
        String nhaCungCap = request.getParameter("selectedProviderID");
        Part filePart = request.getPart("file");

        if (checkValidate(request, response, tenSanPham, moTa, price,
                khoiLuong,
                soKGMacDinh,ngayNhapHang, ngayHetHan, nhaCungCap)) {

            double priceValue = Double.valueOf(price);
            double khoiluongValue = Double.valueOf(khoiLuong);
            double soKGMacDinhValue = Double.valueOf(soKGMacDinh);
            int idNhaCungCap = Integer.valueOf(nhaCungCap);

            if (filePart == null || filePart.getSize() == 0) {
                // Người dùng không chọn file, xử lý tại đây

                UpdateProductServiceForAdmin.getInstance().editProductNoImage(idSanPham, tenSanPham, moTa,seasonalFruit, sourceImport,driedFruit, priceValue, khoiluongValue, soKGMacDinhValue, Date.valueOf(ngayNhapHang), Date.valueOf(ngayHetHan), user.getId(), idNhaCungCap);
                doGet(request,response);
            } else {
                String linkAnhSanPham ="";
                String fileName = filePart.getSubmittedFileName();
                ServletContext servletContext = getServletContext();
                File root = new File(servletContext.getRealPath("/") + "/data");
                if (!root.exists()) root.mkdirs();
                for (Part part : request.getParts()) {
                    part.write(root.getAbsolutePath() + "/" + fileName);
                    linkAnhSanPham ="/data/" + fileName;
                }
                UpdateProductServiceForAdmin.getInstance().editProductHaveImage(idSanPham, tenSanPham, moTa,seasonalFruit, sourceImport,driedFruit,priceValue, khoiluongValue, soKGMacDinhValue, Date.valueOf(ngayNhapHang), Date.valueOf(ngayHetHan),linkAnhSanPham, user.getId(), idNhaCungCap);
                doGet(request,response);
            }
        } else {

            List<Products> listProduct = ShopService.getInstance().getListProducts();
            Products products = new Products();
            for(Products p: listProduct) {
                if(p.getId() == idSanPham) {
                    products =p;
                    break;
                }
            }
            ProviderDao dao = new ProviderDaoImpl();
            List<Providers> listProvider = dao.getAllProvider();
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/admin/update-product.jsp");
            request.setAttribute("product",products);
            request.setAttribute("listProvider",listProvider);
            dispatcher.forward(request, response);
        }




    }



    /**
     * check validate for form input
     *
     * @param productName
     * @param description
     * @param price
     * @param weightQuantity
     * @param weightDefault
     * @param importedDate
     * @param expirationDate
     * @param provider
     * @return
     */
    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                         String productName, String description, String price,
                                         String weightQuantity, String weightDefault,String importedDate , String expirationDate
                                         , String provider) {
        // check validate
        String checkName = UpdatedProductValidator.validateTenSP(productName);
        String checkDescription = UpdatedProductValidator.validateMoTaSP(description);
        String checkPrice = UpdatedProductValidator.validateGiaTienSP(price);
        String checkWeightQuantity = UpdatedProductValidator.validateKhoiLuongSP(weightQuantity);
        String checkWeightDefault = UpdatedProductValidator.validateKgMacDinhSP(weightDefault);
        String checkImportedDate =   UpdatedProductValidator.validateNgayNhapHang(importedDate);
        String checkexpirationDate = UpdatedProductValidator.validateNgayHetHan(expirationDate);
        String checkProvider = UpdatedProductValidator.validateNhaCC(provider);


        // count for validate
        int count = 0;

        if (!checkName.isEmpty()) {
            count++;
            request.setAttribute("ten_sp_error", checkName);
        } else {
            request.setAttribute("ten_sp", productName);
        }

        if (!checkDescription.isEmpty()) {
            count++;
            request.setAttribute("mo_ta_error", checkDescription);
        } else {
            request.setAttribute("mota_sp", description);
        }

        if (!checkPrice.isEmpty()) {
            count++;
            request.setAttribute("gia_tien_error", checkPrice);
        } else {
            request.setAttribute("gia_tien_sp", Double.parseDouble(price));
        }

        if (!checkWeightQuantity.isEmpty()) {
            count++;
            request.setAttribute("khoi_luong_error", checkWeightQuantity);
        } else {
            request.setAttribute("khoi_luong_sp", Double.parseDouble(weightQuantity));
        }

        if (!checkWeightDefault.isEmpty()) {
            count++;
            request.setAttribute("khoi_luong_mac_dinh_error", checkWeightDefault);
        } else {
            request.setAttribute("khoi_luong_mac_dinh_sp", Double.parseDouble(weightDefault));
        }
        if (!checkexpirationDate.isEmpty()) {
            count++;
            request.setAttribute("ngay_het_han_error", checkexpirationDate);
        } else {
            request.setAttribute("ngay_het_han_sp", expirationDate);
        }


        if (!checkImportedDate.isEmpty()) {
            count++;
            request.setAttribute("ngay_nhap_hang_error", checkexpirationDate);
        } else {
            request.setAttribute("ngay_nhap_hang_sp", expirationDate);
        }

        if (!checkProvider.isEmpty()) {
            count++;
            request.setAttribute("nha_cung_cap_error", checkProvider);
        } else {
            request.setAttribute("nha_cung_cap", provider);
        }



        if (count > 0) {
            return false;
        }
        return true;
    }
}
