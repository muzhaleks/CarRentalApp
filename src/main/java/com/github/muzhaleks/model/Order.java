package com.github.muzhaleks.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Order implements Serializable {

    private long id;
    private User user;
    private Car car;
    private int rentHours;
    private double totalPrice;
    private boolean paymentStatus;
    private boolean confirmByAdminStatus;
    private Timestamp dateOfRegOrder;
    private boolean orderStatus;
    private String notes;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getRentHours() {
        return rentHours;
    }

    public void setRentHours(int rentHours) {
        this.rentHours = rentHours;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isConfirmByAdminStatus() {
        return confirmByAdminStatus;
    }

    public void setConfirmByAdminStatus(boolean confirmByAdminStatus) {
        this.confirmByAdminStatus = confirmByAdminStatus;
    }

    public Timestamp getDateOfRegOrder() {
        return dateOfRegOrder;
    }

    public void setDateOfRegOrder(Timestamp dateOfRegOrder) {
        this.dateOfRegOrder = dateOfRegOrder;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id &&
                rentHours == order.rentHours &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                paymentStatus == order.paymentStatus &&
                confirmByAdminStatus == order.confirmByAdminStatus &&
                orderStatus == order.orderStatus &&
                Objects.equals(user, order.user) &&
                Objects.equals(car, order.car) &&
                Objects.equals(dateOfRegOrder, order.dateOfRegOrder) &&
                Objects.equals(notes, order.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, car, rentHours, totalPrice, paymentStatus, confirmByAdminStatus, dateOfRegOrder, orderStatus, notes);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", rentHours=" + rentHours +
                ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", confirmByAdminStatus=" + confirmByAdminStatus +
                ", dateOfRegOrder=" + dateOfRegOrder +
                ", orderStatus=" + orderStatus +
                ", notes='" + notes + '\'' +
                '}';
    }
}
