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
public class PharmacyApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Transaction trans = new Transaction();
        User user = new User();
        Adult adult = new Adult();
        Admin admin = new Admin();
        SeniorCitizen sc = new SeniorCitizen();
        Medicine med = new Medicine();
        Orders order = new Orders();
        int count = 0;
      user.getUserList().add(new Adult(1, "Maire", "Maire44", 18, 500));
      user.getUserList().add(new SeniorCitizen(2, "Jude", "Jude44", 62,2000));
       user.getUserList().add(new Admin("adminjude", "cabalhao"));


        med.getMedicineList().add(new BodyPain(1, "Ibuprofen + Paracetamol", "Alaxan", "Body Pain", 8.25, 100));
        med.getMedicineList().add(new BodyPain(2, "Paracetamol Caffeine", "Rexidol", "Body Pain", 15.50, 75));
        med.getMedicineList().add(new BodyPain(3, "Ibuprofen", "Medicol Advance", "Body Pain", 6.00, 100));

        med.getMedicineList().add(new Allergies(4, "Cetirizine", "Allerkid", "Allergies", 8, 50));
        med.getMedicineList().add(new Allergies(5, "Loratadine", "Allerta", "Allergies", 16, 50));
        med.getMedicineList().add(new Allergies(6, "Mometasone Furoate", "Allerta Dermatec", "Allergies", 24, 50));

        med.getMedicineList().add(new Cough(7, "Carbocisteine", "Solmux", "Cough", 17, 50));
        med.getMedicineList().add(new Cough(8, "Ambroxol", "Myracof", "Cough", 22, 50));
        med.getMedicineList().add(new Cough(9, "Ambroxol", "Expel OD", "Cough", 29, 50));


        order.getOrderList().add(new Orders(110, 1, 1, "Alaxan", 4, 33));
        order.getOrderList().add(new Orders(11, 3, 2, "Rexidol", 6, 50));
        order.getOrderList().add(new Orders(91, 2, 11, "Biogesic", 6, 40));

        for (int i = 0; i < order.getOrderList().size(); i++) {
            System.out.println("[ Int ID: " + order.getOrderList().get(i).getId() + ", User ID: " + order.getOrderList().get(i).getUserId() + ", Ordered Name: " + order.getOrderList().get(i).getOrderedName() + ", Quantity: " + order.getOrderList().get(i).getQuantity() + ", Total Amount: " + order.getOrderList().get(i).getAmount() + " ]");
        }

        while (true) {
            System.out.println("---------------***WELCOME TO JUDE'S PHARMACY***--------------\n Choices:\n |Press 1 to Login|\n |Press 2 to Register|");
            System.out.print("Enter choice: ");
            int regOrLogIn = input.nextInt();
            if (regOrLogIn == 2) {
                User newUser = user.register(user);
                user.getUserList().add(newUser);
                System.out.println("ID: " + newUser.getId() + "\nUsername: " + newUser.getUserName() + "\nPassword: " + newUser.getPassWord() + "\nAge: " + newUser.getAge() + "\nMoney: " + newUser.getMoney());
                //System.out.println(user.toString());
                for (int i = 0; i < user.getUserList().size(); i++) {
                    System.out.println("[ User ID: " + user.getUserList().get(i).getId() + ", Username: " + user.getUserList().get(i).getUserName() + ", Password: " + user.getUserList().get(i).getPassWord() + ", Age: " + user.getUserList().get(i).getAge() + ", Balance: " + user.getUserList().get(i).getMoney() + ", Registered as: " + user.getUserList().get(i).getClass().getSimpleName() + " ]");
                }
            } else if (regOrLogIn == 1) {
                User a = user.login(user);
                

                if (a instanceof Admin == true) {
                    while (true) {
                        System.out.println("Your account is now logged in as an Administrator! ");
                        System.out.println("-------------------------------------------------------------------------------");
                        System.out.println("Choose transactions:\n0. Add Medicine\n1. Remove Medicine\n2. Display Medicines\n3. View Orders\n4. Logout");
                        System.out.print("Enter your choice: ");
                        int option = input.nextInt();
                        if (option == 0) {
                            Admin jude= new Admin();
                            med.getMedicineList().add(jude.addMedicine(med));
                        } else if (option == 1) {
                            Admin jude = new Admin();
                            med.getMedicineList().remove(jude.removeMedicine(med));
                            for (int i = 0; i < med.getMedicineList().size(); i++) {
                                med.getMedicineList().get(i).setId(i + 1);
                            }
                        } else if (option == 2) {
                            System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s", "ID", "|", "Generic Name", "|", "Brand Name", "|", "Type of Medicine", "|", "Price", "|", "Stock (Pieces)\n");
                            System.out.printf("%s", "------------------------------------------------------------------------------------------------------------------------------------------------\n");
                            for (int i = 0; i < med.getMedicineList().size(); i++) {
                                System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s \n", med.getMedicineList().get(i).getId(), "|", med.getMedicineList().get(i).getGenericName(), "|", med.getMedicineList().get(i).getBrandName(), "|", med.getMedicineList().get(i).getMedicineType(), "|", med.getMedicineList().get(i).getPrice(), "|", med.getMedicineList().get(i).getStock());
                            }

                            //a.displayMedicines();
                        } else if (option == 3) {
                            System.out.printf("%10s %5s %10s %5s %25s %5s %15s %5s %10s", "ID", "|", "User ID", "|", "Ordered Generic Name", "|", "Quantity", "|", "Amount\n");
                            System.out.printf("%s", "-------------------------------------------------------------------------------------------------------------------------------\n");
                            for (int i = 0; i < order.getOrderList().size(); i++) {
                                System.out.printf("%10s %5s %10s %5s %25s %5s %15s %5s %10s \n", order.getOrderList().get(i).getId(), "|", order.getOrderList().get(i).getUserId(), "|", order.getOrderList().get(i).getOrderedName(), "|", order.getOrderList().get(i).getQuantity(), "|", order.getOrderList().get(i).getAmount());
                            }
                        } else if (option == 4) {
                            System.out.println("You are now logged out!");
                            break;
                        } else {
                            System.out.println("Invalid input!");
                        }
                    }
                } else {
                    System.out.println("Register first!!");
                    user.register(user);
                    System.out.println("You're logged in as " + a.getClass().getSimpleName() + "!");
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Choose transaction:\n0. View balance \n1. Deposit balance\n2. Order\n3. View Medicines\n4. View Orders\n5. Change Password\n6. Pay Order\n7. Log out");
                    while (true) {

                        System.out.print("Enter your choice: ");
                        int option = input.nextInt();
                        if (option == 0) {
                            System.out.println("Your balance is: Php" + a.getMoney());
                        } else if (option == 1) {
                            a.deposit(a);
                        } else if (option == 2) {
                            count++;
                            System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s", "ID", "|", "Generic Name", "|", "Brand Name", "|", "Type of Medicine", "|", "Price", "|", "Stock (Pieces)\n");
                            System.out.printf("%s", "------------------------------------------------------------------------------------------------------------------------------------------------\n");
                            for (int i = 0; i < med.getMedicineList().size(); i++) {
                                System.out.printf("%10s %5s %30s %5s %20s %5s %20s %5s %15s %5s %10s \n", med.getMedicineList().get(i).getId(), "|", med.getMedicineList().get(i).getGenericName(), "|", med.getMedicineList().get(i).getBrandName(), "|", med.getMedicineList().get(i).getMedicineType(), "|", med.getMedicineList().get(i).getPrice(), "|", med.getMedicineList().get(i).getStock());
                            }

                            int max = 150;
                            int min = 15;
                            int range = max - min + 1;
                            int num = (int) (Math.random() * range) + min;
                            System.out.println("Choose by entering item ID!");
                            System.out.print("Enter ID for the item: ");
                            int option123 = input.nextInt();
                            System.out.print("Enter quantity: ");
                            int qty = input.nextInt();
                            for (int i = 0; i < med.getMedicineList().size(); i++) {
                                if (option123 == med.getMedicineList().get(i).getId()) {
                                    if (a instanceof Adult == true) {
                                        order.getOrderList().add(new Orders(num, a.getId(), med.getMedicineList().get(i).getId(), med.getMedicineList().get(i).getBrandName(), qty, qty * med.getMedicineList().get(i).getPrice()));
                                        System.out.println("Successfully Ordered! ");

                                    } else if (a instanceof SeniorCitizen == true) {
                                        order.getOrderList().add(new Orders(num, a.getId(), med.getMedicineList().get(i).getId(), med.getMedicineList().get(i).getBrandName(), qty, qty * (med.getMedicineList().get(i).getPrice() * 0.2)));
                                        System.out.println("Successfully Ordered! ");

                                    }
                                }
                            }

                        } else if (option == 3) {
                            a.displayMedicines(med);
                        } else if (option == 4) {
                            a.displayOrders(order, a);
                        } else if (option == 5) {
                            a.changePass(a);
                        } else if (option == 6) {
                            a.displayOrders(order, a);
                            System.out.print("Choose order by Order ID: ");
                            int inputs = input.nextInt();
                            Medicine replaceMed = new Medicine(med.decMedicine(med, order, inputs));
                            for (int i = 0; i < med.getMedicineList().size(); i++) {
                                if (replaceMed.getId() == med.getMedicineList().get(i).getId()) {
                                    med.getMedicineList().get(i).setStock(replaceMed.getStock());
                                }
                            }              
                            User replaceUser = new User(a.payOrder(a, order,inputs));
                            System.out.println("Balance is: "+replaceUser.getMoney());
                            for(int i = 0; i < a.getUserList().size();i++){
                                if(replaceUser.getId() == a.getUserList().get(i).getId()){
                                    a.getUserList().get(i).setMoney(replaceUser.getMoney());
                                }
                            }
                            Orders updateOrder = new Orders();
                            order.getOrderList().remove(order.removeOrder(order, inputs));
                        } else if (option == 7) {
                            System.out.println("You are now logged out!");
                            break;
                        } else {
                            System.out.println("Input Error!");
                        }
                    }

                }

            } else {
                System.out.println("Invalid Input!");
            }

        }

    }

}