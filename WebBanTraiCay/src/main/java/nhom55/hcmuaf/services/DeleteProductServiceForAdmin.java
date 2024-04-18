package nhom55.hcmuaf.services;


import nhom55.hcmuaf.dao.ProductDao;
import nhom55.hcmuaf.dao.daoimpl.ProductDaoImpl;

public class DeleteProductServiceForAdmin {
    private static DeleteProductServiceForAdmin instance;
    private DeleteProductServiceForAdmin() {
    }

    public static DeleteProductServiceForAdmin getInstance() {
        if (instance == null) {
            instance = new DeleteProductServiceForAdmin();
        }
        return instance;
    }
    public void deleteProduct(int idProduct) {
        ProductDao dao = new ProductDaoImpl();
        dao.deleteProduct(idProduct);
    }
}
