package nhom55.hcmuaf.services;


import nhom55.hcmuaf.dao.ProductDao;
import nhom55.hcmuaf.dao.daoimpl.ProductDaoImpl;

import java.sql.Date;

public class UpdateProductServiceForAdmin {
    private static UpdateProductServiceForAdmin instance;
    private UpdateProductServiceForAdmin() {
    }

    public static UpdateProductServiceForAdmin getInstance() {
        if (instance == null) {
            instance = new UpdateProductServiceForAdmin();
        }
        return instance;
    }
    public void editProductNoImage(int idProduct, String name, String des,String mua, String nguonNhap,String driedFruit, double giaTien, double khoiLuong, double soKgMacDinh, Date ngayNhapKho, Date ngayHetHan, int idAdmin, int idnhaCungCap) {
        ProductDao dao = new ProductDaoImpl();
        dao.editProductNoImage(idProduct,name,des,mua,nguonNhap,driedFruit,giaTien,khoiLuong,soKgMacDinh,ngayNhapKho,ngayHetHan,idAdmin,idnhaCungCap);
    }
    public void editProductHaveImage(int idProduct, String name, String des,String mua, String nguonNhap,String driedFruit, double giaTien, double khoiLuong, double soKgMacDinh,Date ngayNhapKho, Date ngayHetHan,String tenAnh, int idAdmin, int idnhaCungCap) {
        ProductDao dao = new ProductDaoImpl();
        dao.editProductHaveImage( idProduct,  name, des, mua,nguonNhap,driedFruit, giaTien,  khoiLuong,  soKgMacDinh, ngayNhapKho,  ngayHetHan, tenAnh,  idAdmin, idnhaCungCap);
    }

}
