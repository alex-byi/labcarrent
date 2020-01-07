package by.htp.jd2.entity;

import java.io.Serializable;
import java.util.Objects;


/**
 * User entity
 *
 * @author alexey
 */
public final class User implements Serializable {

    private static final long serialVersionUID = -4899089534072772832L;
    private String login;
    private String password;
    private String fullName;
    private String passNum;
    private String email;
    private String address;
    private String type;
    private int cash;
    private boolean active;
    private int id;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
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

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cash == user.cash &&
                active == user.active &&
                id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(passNum, user.passNum) &&
                Objects.equals(email, user.email) &&
                Objects.equals(address, user.address) &&
                Objects.equals(type, user.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, fullName, passNum, email, address, type, cash, active, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", passNum='" + passNum + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", cash=" + cash +
                ", active=" + active +
                ", id=" + id +
                '}';
    }
}
