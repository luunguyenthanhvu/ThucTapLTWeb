package nhom55.hcmuaf.controller.admin.product;

import nhom55.hcmuaf.beans.Products;

import nhom55.hcmuaf.beans.Providers;

import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.dao.ProviderDao;
import nhom55.hcmuaf.dao.daoimpl.ProviderDaoImpl;

import nhom55.hcmuaf.services.ShopService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateProduct", value = "/admin/product/update")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
         int idSanPham = Integer.valueOf(request.getParameter("id"));
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
        request.setAttribute("admin", admin);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
