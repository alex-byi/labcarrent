package by.htp.jd2.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    @Column(name = "role")
    private String type;

    @Column(name = "password")
    private String password;

    @Column(name = "passportnumber")
    private String passNum;

    @Column(name = "login")
    private String login;

    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "active")
    private boolean active;

    @Column(name = "cash")
    private int cash;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                active == user.active &&
                cash == user.cash &&
                Objects.equals(type, user.type) &&
                Objects.equals(password, user.password) &&
                Objects.equals(passNum, user.passNum) &&
                Objects.equals(login, user.login) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, password, passNum, login, id, fullName, email, address, active, cash);
    }

    @Override
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", passportNumber='" + passNum + '\'' +
                ", login='" + login + '\'' +
                ", id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", cash=" + cash +
                '}';
    }
}
