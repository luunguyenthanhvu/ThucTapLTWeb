package nhom55.hcmuaf.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhom55.hcmuaf.beans.Products;
import nhom55.hcmuaf.util.MyUtils;

@WebServlet(name = "TestDTOResponse", value = "/TestDTOResponse")
public class TestDTOResponse extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Products products = new Products();
    products.setImgAssetId("Long lz");
    products.setNameOfProduct("djtme lang coc");

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    try (PrintWriter out = response.getWriter()) {
      out.println(MyUtils.convertToJson(products));
      out.flush();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }
}
