package nhom55.hcmuaf.controller.page.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Properties;

import nhom55.hcmuaf.beans.Bills;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.cart.Cart;
import nhom55.hcmuaf.beans.cart.CartProduct;
import nhom55.hcmuaf.dao.BillDao;
import nhom55.hcmuaf.dao.BillDaoImpl;
import nhom55.hcmuaf.sendmail.MailProperties;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.OrderValidator;

@WebServlet(name = "CheckOut", value = "/page/order/check-out")
public class CheckOut extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    double subTotalPrice = 0;
    // get selected Product for buy
    List<String> selectedProductIds = (List<String>) session.getAttribute("selectedProductIds");
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart != null && selectedProductIds != null) {
      // get product list selected from cart
      List<CartProduct> selectedProducts = cart.getSelectedProducts(selectedProductIds);
      subTotalPrice = getTotalPrice(selectedProducts);
    }
    // set transport price
    if (subTotalPrice >= 200000) {
      request.setAttribute("totalPrice", subTotalPrice);
    } else {
      request.setAttribute("transportPrice", 20000);
      request.setAttribute("totalPrice", subTotalPrice + 20000);
    }
    request.setAttribute("subTotalPrice", subTotalPrice);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/checkout.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String lastName = request.getParameter("ho_nguoi-dung");
    String firstName = request.getParameter("ten_nguoi-dung");
    String address = request.getParameter("dia-chi_nguoi-dung");
    String city = request.getParameter("thanh-pho");
    String phoneNumber = request.getParameter("sdt_nguoi-dung");
    String email = request.getParameter("email_nguoi-dung");
    String payment = request.getParameter("payment_selection");
    if(checkValidate(request,response,lastName,firstName,address,city,phoneNumber,email)) {
      HttpSession session = request.getSession();
      Users users = MyUtils.getLoginedUser(session);
      double subTotalPrice = 0;
      // get selected Product for buy
      List<String> selectedProductIds = (List<String>) session.getAttribute("selectedProductIds");
      Cart cart = (Cart) session.getAttribute("cart");
      if (cart != null && selectedProductIds != null) {
        // get product list selected from cart
        List<CartProduct> selectedProducts = cart.getSelectedProducts(selectedProductIds);
        subTotalPrice = getTotalPrice(selectedProducts);
        BillDao billDao = new BillDaoImpl();
        // biến này sẽ lưu tất cả các hóa đơn người dùng đã mua
        List<Bills> listBills = new ArrayList<>();
        LocalDateTime timeNow = LocalDateTime.now();
        String productNameList ="";
        for(CartProduct c: selectedProducts) {
          productNameList +=  c.getProducts().getNameOfProduct()+"\t";
        }
        int idPayment =0;
        if(payment.equals("Paypal")) {
          idPayment=2;
        } else {
          idPayment=1;
        }

        if(billDao.addAListProductToBills(timeNow,productNameList,"Đang giao", users.getId(), idPayment,firstName,lastName,address,city,phoneNumber,email,subTotalPrice )) {
          int id_bills = billDao.getIDAListProductFromBills(timeNow, users.getId());
          for(CartProduct c: selectedProducts) {
            if( billDao.addAProductToBillDetails(c.getProducts().getId(),id_bills,c.getQuantity(),c.getQuantity()*c.getProducts().getPrice())) {
              billDao.degreeAmountWhenOderingSuccessfully(c.getProducts().getId(),c.getQuantity());
            }
          }

          // xoa san pham sau khi dat hang
          deleteCart(session);

          //          Thông báo người mua đã đặt thành công
          Properties smtpProperties = MailProperties.getSMTPPro();
          Session session1 = Session.getInstance(smtpProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(MailProperties.getEmail(), MailProperties.getPassword());
            }
          });
          try {
            Message message = new MimeMessage(session1);
            message.addHeader("Content-type", "text/HTML; charset= UTF-8");
            message.setFrom(new InternetAddress(MailProperties.getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("DAT HANG");
            message.setText("Don dat hang cua ban thanh cong. Xem don hang ban vua moi dat tai day : " + "http://localhost:8080/page/bill/detail?idBills="
                    +id_bills);
            Transport.send(message);
            boolean  isOrderSuccessfully = true;
            RequestDispatcher dispatcher = request.getRequestDispatcher("/page/shop/shop-forward");
            request.setAttribute("isOrderSuccessfully",isOrderSuccessfully);
            dispatcher.forward(request,response);
          } catch (Exception e) {
            System.out.println("SendEmail File Error " + e);
          }
        }


      }

    } else {
      doGet(request,response);
    }

  }

  private static double getTotalPrice(List<CartProduct> selectedProducts) {
    double result = 0;
    for (CartProduct product : selectedProducts) {
      result += product.getPrice();
    }
    return result;
  }
  private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,String lastName, String firstName, String address,String city, String phoneNumber, String email){
           String checkFirstName= OrderValidator.validateFirstName(firstName);
           String checkLastName= OrderValidator.validateLastName(lastName);
           String checkAddress= OrderValidator.validateAddress(address);
           String checkCity = OrderValidator.validateCity(city);
           String checkPhone = OrderValidator.validatePhoneNumber(phoneNumber);
           String checkEmail = OrderValidator.validateEmail(email);
           int count =0;
           if(!checkFirstName.isEmpty()) {
              count++;
              request.setAttribute("firstNameError",checkFirstName);
           } else {
             request.setAttribute("firstName",firstName);
           }
           if(!checkLastName.isEmpty()) {
             count++;
             request.setAttribute("lastNameError",checkLastName);
           } else {
             request.setAttribute("lastName",lastName);
           }
           if(!checkAddress.isEmpty()) {
             count++;
             request.setAttribute("addressError",checkAddress);
           } else {
             request.setAttribute("address",address);
           }
           if(!checkCity.isEmpty()) {
             count++;
             request.setAttribute("cityError",checkCity);
           } else {
             request.setAttribute("city",city);
           }
    if(!checkPhone.isEmpty()) {
      count++;
      request.setAttribute("phoneError",checkPhone);
    } else {
      request.setAttribute("phone",phoneNumber);
    }
    if(!checkEmail.isEmpty()) {
      count++;
      request.setAttribute("emailError",checkEmail);
    } else {
      request.setAttribute("email",city);
    }
    if(count >0) {
      return false;
    } else {
      return true;
    }
  }
  public static void deleteCart(HttpSession session) {
    List<String> selectedProductIds = (List<String>) session.getAttribute("selectedProductIds");
    Cart cart = (Cart) session.getAttribute("cart");
    for(String idProduct : selectedProductIds) {
      int id = Integer.valueOf(idProduct);
      cart.deleteProduct(id);
    }
  }
}