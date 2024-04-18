package nhom55.hcmuaf.controller.page.contact;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.daoimpl.ContactDAO;
import nhom55.hcmuaf.util.ContactValidator;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Contact", value = "/page/contact")
public class Contact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users users = MyUtils.getLoginedUser(session);
        if(users == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact.jsp");
            dispatcher.forward(request,response);
        } else {
            request.setAttribute("user",users);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact.jsp");
            dispatcher.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String ten = request.getParameter("ten");
         String email = request.getParameter("email");
         String deTai = request.getParameter("deTai");
         String tinNhan = request.getParameter("tinNhan");
         if(checkValidate(request,response,ten,email,deTai,tinNhan)) {
             ContactDAO contactDAO = new ContactDAO();
             contactDAO.themLienHe(ten,email,deTai,tinNhan);
             request.setAttribute("notify","Đã gửi thành công");
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact.jsp");
             dispatcher.forward(request,response);
         } else {
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/contact.jsp");
             dispatcher.forward(request,response);
         }


    }
    private static boolean checkValidate( HttpServletRequest request, HttpServletResponse response,String ten, String email,String deTai,String tinNhan) {
        String checkTen = ContactValidator.validateName(ten);
        String checkEmail = ContactValidator.validateEmail(email);
        String checkDeTai = ContactValidator.validateTopic(deTai);
        String checkTinNhan = ContactValidator.validateText(tinNhan);
        int count =0;
        if(!checkTen.isEmpty()) {
            count++;
            request.setAttribute("tenError",checkTen);
        } else {
            request.setAttribute("tenNguoiDung",ten);
        }

        if(!checkEmail.isEmpty()) {
            count++;
            request.setAttribute("emailError",checkEmail);
        } else {
            request.setAttribute("emailNguoiDung",email);
        }

        if(!checkDeTai.isEmpty()) {
            count++;
            request.setAttribute("deTaiError",checkDeTai);
        } else {
            request.setAttribute("deTaiNguoiDung",deTai);
        }

        if(!checkTinNhan.isEmpty()) {
            count++;
            request.setAttribute("tinNhanError",checkTinNhan);
        } else {
            request.setAttribute("tinNhanNguoiDung",ten);
        }

        if(count >0) {
            return false;
        } else {
            return true;
        }
    }
}
