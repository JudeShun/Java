/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedApp;

/**
 *
 * @author 2ndyrGroupB
 */
public class BodyPain extends Medicine{
    
    public BodyPain(int id, String genericName, String brandName, String medicineType,double price, int stock) {
        super(id,genericName, brandName, medicineType,price, stock);
    }

    public BodyPain(Medicine info) {
        this(info.getId(),info.getGenericName(), info.getBrandName(), info.getMedicineType(),info.getPrice(), info.getStock());
    }
    
    public BodyPain(){
        this(new BodyPain());
    }
    
    public BodyPain(int size, Medicine e){
        super(size,e.getGenericName(), e.getBrandName(), e.getMedicineType(),e.getPrice(), e.getStock());
    }
    
    public BodyPain(String genericName, String brandName, String medicineType,double price, int stock) {
        super(genericName, brandName, medicineType,price, stock);
    }
    
    @Override
    public String toString() {
        return String.format("Generic name: %s \nBrand Name: %s\nMedicine Type: %s\nPrice: %.2f\nStock: %d",super.getGenericName(),super.getBrandName(),super.getMedicineType(),super.getPrice(),super.getStock());
    }
    
}

