/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedApp;

import java.util.Scanner;

/**
 *
 * @author 2ndyrGroupB
 */
class Admin extends User {
    
    
    public Admin(){
        this(new User());
    }
    
    Scanner input = new Scanner(System.in);
    
    public Admin(String userName, String passWord){
        super(userName,passWord);
    }
    
    public Admin(User e){
        this(e.getUserName(),e.getPassWord());
    }
    
    public void error(){
        System.out.println("Sorry try again to add medicine from the start!");
    }
    
    public Medicine addMedicine(Medicine e){
        int size = e.getMedicineList().size()+1;
        System.out.print("Input Generic Name: ");
        String genName = input.nextLine();
        System.out.print("Input price: ");
        String price = input.nextLine();
        System.out.print("Input Brand Name: ");
        String bName = input.nextLine();
        System.out.print("Input Quantity: ");
        String qty = input.nextLine();
        System.out.println("Choose Type of Medicine: \n1. Body Pain\n2. Cough\n3. Allergies\n4. Headache");
        System.out.print("Choose Medicine Type: ");
        int option1 = input.nextInt();
        if(option1 == 1){
            e = new BodyPain(size,genName, bName, "Body Pain", Double.valueOf(price), Integer.valueOf(qty));
            return e;
        }
        else if(option1 == 2){
            e = new Cough(size, genName, bName, "Cough", Double.valueOf(price), Integer.valueOf(qty));
            return e;
        }
        else if(option1 == 3){
            e = new Allergies(size,genName, bName, "Allergies", Double.valueOf(price), Integer.valueOf(qty));
            return e;
        }
        else if(option1 == 4){
            e = new Headache(size,genName, bName, "Headache", Double.valueOf(price), Integer.valueOf(qty));
            return e;
        }
        else{
            error();
        }
        return e;
    }
    
    public Medicine removeMedicine(Medicine e){
        Medicine a = new Medicine();
        System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s", "ID","|","Generic Name", "|", "Brand Name", "|", "Type of Medicine", "|", "Price", "|", "Stock (Pieces)\n");
        System.out.printf("%s", "-----------------------------------------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < e.getMedicineList().size(); i++) {
            System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s \n",e.getMedicineList().get(i).getId(),"|", e.getMedicineList().get(i).getGenericName(), "|", e.getMedicineList().get(i).getBrandName(), "|", e.getMedicineList().get(i).getMedicineType(), "|", e.getMedicineList().get(i).getPrice(), "|", e.getMedicineList().get(i).getStock());
        }
        
        
        System.out.print("Input ID of the medicine: ");
        int choice = input.nextInt();
        for (int i = 0; i < e.getMedicineList().size(); i++) {
            if (e.getMedicineList().get(i).getId() == choice) {
                a = e.getMedicineList().get(i);

            }
        }  
        return a;
    }

    @Override
    public String toString() {
        return String.format(super.getUserName(), super.getAge(), super.getPassWord(), super.getMoney());
    }
    
}
