package com.violence.entity;

import com.violence.util.api.annotation.Column;
import java.util.Objects;

public class User implements DomainObject<User> {
    @Column("user_id")
    private Long id;
    @Column("login")
    private String login;
    @Column("user_name")
    private String name;
    @Column("user_surname")
    private String surname;
    @Column("password")
    private String password;
    @Column("email")
    private String email;
    @Column("phone")
    private String phone;
    @Column("role")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, name, surname, password, email, phone, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public String getFieldVsValue() {
        return  "id = '" + id + '\'' +
                ", login = '" + login + '\'' +
                ", name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                ", password = '" + password + '\'' +
                ", email = '" + email + '\'' +
                ", phone = '" + phone + '\'' +
                ", role = '" + role + '\'';
    }

//    @Override
//    public User getObject(ResultSet resultSet) throws SQLException {
//        User user = new User();
//
//        user.setId(resultSet.getLong("user_id"));
//        user.setName(resultSet.getString("user_name"));
//        user.setLogin(resultSet.getString("login"));
//        user.setPassword(resultSet.getString("password"));
//        user.setEmail(resultSet.getString("email"));
//        user.setPhone(resultSet.getString("phone"));
//        user.setSurname(resultSet.getString("user_surname"));
//
//        return user;
//    }
}
