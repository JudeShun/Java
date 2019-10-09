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

public class Medicine {
    private String genericName;
    private String brandName;
    private String medicineType;
    private int stock;
    private double price;
    private int id;
    ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
    
    public Medicine(){}
    
    public Medicine(Medicine e){
        this(e.getId(),e.getGenericName(),e.getBrandName(),e.getMedicineType(),e.getPrice(),e.getStock());
    }
    
    public Medicine(int id, String genericName,String brandName, String medicineType,double price, int stock){
        this.brandName = brandName;
        this.genericName = genericName;
        this.medicineType = medicineType;
        this.stock = stock;
        this.price = price;
        this.id = id;
    }
    
    public Medicine(String genericName,String brandName, String medicineType,double price, int stock){
        this.brandName = brandName;
        this.genericName = genericName;
        this.medicineType = medicineType;
        this.stock = stock;
        this.price = price;
    }
    
    public Medicine(int id, Medicine e){
        this(id, e.getGenericName(),e.getBrandName(),e.getMedicineType(),e.getPrice(),e.getStock());
    }
    
    
    
    

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }
    
    public Medicine decMedicine(Medicine e, Orders a, int inputs){
        for(int i = 0; i< medicineList.size();i++){
            for(int j = 0; j<a.getOrderList().size();j++){
                if(medicineList.get(i).getId() == a.getOrderList().get(j).getMedId() && a.getOrderList().get(j).getId() == inputs){
                    if(medicineList.get(i).getStock()>a.getOrderList().get(j).getQuantity()){
                        medicineList.get(i).setStock(medicineList.get(i).getStock()-a.getOrderList().get(j).getQuantity());
                        System.out.println(medicineList.get(i).getStock());
                        e = medicineList.get(i);
                        return e;
                    }
                    else {
                        outOfStock();
                    }
                
                
                }
            }
        }
        return e;
    }
    
    public void outOfStock(){
        System.out.println("Sorry! Insufficient stock!");
    }
    
    
    @Override
    public String toString() {
        return String.format("ID: %d \nGeneric name: %s \nBrand Name: %s\nMedicine Type: %s\nPrice: %.2f\nStock: %d",id,genericName,brandName,medicineType,price,stock);
    }

    
    
}

