package by.htp.jd2.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * Order entity
 *
 * @author alexey
 */
public final class Order implements Serializable {

    private static final long serialVersionUID = 8604506722901962308L;
    private Date dateOrder;
    private Date startDate;
    private Date endDate;
    private boolean isPaid;
    private boolean isCrash;
    private int idCar;
    private int crashBill;
    private int idUser;
    private int dayCol;
    private int amount;
    private int id;
    private boolean isCanceled;
    private boolean isComplete;
    private String rejectReason;


    public Order() {
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isCrash() {
        return isCrash;
    }

    public void setCrash(boolean crash) {
        isCrash = crash;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getCrashBill() {
        return crashBill;
    }

    public void setCrashBill(int crashBill) {
        this.crashBill = crashBill;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getDayCol() {
        return dayCol;
    }

    public void setDayCol(int dayCol) {
        this.dayCol = dayCol;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
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
        return isPaid == order.isPaid &&
                isCrash == order.isCrash &&
                idCar == order.idCar &&
                crashBill == order.crashBill &&
                idUser == order.idUser &&
                dayCol == order.dayCol &&
                amount == order.amount &&
                id == order.id &&
                isCanceled == order.isCanceled &&
                isComplete == order.isComplete &&
                Objects.equals(dateOrder, order.dateOrder) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(endDate, order.endDate) &&
                Objects.equals(rejectReason, order.rejectReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOrder, startDate, endDate, isPaid, isCrash, idCar, crashBill, idUser, dayCol, amount, id, isCanceled, isComplete, rejectReason);
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateOrder=" + dateOrder +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPaid=" + isPaid +
                ", isCrash=" + isCrash +
                ", idCar=" + idCar +
                ", crashBill=" + crashBill +
                ", idUser=" + idUser +
                ", dayCol=" + dayCol +
                ", amount=" + amount +
                ", id=" + id +
                ", isCanceled=" + isCanceled +
                ", isComplete=" + isComplete +
                ", rejectReason='" + rejectReason + '\'' +
                '}';
    }
}
