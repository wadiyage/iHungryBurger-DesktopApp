/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihungryburger.model;

/**
 *
 * @author Dell
 */
public class Burger {
    private String orderID;
    private String customerID;
    private String customerName;
    
    public static final double BURGERPRICE = 500;
    private int burgerQty;
    
    public static final int PREPARING = 0;
    public static final int DELIVERED = 1;
    public static final int CANCELED = 2;
    private int status;

    public Burger() {
    }

    public Burger(String orderID, String customerID, String customerName, int burgerQty, int status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.burgerQty = burgerQty;
        this.status = status;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the burgerQty
     */
    public int getBurgerQty() {
        return burgerQty;
    }

    /**
     * @param burgerQty the burgerQty to set
     */
    public void setBurgerQty(int burgerQty) {
        this.burgerQty = burgerQty;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
