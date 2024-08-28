package JPL_L_A301.Entity;

import java.util.Date;

public class Order {
    private int id;
    private Date orderDate;
    private int customerId;
    private int employeeId;
    private double total;

    public Order() {}

    public Order(int id, Date orderDate, int customerId, int employeeId, double total) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", total=" + total +
                '}';
    }
}
