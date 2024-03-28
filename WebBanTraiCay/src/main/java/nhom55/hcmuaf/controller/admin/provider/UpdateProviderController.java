package nhom55.hcmuaf.controller.admin.provider;

import nhom55.hcmuaf.beans.Providers;
import nhom55.hcmuaf.services.ProviderService;
import nhom55.hcmuaf.util.ProviderValidator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateProviderController", value = "/admin/provider/update-provider-controller")
public class UpdateProviderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.sendRedirect("ProviderList");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         int idProvider = Integer.valueOf(request.getParameter("id_san_pham"));
         String tenNCC = request.getParameter("ten_nha_cung_cap");
         String diaChi = request.getParameter("dia_chi_nha_cung_cap");
         if(checkValidate(request,response,tenNCC,diaChi)) {
             ProviderService.getInstance().updateProvider(tenNCC,diaChi,idProvider);
             doGet(request,response);
         } else {
             List<Providers> list = ProviderService.getInstance().getAll();
             Providers provider =null;
             for(Providers p: list) {
                 if(p.getId() == idProvider) {
                     provider =p;
                     break;
                 }
             }
             request.setAttribute("provider",provider);
             RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/update-provider.jsp");
             dispatcher.forward(request,response);
         }
    }


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
