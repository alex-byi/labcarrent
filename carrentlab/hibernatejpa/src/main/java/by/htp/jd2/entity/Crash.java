package by.htp.jd2.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "crashbill")
public class Crash {

    public Crash() {
    }

    @Id
    @Column(name = "idcrashbill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String damage;

    @Column(name = "amount")
    private int amount;

    @Column(name = "complete")
    private Boolean isComplete;

    @Column(name = "cars_idcars")
    private int idCar;

    @Column(name = "users_iduser")
    private int idUser;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crash crash = (Crash) o;
        return id == crash.id &&
                amount == crash.amount &&
                idCar == crash.idCar &&
                idUser == crash.idUser &&
                Objects.equals(damage, crash.damage) &&
                Objects.equals(isComplete, crash.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, damage, amount, isComplete, idCar, idUser);
    }

    @Override
    public String toString() {
        return "Crash{" +
                "id=" + id +
                ", damage='" + damage + '\'' +
                ", amount=" + amount +
                ", isComplete=" + isComplete +
                ", idCar=" + idCar +
                ", idUser=" + idUser +
                '}';
    }
}
