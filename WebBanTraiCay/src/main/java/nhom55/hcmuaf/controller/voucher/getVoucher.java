package nhom55.hcmuaf.controller.voucher;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.beans.Voucher;
import nhom55.hcmuaf.dao.daoimpl.VoucherDAOImpl;
import nhom55.hcmuaf.util.MyUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getVoucher", value = "/voucher/get-voucher")
public class getVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users users = MyUtils.getLoginedUser(session);
        List<Voucher> listVoucher = new ArrayList<>();
        VoucherDAOImpl voucherDAO = new VoucherDAOImpl();
        listVoucher = voucherDAO.getVoucherByIdUser(users.getId());
        System.out.println("Kích thước list voucher: " + listVoucher.size());
        // Chuyển đổi danh sách voucher thành JSON
        JSONArray jsonArray = new JSONArray();
        for (Voucher voucher : listVoucher) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", voucher.getId());
            jsonObject.put("idUser", voucher.getIdUser());
            jsonObject.put("title", voucher.getTitle());
            jsonObject.put("content", voucher.getContent());
            jsonObject.put("beginDate", voucher.getBeginDate().toString());
            jsonObject.put("endDate", voucher.getEndDate().toString());
            jsonObject.put("price", voucher.getPrice());
            jsonArray.add(jsonObject);
        }

        // Thiết lập kiểu phản hồi và mã hóa
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Ghi JSON vào phản hồi
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}