/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author 2ndyrGroupB
 */
public class Model {

    public boolean register(String username, int age, String password, double money) {
        boolean finish = false;

        try {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/jude";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO `tblcustomer`(`username`, `age`, `password`, `balance`) VALUES ('" + username + "'," + age + ",'" + password + "'," + money + ")";
            stmt.executeUpdate(sql);
            return finish = true;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database!");
        }
        return finish;
    }

    public int login(String username, String password) {
        int done = 200; // if error
        boolean finish = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jude", "root", "");
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `tbladmin` where username = '" + username + "' and password = '" + password + "'");

            if (rs.next()) {
                finish = true;
                return done = 500; // success as admin
            } else {
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM `tblcustomer` where username = '" + username + "' and password = '" + password + "'");
                rs1.next();
                if (rs1.getString("password") != null && rs1.getString("username") != null) {
                    finish = true;
                    return done = 600; // success as customer

                }
            }
            if (finish == false) {
                return done;
            }
            con.close();
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while connecting!");
        }
        return done;
    }
    
    public boolean purchase(String username, int id, int qty){
        boolean finish = false;
        
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jude", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE id='" +id+ "'");

            while (rs.next()) {
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                if (rs.getString("id").equals(id)) {
                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM `tblcustomer`");
                    while (rs1.next()) {
                        int age = rs1.getInt("age");
                        double balance = rs1.getDouble("money");
                        double  balanceLeftSenior =  balance - ((stock * price) * .80);
                        double  balanceLeftAdult =  balance- (stock * price);
                        if (rs1.getString("username").equals(username)) {
                            System.out.println("prinitnig");
                            if (stock < qty) {
                                JOptionPane.showMessageDialog(null, "Insufficient stock!");
                            } else if (stock == qty) {
                                if ( balance < price) {
                                    JOptionPane.showMessageDialog(null, "Insufficient money!");
                                } else {
                                    if (age >= 18 && age <= 59) {
                                        JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
                                        String sql1 = "UPDATE `tblcustomer` SET `money`=" + balanceLeftAdult + " WHERE username='" + username + "'";
                                        stmt.addBatch(sql);
                                        stmt.addBatch(sql1);
                                        stmt.executeBatch();
                                        con.close();
                                        return finish = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
                                        String sql1 = "UPDATE `tblcustomer` SET `money`=" + balanceLeftSenior + " WHERE username='" + username + "'";
                                        stmt.addBatch(sql);
                                        stmt.addBatch(sql1);
                                        stmt.executeBatch();
                                        con.close();
                                        return finish = true;
                                    }
                                }
                            }
                        } else {
                            if (balance < price) {
                                JOptionPane.showMessageDialog(null, "Insufficient money!");
                            } else {
                                if (age >= 18 && age <= 59) {
                                    JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
                                    String sql = "UPDATE `tblmedicine` SET `stock`=" + (stock - qty) + " WHERE id='" + id + "'";
                                    String sql1 = "UPDATE `tblcustomer` SET `money`=" + balanceLeftAdult + " WHERE username='" + username + "'";
                                    stmt.addBatch(sql);
                                    stmt.addBatch(sql1);
                                    stmt.executeBatch();
                                   
                                    return finish = true;

                                } else {
                                    JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
                                    String sql = "UPDATE `tblmedicine` SET `stock`=" + (stock - qty) + " WHERE id='" + id + "'";
                                    String sql1 = "UPDATE `tblcustomer` SET `money`=" + balanceLeftSenior + " WHERE username='" + username + "'";
                                    stmt.addBatch(sql);
                                    stmt.addBatch(sql1);
                                    stmt.executeBatch();
                                    return finish = true;
                                }
                            }
                        }
                    }
                }
                break;
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database!");
        }

        return finish;
    }

}
