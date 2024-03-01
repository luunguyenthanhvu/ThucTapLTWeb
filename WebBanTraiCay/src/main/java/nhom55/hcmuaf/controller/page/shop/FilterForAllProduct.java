package nhom55.hcmuaf.controller.page.shop;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.services.ShopService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "FilterForAllProduct", value = "/page/shop/filter-for-all-product")
public class FilterForAllProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       Lấy các thuộc tính của filter
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");

 // pageSTR(pageNumber là kiểu int khi chuyển từ String PageSTr),  là số mà người dùng bấm vào số mà muốn chuyển trang
        String pageStr = request.getParameter("pageId");
        int pageNumber = 0;
        if(pageStr == null) {
            pageNumber =1;
        } else {
            pageNumber = Integer.valueOf(pageStr);
        }

        int quantityDefault =20;
        int totalRow = ShopService.getInstance().countTotalRowProductInDatabase();
        int haveMaxPage = (totalRow/quantityDefault) +1;

        List<Products> list =  ShopService.getInstance().sortByFilter(pageNumber,quantityDefault,sortBy,order);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/shop.jsp");
            request.setAttribute("sortBy",sortBy);
            request.setAttribute("order",order);
            request.setAttribute("haveMaxPage",haveMaxPage);
            request.setAttribute("listOfProduct",list);
            request.setAttribute("pageId",pageNumber);
            rd.forward(request,response);
    }
}