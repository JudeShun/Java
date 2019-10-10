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
public class Transaction {
    
    private double money;
    private int id;
    private int id2;
    private String name;
    private int qty;
    private double amount;
    ArrayList<Transaction> uaoList = new ArrayList<Transaction>();
    
    public Transaction(){}
            
    public Transaction(double money, Orders e){
        this(money, e.getId(),e.getUserId(),e.getOrderedName(),e.getQuantity(),e.getAmount());
    }
    
    public Transaction(double money, int id, int id2, String name, int qty, double amount){
        this.money = money;
        this.id = id;
        this.id2 = id2;
        this.name = name;
        this.qty = qty;
        this.amount = amount;
    }

    public double getMoney() {
        return money;
    }

    public int getId() {
        return id;
    }

    public int getId2() {
        return id2;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public double getAmount() {
        return amount;
    }

    public ArrayList<Transaction> getUaoList() {
        return uaoList;
    }

 
}

