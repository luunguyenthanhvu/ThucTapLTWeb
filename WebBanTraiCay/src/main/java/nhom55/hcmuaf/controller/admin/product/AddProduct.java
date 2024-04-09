package nhom55.hcmuaf.controller.admin.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProductService;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.ProductValidator;
import org.codehaus.jackson.map.ObjectMapper;

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

    BufferedReader reader = request.getReader();
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
    }

    String jsonData = stringBuilder.toString();

    ObjectMapper objectMapper = new ObjectMapper();
    Products products = objectMapper.readValue(jsonData, Products.class);

    // value field from front end
//    String productName = request.getParameter("ten_san_pham");
//    String description = request.getParameter("mo_ta_san_pham");
//    String priceString = request.getParameter("gia_tien_san_pham");
//    String weightQuantityString = request.getParameter("khoi_luong_san_pham");
//    String weightDefaultString = request.getParameter("so_kg_mac_dinh");
//    String expirationDateString = request.getParameter("ngay_het_han");
//    String providerString = request.getParameter("provider");
//    Part filePart = request.getPart("upload_file_san_pham");
//    String filePartString = filePart.getSubmittedFileName();

    // if user Enter correct data
    if (checkValidate(request, response, products.getNameOfProduct(), products.getDescription(),
        String.valueOf(products.getPrice()),
        String.valueOf(products.getWeight()),
        String.valueOf(products.getWeightDefault()), String.valueOf(products.getExpriredDay()),
        String.valueOf(products.getImageList()), String.valueOf(products.getProvider()))) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      // parse to valid value
//      double price = Double.parseDouble(priceString);
//      double weightQuantity = Double.parseDouble(weightQuantityString);
//      double weightDefault = Double.parseDouble(weightDefaultString);
//      int provider = Integer.parseInt(providerString);

      // generate expiration date
//      Date expirationDate = null;
//      try {
//        expirationDate = dateFormat.parse(expirationDateString);
//      } catch (ParseException e) {
//        e.printStackTrace();
//      }
//      // generate img name
//      String imgProduct = "";

//      String fileName = filePart.getSubmittedFileName();
//      ServletContext servletContext = getServletContext();
//
//      File root = new File(servletContext.getRealPath("/") + "/data");
//
//      // create a new folder if not exists
//      if (!root.exists()) {
//        root.mkdirs();
//      }
//      // save img to data folder
//      for (Part part : request.getParts()) {
//        part.write(root.getAbsolutePath() + '/' + fileName);
//        imgProduct = "/data/" + fileName;
//      }

      // generate date admin import product
      LocalDateTime localDateTime = LocalDateTime.now();
      Date dateImport = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

      products.setAdminCreate(admin.getId());
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
