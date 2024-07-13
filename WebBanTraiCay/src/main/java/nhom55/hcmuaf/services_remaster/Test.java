package nhom55.hcmuaf.services_remaster;

public class Test {

  public static void main(String[] args) {
    ProductService productService = new ProductService();
    productService.findAllBy().forEach(item -> System.out.println(item));
    productService.close();
  }
}
