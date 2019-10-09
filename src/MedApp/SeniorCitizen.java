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
public class SeniorCitizen extends User{
    
    public SeniorCitizen(){
        this(new User());
    }
    
    public SeniorCitizen(String userName, String passWord, int age, double money){
        super(userName, passWord, age, money);
    }
    
    public SeniorCitizen(User e){
        this(e.getId(),e.getUserName(), e.getPassWord(), e.getAge(),e.getMoney());
    }
    
    public SeniorCitizen(int id, String userName, String passWord, int age, double money){
        super(id ,userName, passWord, age, money);     
    }
    
    
    public void purchaseWithDiscount(){}

    
    @Override
    public String toString() {
        return String.format(super.getUserName(), super.getPassWord(), super.getAge(), super.getMoney());
    }
    
    
}
