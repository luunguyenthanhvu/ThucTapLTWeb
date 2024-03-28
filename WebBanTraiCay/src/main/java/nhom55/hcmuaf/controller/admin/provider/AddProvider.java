package nhom55.hcmuaf.controller.admin.provider;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.MyUtils;
import nhom55.hcmuaf.util.ProductValidator;
import nhom55.hcmuaf.util.ProviderValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddProvider", value = "/admin/provider/add-provider")
public class AddProvider extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        request.setAttribute("admin",admin);
        RequestDispatcher requestDispatcher = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/admin/add-provider.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        String nhaCungCap = request.getParameter("ten_nha_cung_cap");
        String diaChiNhaCungCap = request.getParameter("dia_chi_nha_cung_cap");
        if(checkValidate(request,response,nhaCungCap,diaChiNhaCungCap)) {
            ProviderService.getInstance().addNewProvider(nhaCungCap,diaChiNhaCungCap);
            doGet(request,response);
        } else {
            request.setAttribute("admin",admin);
            RequestDispatcher dispatcher = this.getServletContext()
                    .getRequestDispatcher("/WEB-INF/admin/add-provider.jsp");
            dispatcher.forward(request, response);
        }
    }



    /**
     * check validate for form input
     *
     * @param tenNhaCungCap
     * @param diaChiNhaCungCap
     * @return
     */
    private static boolean checkValidate(HttpServletRequest request, HttpServletResponse response,
                                         String tenNhaCungCap, String diaChiNhaCungCap) {
        // check validate
        String checkTenNhaCungCap = ProviderValidator.validateTenNCC(tenNhaCungCap);
        String checkDiaChiNhaCungCap = ProviderValidator.validateDiaChiNhaCungCap(diaChiNhaCungCap);


        // count for validate
           int count = 0;

        if (!checkTenNhaCungCap.isEmpty()) {
            count++;
            request.setAttribute("ten_ncc_error", checkTenNhaCungCap);
        } else {
            request.setAttribute("ten_NCC", tenNhaCungCap);
        }

        if (!checkDiaChiNhaCungCap.isEmpty()) {
            count++;
            request.setAttribute("dia_chi_NCC_error", checkDiaChiNhaCungCap);
        } else {
            request.setAttribute("diachi_NCC", diaChiNhaCungCap);
        }



        if (count > 0) {
            return false;
        }
        return true;
    }
}
