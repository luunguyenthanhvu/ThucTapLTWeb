package nhom55.hcmuaf.dao;

import nhom55.hcmuaf.beans.Voucher;

import java.time.LocalDate;
import java.util.List;

public interface VoucherDAO {
    public List<Voucher> getAllVoucher();
    public List<Voucher> getVoucherByIdUser(int idUser);

    public boolean deleteVoucher(int idVoucher);

    public boolean insertVoucher( int idUser, String title, String content, LocalDate beginDate, LocalDate endDate, double price);
}
