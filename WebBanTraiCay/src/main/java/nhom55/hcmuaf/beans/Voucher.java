package nhom55.hcmuaf.beans;

import java.time.LocalDate;

public class Voucher {
    private int id ;
    private int idUser;
    private String title;
    private String content;

    private LocalDate beginDate;
    private LocalDate endDate;
    private double price;

    public Voucher(int id, int idUser, String title, String content, LocalDate beginDate, LocalDate endDate, double price) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.content = content;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.price = price;
    }
    public Voucher(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}
