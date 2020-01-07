package by.htp.jd2.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    public Order() {
    }

    @Id
    @Column(name = "idorder")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "dateorder")
    @Temporal(TemporalType.DATE)
    private Date dateOrder;

    @Column (name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column (name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column (name = "ispaid")
    private Boolean isPaid;

    @Column (name = "iscrash")
    private Boolean isCrash;

    @Column (name = "cars_idcar")
    private int idCar;

    @Column (name = "crashbill_idcrashbill")
    private Integer crashBill;

    @Column (name = "users_iduser")
    private int idUser;

    @Column (name = "amount")
    private int amount;

    @Column (name = "dayCol")
    private int dayCol;

    @Column (name = "iscanceled")
    private Boolean isCanceled;

    @Column (name = "iscomplete")
    private Boolean isComplete;

    @Column (name = "reject_reason")
    private String rejectReason;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getCrash() {
        return isCrash;
    }

    public void setCrash(Boolean crash) {
        isCrash = crash;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public Integer getCrashBill() {
        return crashBill;
    }

    public void setCrashBill(Integer crashBill) {
        this.crashBill = crashBill;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDayCol() {
        return dayCol;
    }

    public void setDayCol(int dayCol) {
        this.dayCol = dayCol;
    }

    public Boolean getCanceled() {
        return isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                idCar == order.idCar &&
                idUser == order.idUser &&
                amount == order.amount &&
                dayCol == order.dayCol &&
                Objects.equals(dateOrder, order.dateOrder) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(endDate, order.endDate) &&
                Objects.equals(isPaid, order.isPaid) &&
                Objects.equals(isCrash, order.isCrash) &&
                Objects.equals(crashBill, order.crashBill) &&
                Objects.equals(isCanceled, order.isCanceled) &&
                Objects.equals(isComplete, order.isComplete) &&
                Objects.equals(rejectReason, order.rejectReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, amount, dayCol, isCanceled, isComplete, rejectReason);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPaid=" + isPaid +
                ", isCrash=" + isCrash +
                ", idCar=" + idCar +
                ", crashBill=" + crashBill +
                ", idUser=" + idUser +
                ", amount=" + amount +
                ", dayCol=" + dayCol +
                ", isCanceled=" + isCanceled +
                ", isComplete=" + isComplete +
                ", rejectReason='" + rejectReason + '\'' +
                '}';
    }
}
