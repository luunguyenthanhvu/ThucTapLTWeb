package nhom55.hcmuaf.controller.admin.product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dto.response.AddProductResponseDTO;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.ProductValidator;

@WebServlet(name = "AddProduct", value = "/admin/product/add-new-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
    1024 * 1024 * 100)
public class AddProductController extends HttpServlet {

  /**
   * @param request  an {@link HttpServletRequest} object that contains the request the client has
   *                 made of the servlet
   * @param response an {@link HttpServletResponse} object that contains the response the servlet
   *                 sends to the client
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // get the provider to add new product
    HttpSession session = request.getSession();
    Users admin = MyUtils.getLoginedUser(session);
    List<Providers> providerList = ProviderService.getInstance().getAll();
    request.setAttribute("providerList", providerList);

    request.setAttribute("admin", admin);
    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
    dispatcher.forward(request, response);
  }

  /**
   * @param request  an {@link HttpServletRequest} object that contains the request the client has
   *                 made of the servlet
   * @param response an {@link HttpServletResponse} object that contains the response the servlet
   *                 sends to the client
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Users admin = MyUtils.getLoginedUser(session);
    String productName = request.getParameter("name");
    LocalDateTime localDateTime = LocalDateTime.now();
    Date dateImport = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    System.out.println(productName);
    String description = request.getParameter("description");
    String price = request.getParameter("price");
//    String quantity = request.getParameter("quantity");
    String defaultWeight = request.getParameter("defaultWeight");
    String supplier = request.getParameter("supplier");
    String expirationDateStr = request.getParameter("expirationDate");
    String imgList = request.getParameter("supImages");
//    String importedFruit = request.getParameter("sourceImport");
    String category = request.getParameter("category");
//    String driedFruit = request.getParameter("driedFruit");

    // public id and asset id of product
    String mainImgPublicId = request.getParameter("mainImgPublicId");
    String mainImgAssetId = request.getParameter("mainImgAssetId");

    String supImgPublicId = request.getParameter("supImgPublicId");
    String supImgAssetId = request.getParameter("supImgAssetId");

    // if user Enter correct data
    //checkValidate(request, response, productName, description, price, quantity, defaultWeight,
    //        expirationDateStr, img, supplier)
    if (true) {
      try {
        List<String> imageList = List.of(imgList.split(","));
        List<String> listImgPublicId = List.of(supImgPublicId.split(","));
        List<String> listImgAssetId = List.of(supImgAssetId.split(","));
        Products products = new Products();
        products.setNameOfProduct(productName);
        products.setDescription(description);
        products.setPrice(Double.valueOf(price));
        products.setWeightDefault(Double.valueOf(defaultWeight));
        products.setProvider(Integer.parseInt(supplier));
        String categoryData;
        if(category.equalsIgnoreCase("trai-cay-viet")) {
          categoryData = "Trái cây Việt";
        } else if(category.equalsIgnoreCase("trai-cay-nhap")) {
          categoryData = "Trái cây Nhập";
        } else {
          categoryData = "Quà Tặng Trái Cây";
        }
        products.setCategory(categoryData);
        products.setDateOfImporting(new java.sql.Date(dateImport.getTime()));
        products.setAdminCreate(admin.getId());
        products.setImgPublicId(mainImgPublicId);
        products.setImgAssetId(mainImgAssetId);

//        Ghi log lại việc Thêm sản phẩm
        Log<Products> productsLog = new Log<>();
        AbsDAO<Products> absDAO= AbsDAO.getInstance();
        RequestInfo requestInfo = new RequestInfo(request.getRemoteAddr(), "HCM", "VietNam");
        productsLog.setIp(requestInfo.getIp());
        productsLog.setNational(requestInfo.getNation());
        productsLog.setAddress(requestInfo.getAddress());
        productsLog.setLevel(LogLevels.ALERT);
        productsLog.setNote("Người dùng "+admin.getUsername()+" đã thêm sản phẩm");
        productsLog.setPreValue("Không có dữ liệu");
        productsLog.setCurrentValue(products.toString());
        productsLog.setCreateAt(LocalDateTime.now());
        absDAO.insert(productsLog);

        ProductService.getInstance()
            .addNewProduct(products, imageList, listImgPublicId, listImgAssetId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject json = new JsonObject();
        json.addProperty("message", "Thêm sản phẩm thành công!");
        response.getWriter().write(json.toString());
        response.getWriter().flush();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      JsonObject json = new JsonObject();
      json.addProperty("message", "Lỗi cmnr");
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
   * @param expirationDate
   * @param filePart
   * @param provider
   * @return
   */
  private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
      String productName, String description, String price, String weightQuantity,
      String weightDefault, String expirationDate, String filePart, String provider) {
    // check validate
    String checkName = ProductValidator.validateTenSP(productName);
    String checkDescription = "";
    String checkPrice = ProductValidator.validateGiaTienSP(price);
    String checkWeightQuantity = ProductValidator.validateKhoiLuongSP(weightQuantity);
    String checkWeightDefault = ProductValidator.validateKgMacDinhSP(weightDefault);
    String checkexpirationDate = ProductValidator.validateNgayHetHan(expirationDate);
    String checkProvider = ProductValidator.validateNhaCC(provider);
    String checkFilePart = ProductValidator.validateFileUpload(filePart);

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

    if (!checkProvider.isEmpty()) {
      count++;
      request.setAttribute("nha_cung_cap_error", checkProvider);
    } else {
      request.setAttribute("nha_cung_cap", provider);
    }

    if (!checkFilePart.isEmpty()) {
      count++;
      request.setAttribute("file_anh_error", checkFilePart);
    }

    if (count > 0) {
      return false;
    }
    return true;
  }
}
