/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import View.*;
import Model.Model;
import static java.lang.Integer.parseInt;

/**
 *
 * @author 2ndyrGroupB
 */
public class Controller {

    Order ord = new Order();

    public boolean register(String username, String age1, String password, String password1) {
        boolean finish = false;

        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/jude";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `tblcustomer` WHERE username='" + username + "'");

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username is already taken!");
            } else {
                try {
                    int age = Integer.parseInt(age1);
                    if (age >= 18) {
                        try {
                            if (password1.equals(password) == true) {
                                try {
//                                    double money = Double.parseDouble(balance);
                                    return finish = model.register(username, age, password);
                                } catch (NumberFormatException e) {
                                    System.out.println(e);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Password do not match!");
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Minors are not allowed to register!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return finish;
    }

    public int[] loginconfirm(String username, String password) {
        int done = 200; // error
        return model.login(username, password); // call the method from the model
    }
//   -----------------------------------------------------------------------------

    Model model = new Model();

    public void CreateOrder(String id, String quantity, int customer_id) {
        if (id.equals("") || quantity.equals("") || String.valueOf(customer_id).equals("")) {
            JOptionPane.showMessageDialog(null, "Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            model.purchaseMedicine(parseInt(id), parseInt(quantity), customer_id);
        }
    }
//
//    public double getBalance(int userId) {
//        double balance = 0;
//        Connection conn = null;
//        try {
//            String myDriver = "org.gjt.mm.mysql.Driver";
//            String myUrl = "jdbc:mysql://localhost/jude";
//            Class.forName(myDriver);
//            conn = DriverManager.getConnection(myUrl, "root", "");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT balance FROM `tblcustomer` WHERE id='" + userId + "'");
//            if (rs.next()) {
//                balance = rs.getDouble("balance");
//            }
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println(e);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return balance;
//    }

//    public boolean order(String username, String medId, String quantity) {
//        boolean finish = false;
//         Model model = new Model();
//        try {
//            int id = Integer.parseInt(medId);
//            try {
//                int qty = Integer.parseInt(quantity);
//                return finish = model.purchase(username, id, qty);
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(ord, "Quantity should be a number!");
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(ord, "ID should be a number!");
//        }
//        return finish;
//    }
//    -------------------------------------------------------------------------------
//       public boolean order(String uname, String id2, String quantity) {
//        boolean success = false;
//        boolean exist = false;
//
//        try {
//
//            int qty = Integer.parseInt(quantity);
//            try {
//                int id = Integer.parseInt(id2);
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jude", "root", "");
//                    Statement stmt = con.createStatement();
//                    ResultSet rs = stmt.executeQuery("SELECT * FROM `tblmedicine` WHERE id='" + id + "'");
//
//                    while (rs.next()) {
//                        int stock = rs.getInt("stock");
//                        double price = rs.getDouble("price");
//                        if (rs.getInt("id") == id) {
//                            exist = true;
//                            return success = model.order(uname, id, qty);
//                        }
//                        break;
//                    }
//                    if (exist == false) {
//                        JOptionPane.showMessageDialog(null, "Medicine do not exist!", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//
//                } catch (NumberFormatException e) {
//                    JOptionPane.showMessageDialog(null, "ID should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//            } catch (ClassNotFoundException | SQLException e) {
//                JOptionPane.showMessageDialog(null, "Error connecting to database!", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Quantity should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        return success;
//    }
//    --------------------------------------------------------------------------------------------------
}
