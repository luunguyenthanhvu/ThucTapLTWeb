package nhom55.hcmuaf.controller.admin.product;

import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.beans.Users;
import nhom55.hcmuaf.services.ShopService;
import nhom55.hcmuaf.util.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilterProductController", value = "/admin/product/filter-product")
public class FilterProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users admin = MyUtils.getLoginedUser(session);
        //       Lấy các thuộc tính của filter
        String  price_sortAsc = request.getParameter("price_sortAsc");

        String  price_sortDesc = request.getParameter("price_sortDesc");

        String  name_sortAsc = request.getParameter("name_sortAsc");
        String  name_sortDesc = request.getParameter("name_sortDesc");
        String  date_sort = request.getParameter("date_sort");
        String  springFruit = request.getParameter("spring");
        String  summerFruit = request.getParameter("summer");
        String  fallFruit = request.getParameter("fall");

        String  winterFruit = request.getParameter("winter");
        String localFruit = request.getParameter("local");
        String importedFruit = request.getParameter("imported");
        String driedFruit = request.getParameter("dried");


        String order ="";
        String whereClause = "";

        if (price_sortAsc != null && !("".equals(price_sortAsc))) {
            order += "price ASC, ";
        }
        if (price_sortDesc != null && !("".equals(price_sortDesc))) {
            order += "price DESC, ";
        }
        if (name_sortAsc != null && !("".equals(name_sortAsc))) {
            order += "nameOfProduct ASC, ";
        }
        if (name_sortDesc != null && !("".equals(name_sortDesc))) {
            order += "nameOfProduct DESC, ";
        }
        if (date_sort != null && !("".equals(date_sort))) {
            order += "dateOfImporting DESC, ";
        }
        if (springFruit != null && !("".equals(springFruit))) {
            whereClause += "seasonalFruit = 'spring' Or ";
        }
        if (summerFruit != null && !("".equals(summerFruit))) {
            whereClause += "seasonalFruit = 'summer' Or ";
        }
        if (fallFruit != null && !("".equals(fallFruit))) {
            whereClause += "seasonalFruit = 'fall' Or ";
        }
        if (winterFruit != null && !("".equals(winterFruit))) {
            whereClause += "seasonalFruit = 'winter' Or ";
        }
        if (localFruit != null && !("".equals(localFruit))) {
            whereClause += "domesticFruit = 'local' Or ";
        }
        if (importedFruit != null && !("".equals(importedFruit))) {
            whereClause += "importedFruit = 'imported' Or ";
        }
        if (driedFruit != null && !("".equals(driedFruit))) {
            whereClause += "driedFruit = 'dried' Or ";
        }
        order = order.replaceAll(", $", "");
        whereClause = whereClause.replaceAll(" Or $", "");




//        String sortBy = request.getParameter("sortBy");
//        String order = request.getParameter("order");

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

        List<Products> list =  ShopService.getInstance().sortByFilter(pageNumber,quantityDefault,order,whereClause);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/product-list.jsp");

        request.setAttribute("order",order);
        request.setAttribute("whereClause",whereClause);
        request.setAttribute("price_sortAsc",price_sortAsc);
        request.setAttribute("price_sortDesc",price_sortDesc);
        request.setAttribute("name_sortAsc",name_sortAsc);
        request.setAttribute("name_sortDesc",name_sortDesc);
        request.setAttribute("date_sort",date_sort);
        request.setAttribute("spring",springFruit);
        request.setAttribute("summer",summerFruit);
        request.setAttribute("fall",fallFruit);
        request.setAttribute("winter",winterFruit);
        request.setAttribute("local",localFruit);
        request.setAttribute("imported",importedFruit);
        request.setAttribute("dried",driedFruit);

        request.setAttribute("admin", admin);
        request.setAttribute("haveMaxPage",haveMaxPage);
        request.setAttribute("listProduct",list);
        request.setAttribute("pageId",pageNumber);
        rd.forward(request,response);
    }
}