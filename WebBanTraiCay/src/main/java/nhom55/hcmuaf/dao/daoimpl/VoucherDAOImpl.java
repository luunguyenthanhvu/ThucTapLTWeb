package nhom55.hcmuaf.dao.daoimpl;

import nhom55.hcmuaf.beans.Voucher;
import nhom55.hcmuaf.dao.VoucherDAO;
import nhom55.hcmuaf.database.JDBIConnector;

import java.time.LocalDate;
import java.util.List;

public class VoucherDAOImpl implements VoucherDAO {
    @Override
    public List<Voucher> getAllVoucher() {
        return JDBIConnector.get().withHandle(handle -> {
            return handle
                    .createQuery("SELECT * FROM voucher")
                    .mapToBean(Voucher.class)
                    .list();
        });
    }

    @Override
    public List<Voucher> getVoucherByIdUser(int idUser) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle
                    .createQuery("SELECT * FROM voucher WHERE idUser = :id AND endDate > CURRENT_DATE")
                    .bind("id", idUser)
                    .mapToBean(Voucher.class)
                    .list();
        });
    }

    @Override
    public boolean deleteVoucher(int idVoucher) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle
                    .createUpdate("DELETE FROM voucher WHERE id = :id")
                    .bind("id", idVoucher)
                    .execute() == 1;
        });
    }

    @Override
    public boolean insertVoucher( int idUser, String title, String content, LocalDate beginDate, LocalDate endDate, double price) {
        return JDBIConnector.get().withHandle(handle -> {
            return handle
                    .createUpdate("INSERT INTO voucher (idUser,title,content,beginDate,endDate,price) VALUES (:idUser,:title,:content,:beginDate,:endDate,:price)")
                    .bind("title", title)
                    .bind("content", content)
                    .bind("beginDate", beginDate)
                    .bind("endDate", endDate)
                    .bind("price", price)
                    .bind("idUser", idUser)
                    .execute() == 1;
        });
    }

}
