/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedApp;

import java.util.ArrayList;

/**
 *
 * @author 2ndyrGroupB
 */
public class Orders {
    
    private int id;
    private String orderedName;
    private int userId;
    private double amount;
    private int medId;
    private int quantity;
    ArrayList<Orders> orderList = new ArrayList<Orders>();
    
    public Orders(){}
    
    public Orders(int id, int userId,int medId, String orderedName, int quantity, double amount){
        this.id = id;
        this.userId = userId;
        this.medId = medId;
        this.orderedName = orderedName;
        this.quantity = quantity;
        this.amount = amount;
    }
    
    public Orders(Orders e){
        this(e.getId(), e.getUserId(),e.getMedId(), e.orderedName, e.quantity, e.getAmount());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderedName() {
        return orderedName;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }
    
    

    public void setOrderedName(String orderedName) {
        this.orderedName = orderedName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Orders> orderList) {
        this.orderList = orderList;
    }
    
    public Orders removeOrder(Orders e , int inputs){
        Orders b = new Orders();
        for (int i = 0; i < e.getOrderList().size(); i++) {
            if (e.getOrderList().get(i).getId() == inputs) {
                b = e.getOrderList().get(i);

            }
        } 
        
        return b;
    
    }

    @Override
    public String toString() {
        return String.format("%10s %5s %20s %5s %20s %5s %15s %5s %10s", id , userId, orderedName, quantity,amount);
    }
    
    
    
    
    
}
