package nhom55.hcmuaf.controller.admin.product;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.beans.Image;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.ProductValidator;

@WebServlet(name = "AddProduct", value = "/admin/product/add-new-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize =
    1024 * 1024 * 100)
public class AddProduct extends HttpServlet {

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

    System.out.println(productName);
    String description = request.getParameter("description");
    double price = Double.parseDouble(request.getParameter("price"));
    double quantity = Double.parseDouble(request.getParameter("quantity"));
    double defaultWeight = Double.parseDouble(request.getParameter("defaultWeight"));
    String supplier = request.getParameter("supplier");
    String expirationDateStr = request.getParameter("expirationDate");
    String[] imgPublicIds = request.getParameterValues("img[public_id]");
    String[] imgUrls = request.getParameterValues("img[url]");

    // Process the image list
    List<Image> imgList = new ArrayList<>();
    if (imgPublicIds != null && imgUrls != null && imgPublicIds.length == imgUrls.length) {
      for (int i = 0; i < imgPublicIds.length; i++) {
        String publicId = imgPublicIds[i];
        String url = imgUrls[i];
        imgList.add(new Image(publicId, url));
      }
    }

    System.out.println("img n");
    imgList.forEach(img -> System.out.println(img));

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // generate expiration date
    Date expirationDate = null;
//    try {
//      expirationDate = formatter.parse(expirationDateStr);
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }

    Products products = new Products();
    products.setNameOfProduct(productName);
    products.setDescription(description);
    products.setPrice(price);
    products.setWeight(quantity);
    products.setWeightDefault(defaultWeight);
    products.setProvider(Integer.parseInt(supplier));
    products.setExpriredDay((java.sql.Date) expirationDate);
    // if user Enter correct data
//    checkValidate(request, response, products.getNameOfProduct(), products.getDescription(),
//        String.valueOf(products.getPrice()),
//        String.valueOf(products.getWeight()),
//        String.valueOf(products.getWeightDefault()), String.valueOf(products.getExpriredDay()),
//        String.valueOf(products.getImageList()), String.valueOf(products.getProvider()))
    if (true) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      // generate date admin import product
      LocalDateTime localDateTime = LocalDateTime.now();
      Date dateImport = null;
      products.setDateOfImporting(null);
      products.setAdminCreate(admin.getId());
      products.setImageList(imgList);
      ProductService.getInstance().addNewProduct(products);
      response.sendRedirect(request.getContextPath() + "/admin/product/add-new-product");

      // user Enter Wrong data
    } else {
      List<Providers> providerList = ProviderService.getInstance().getAll();
      request.setAttribute("providerList", providerList);
      request.setAttribute("admin", admin);
      RequestDispatcher dispatcher = this.getServletContext()
          .getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
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
   * @param expirationDate
   * @param filePart
   * @param provider
   * @return
   */
  private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
      String productName, String description, String price,
      String weightQuantity, String weightDefault, String expirationDate,
      String filePart, String provider) {
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
