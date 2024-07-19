package nhom55.hcmuaf.controller.page.order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.daoimpl.BillDaoImpl;
import nhom55.hcmuaf.enums.LogLevels;
import nhom55.hcmuaf.log.AbsDAO;
import nhom55.hcmuaf.log.Log;
import nhom55.hcmuaf.log.RequestInfo;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.OrderValidator;
import nhom55.hcmuaf.websocket.entities.CartsEntityWebSocket;

@WebServlet(name = "CheckOut", value = "/page/order/check-out")
public class CheckOut extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();

//    request.setAttribute("subTotalPrice", subTotalPrice);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/checkout.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String lastName = request.getParameter("ho_nguoi-dung");
    String firstName = request.getParameter("ten_nguoi-dung");
    String address = request.getParameter("dia-chi_nguoi-dung");
    String city = request.getParameter("provinceName");
    String district = request.getParameter("districtName");
    String phoneNumber = request.getParameter("sdt_nguoi-dung");
    String email = request.getParameter("email_nguoi-dung");
    String deliveryFee = request.getParameter("delivery_fee");
    String cleanedString = deliveryFee.replaceAll("[₫\\s]", "");
    cleanedString = cleanedString.replace(".", "");
    double deliveryFeeDouble = Double.parseDouble(cleanedString);
    String note = request.getParameter("note_nguoi-dung");
    String idVoucherString = request.getParameter("idVoucher");
    int idVoucher = 0;
    if (idVoucherString != null && !idVoucherString.trim().isEmpty()) {
      idVoucher = Integer.valueOf(idVoucherString);
    }
    if (checkValidate(request, response, lastName, firstName, address, city, phoneNumber, email)) {
      HttpSession session = request.getSession();
      Users users = MyUtils.getLoginedUser(session);
      double subTotalPrice = 0;
      // get selected Product for buy
      List<String> selectedProductIds = (List<String>) session.getAttribute("selectedProductIds");
      CartsEntityWebSocket cart = MyUtils.getCart(session);
      if (cart != null && selectedProductIds != null) {
        // get product list selected from cart
        List<CartsEntityWebSocket.CartItem> cartItem = cart.getCartItemList();
        subTotalPrice = (Double) session.getAttribute("subTotalPrice");
        BillDao billDao = new BillDaoImpl();
        // biến này sẽ lưu tất cả các hóa đơn người dùng đã mua
        List<Bills> listBills = new ArrayList<>();
        LocalDateTime timeNow = LocalDateTime.now();
        String productNameList = "";
        for (CartsEntityWebSocket.CartItem itemProduct : cartItem) {
          productNameList += itemProduct.getProductName() + ", ";
        }
        int idPayment = 1;
        address += address + ", quận " + district + ", tỉnh " + city;

        if (billDao.addAListProductToBills(timeNow, productNameList, "Đang giao", users.getId(),
            idPayment, firstName, lastName, address, city, phoneNumber, email, subTotalPrice,
            deliveryFeeDouble, note)) {
          int id_bills = billDao.getIDAListProductFromBills(timeNow, users.getId());
          for (CartsEntityWebSocket.CartItem itemProduct : cartItem) {
            if (billDao.addAProductToBillDetails(itemProduct.getId(), id_bills,
                itemProduct.getQuantity(), itemProduct.getQuantity() * itemProduct.getPrice())) {
//              billDao.degreeAmountWhenOderingSuccessfully(itemProduct.getId(),itemProduct.getQuantity());
            }
          }

          // xoa san pham sau khi dat hang
          deleteCart(session);

          //          Thông báo người mua đã đặt thành công
          Properties smtpProperties = MailProperties.getSMTPPro();
          Session session1 = Session.getInstance(smtpProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(MailProperties.getEmail(),
                  MailProperties.getPassword());
            }
          });
          try {
            Message message = new MimeMessage(session1);
            message.addHeader("Content-type", "text/HTML; charset= UTF-8");
            message.setFrom(new InternetAddress(MailProperties.getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("DAT HANG");
            message.setText(
                "Don dat hang cua ban thanh cong. Xem don hang ban vua moi dat tai day : "
                    + "http://localhost:8080/page/bill/detail?idBills="
                    + id_bills);
            Transport.send(message);
            boolean isOrderSuccessfully = true;
            Log<Bills> log = new Log<>();
            AbsDAO<Bills> absDAO = new AbsDAO<>();
            RequestInfo requestInfo = new RequestInfo(request.getRemoteAddr(), "HCM", "VietNam");
            log.setLevel(LogLevels.INFO);
            log.setIp(requestInfo.getIp());
            log.setAddress(requestInfo.getAddress());
            log.setNational(requestInfo.getNation());
            log.setNote("Người dùng " + users.getUsername() + " vừa đặt hàng thành công");
            log.setCurrentValue(
                lastName + " " + firstName + ", địa chỉ: " + address + ", số điện thoại: "
                    + phoneNumber + ", email: " + email + ", giá tiền đơn hàng: " + subTotalPrice
                    + ", tiền vận chuyển: " + deliveryFeeDouble + ", ghi chú: " + note
                    + ", kiểu thanh toán: Thẻ tín dụng " + ", ngày đặt hàng: " + timeNow
                    + " ,tổng tiền: " + (subTotalPrice + deliveryFeeDouble));
            log.setCreateAt(timeNow);
            absDAO.insert(log);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/bill/list-bill");
//            request.setAttribute("isOrderSuccessfully",isOrderSuccessfully);
            dispatcher.forward(request, response);
          } catch (Exception e) {
            System.out.println("SendEmail File Error " + e);
          }
        }


      }

    } else {
      doGet(request, response);
    }

  }


  private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
      String lastName, String firstName, String address, String city, String phoneNumber,
      String email) {
    String checkFirstName = OrderValidator.validateFirstName(firstName);
    String checkLastName = OrderValidator.validateLastName(lastName);
    String checkAddress = OrderValidator.validateAddress(address);
    String checkCity = OrderValidator.validateCity(city);
    String checkPhone = OrderValidator.validatePhoneNumber(phoneNumber);
    String checkEmail = OrderValidator.validateEmail(email);
    int count = 0;
    if (!checkFirstName.isEmpty()) {
      count++;
      request.setAttribute("firstNameError", checkFirstName);
    } else {
      request.setAttribute("firstName", firstName);
    }
    if (!checkLastName.isEmpty()) {
      count++;
      request.setAttribute("lastNameError", checkLastName);
    } else {
      request.setAttribute("lastName", lastName);
    }
    if (!checkAddress.isEmpty()) {
      count++;
      request.setAttribute("addressError", checkAddress);
    } else {
      request.setAttribute("address", address);
    }
    if (!checkCity.isEmpty()) {
      count++;
      request.setAttribute("cityError", checkCity);
    } else {
      request.setAttribute("city", city);
    }
    if (!checkPhone.isEmpty()) {
      count++;
      request.setAttribute("phoneError", checkPhone);
    } else {
      request.setAttribute("phone", phoneNumber);
    }
    if (!checkEmail.isEmpty()) {
      count++;
      request.setAttribute("emailError", checkEmail);
    } else {
      request.setAttribute("email", city);
    }
    if (count > 0) {
      return false;
    } else {
      return true;
    }
  }

  public static void deleteCart(HttpSession session) {
    List<String> selectedProductIds = (List<String>) session.getAttribute("selectedProductIds");
    CartsEntityWebSocket cart = (CartsEntityWebSocket) session.getAttribute("cart");
    List<Integer> productIds = selectedProductIds.stream()
        .map(Integer::valueOf)
        .collect(Collectors.toList());
    cart.deleteItems(productIds);
  }
}
