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
public class Adult extends User{
    
    public Adult(){
        this(new User());
    }
    
    public Adult(String userName, String passWord, int age, double money){
        super(userName, passWord, age, money);
    }
    
    public Adult(User e){
        this(e.getId(),e.getUserName(), e.getPassWord(), e.getAge(),e.getMoney());
    }
    
    public Adult(int id, String userName, String passWord, int age, double money){
        super(id ,userName, passWord, age, money);     
    }
    
    @Override
    public String toString() {
        return String.format(super.getUserName(), super.getAge(), super.getPassWord(), super.getMoney());
    }
    
}

