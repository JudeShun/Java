/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import View.*;

/**
 *
 * @author 2ndyrGroupB
 */
public class Controller {

    Order ord = new Order();
    Model model = new Model();

    public boolean register(String username, String age1, String password, String password1, String balance) {
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
                                    double money = Double.parseDouble(balance);
                                    return finish = model.register(username, age, password, money);
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Money should be a number!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Password do not match!");
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Minors are not allowed to register!");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Age should only be a number!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database!");
        }
        return finish;
    }

    public int loginconfirm(String username, String password) {
        int done = 200; // error
        return model.login(username, password); // call the method from the model
    }
//   -----------------------------------------------------------------------------

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
