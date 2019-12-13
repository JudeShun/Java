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
import View.*;

/**
 *
 * @author 2ndyrGroupB
 */
public class Model {

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost/jude";
    final String USER = "root";
    final String PASS = "";
    int userId;
  
    public boolean register(String username, int age, String password) {
        boolean finish = false;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO `tblcustomer`(`username`, `age`, `password`) VALUES ('" + username + "'," + age + ",'" + password + "')";
            stmt.executeUpdate(sql);
            return finish = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return finish;
    }

    public int[] login(String username, String password) {
        int done = 200; // if error
        boolean finish = false;
        int[] resp = new int[2];
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `tbladmin` where username = '" + username + "' and password = '" + password + "'");
            if (rs.next()) {
                finish = true;
                resp[0] = 500;
                resp[1] = rs.getInt("id");
                return resp; // success as admin
            } else {

                ResultSet rs1 = stmt.executeQuery("SELECT * FROM `tblcustomer` where username = '" + username + "' and password = '" + password + "'");
                if (rs1.next()) {
                    this.userId = rs1.getInt("id");
//                    this.balance = rs1.getDouble("balance");
                }
//                System.out.println(this.balance);

                Viewmed vmed = new Viewmed(this.userId);
                if (rs1.getString("password") != null && rs1.getString("username") != null) {
                    finish = true;
                    resp[0] = 600;
                    resp[1] = this.userId;
                    return resp; // success as customer
                }
            }
            if (finish == false) {
                resp[0] = 200;
                return resp;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resp;
    }
//   ----------------------------------------------------------------------------------------------

    public void purchaseMedicine(int id, int quantity, int customer_id) {

        Statement stmtSelect = null;
        Connection conn = null;
        Statement stmtDelete = null;
        Statement stmtUpdate = null;
        Statement stmtInsert = null;
        double amount_paid = 0;
        String deleteQuery;
        String updateQuery;
        String selectMed;
        String insertPurchasedMed;
        selectMed = "SELECT id,stock,price,brandname from `tblmedicine` WHERE id = '" + id + "'";
        deleteQuery = "DELETE FROM `tblmedicine` WHERE brandnaidme = '" + id + "'";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmtSelect = conn.createStatement();
            ResultSet select = stmtSelect.executeQuery(selectMed);
            while (select.next()) {
                int stock = select.getInt("stock");
                double price = select.getDouble("price");
                amount_paid = quantity * price;
                String brandname = select.getString("brandname");
                if (stock < quantity) {
                    System.out.println("to order");
                    stmtDelete = conn.createStatement();
                    stmtDelete.executeUpdate(deleteQuery);
//                    insertPurchasedMed = String.format("INSERT INTO `tblorder` (user id,ordermed,quantity,amount)"
//                            + "VALUES ('%d','%s','%d','%f')", customer_id, medicine, quantity, amount_paid);
                    insertPurchasedMed = "INSERT INTO `tblorder` (`user id`, `ordermed`, `quantity`, `amount`) VALUES ('" + this.userId + "','" + brandname + "','" + quantity + "','" + amount_paid + "')";
                    stmtInsert = conn.createStatement();
                    int orderId = stmtInsert.executeUpdate(insertPurchasedMed, Statement.RETURN_GENERATED_KEYS);
                    stmtInsert = conn.createStatement();
                    String insertTransaction = String.format("INSERT INTO `tbltransactions`(`orderId`) VALUES (%d)", orderId);
                    int transId = stmtInsert.executeUpdate(insertTransaction, Statement.RETURN_GENERATED_KEYS);
                    System.out.println(orderId);
                    System.out.println(transId);

                } else if (stock >= quantity) {
                    int upqty = stock - quantity;
                    updateQuery = "UPDATE `tblmedicine` SET stock = '" + upqty + "' WHERE id = '" + id + "'";
                    stmtUpdate = conn.createStatement();
                    stmtUpdate.executeUpdate(updateQuery);
//                    insertPurchasedMed = String.format("INSERT INTO `tblorder` (user id,ordermed,quantity,amount)"
//                            + "VALUES ('%d','%s','%d','%f')", customer_id, brandname, quantity, amount_paid);
                    insertPurchasedMed = "INSERT INTO `tblorder` (`user id`, `ordermed`, `quantity`, `amount`) VALUES ('" + customer_id + "','" + brandname + "','" + quantity + "','" + amount_paid + "')";
                    stmtInsert = conn.createStatement();
                    String selectMax = "SELECT id from `tblorder`";
                    stmtSelect = conn.createStatement();
                    select = stmtSelect.executeQuery(selectMax);
                    int maxId = 0;
                    while (select.next()) {
                        maxId = select.getInt("id") + 1;
                    }

//                    select balance id = this.userId
//                    balance - amount_paid
//                    System.out.println(this.balance);
//
//                    this.balance -= amount_paid;
//                    System.out.println(this.balance);

//                    String updateBalance = "UPDATE `tblcustomer` SET balance= '" + this.balance + "' WHERE id = '" + this.userId + "'";
//                    stmtUpdate = conn.createStatement();
//                    stmtUpdate.executeUpdate(updateBalance);

                    stmtInsert.executeUpdate(insertPurchasedMed, Statement.RETURN_GENERATED_KEYS);
                    stmtInsert = conn.createStatement();
                    String insertTransaction = String.format("INSERT INTO `tbltransactions`(`orderId`) VALUES (%d)", maxId);
                    stmtInsert.executeUpdate(insertTransaction, Statement.RETURN_GENERATED_KEYS);
                    System.out.println(maxId);
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public boolean purchase(String username, int id, int qty) {
        boolean finish = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jude", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `tblmedicine` WHERE id='" + id + "'");

            while (rs.next()) {
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                if (rs.getString("id").equals(id)) {
                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM `tblcustomer`");
                    while (rs1.next()) {
                        int age = rs1.getInt("age");
                        double balance = rs1.getDouble("money");
                        double balanceLeftSenior = balance - ((stock * price) * .80);
                        double balanceLeftAdult = balance - (stock * price);
                        if (rs1.getString("username").equals(username)) {
                            System.out.println("prinitnig");
                            if (stock < qty) {
                                JOptionPane.showMessageDialog(null, "Insufficient stock!");
                            } else if (stock == qty) {
                                if (balance < price) {
                                    JOptionPane.showMessageDialog(null, "Insufficient money!");
                                } else {
                                    if (age >= 18 && age <= 59) {
                                        JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
                                        String sql1 = "UPDATE `tblcustomer` SET `balance`=" + balanceLeftAdult + " WHERE username='" + username + "'";
                                        stmt.addBatch(sql);
                                        stmt.addBatch(sql1);
                                        stmt.executeBatch();

                                        return finish = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
                                        String sql1 = "UPDATE `tblcustomer` SET `balance`=" + balanceLeftSenior + " WHERE username='" + username + "'";
                                        stmt.addBatch(sql);
                                        stmt.addBatch(sql1);
                                        stmt.executeBatch();

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
                                    String sql1 = "UPDATE `tblcustomer` SET `balance`=" + balanceLeftAdult + " WHERE username='" + username + "'";
                                    stmt.addBatch(sql);
                                    stmt.addBatch(sql1);
                                    stmt.executeBatch();

                                    return finish = true;

                                } else {
                                    JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
                                    String sql = "UPDATE `tblmedicine` SET `stock`=" + (stock - qty) + " WHERE id='" + id + "'";
                                    String sql1 = "UPDATE `tblcustomer` SET `balance`=money" + balanceLeftSenior + " WHERE username='" + username + "'";
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

//    -------------------------------------------------------------------------------------------------
//    public boolean order(String uname, int id, int qty) {
//
//        boolean success = false;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jude", "root", "");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `tblmedicine` WHERE id=" + id);
//            System.out.println("nakasulod");
//            while (rs.next()) {
//                int stock = rs.getInt("stock");
//                double price = rs.getDouble("price");
//                System.out.println("nakasulod sa medicines");
//                if (rs.getInt("id") == id) {
//                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM `tblcustomer`");
//                    while (rs1.next()) {
//                        int age = rs1.getInt("age");
//                        if (rs1.getString("username").equals(uname)) {
//                            if (rs1.getString("id").equals(id)) {
//                                if (stock < qty) {
//                                    JOptionPane.showMessageDialog(null, "Insufficient stock!");
//                                } else if (stock == qty) {
//                                    if (age >= 18 && age <= 59) {
//                                        System.out.println(age);
//                                        System.out.println(" equal then " + (qty * price));
//                                        JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
//                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
//                                        stmt.addBatch(sql);
//                                        stmt.executeBatch();
//                                        return success = true;
//                                    } else {
//                                        System.out.println(age);
//                                        System.out.println(" equal then senior " + ((qty * price) * .80));
//                                        JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
//                                        String sql = "DELETE FROM `tblmedicine` WHERE id='" + id + "'";
//                                        stmt.addBatch(sql);
//                                        stmt.executeBatch();
//                                        return success = true;
//                                    }
//                                }
//
//                            } else {
//
//                                if (age >= 18 && age <= 59) {
//                                    System.out.println(age);
//                                    System.out.println(" not equal then " + (qty * price));
//                                    JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
//                                    String sql = "UPDATE `tblmedicine` SET `stock`=" + (stock - qty) + " WHERE id='" + id + "'";
//                                    stmt.addBatch(sql);
//                                    stmt.executeBatch();
//
//                                    return success = true;
//                                } else {
//                                    System.out.println(age);
//                                    System.out.println(" not equal then senior " + ((qty * price) * .80));
//                                    JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
//                                    String sql = "UPDATE `tblmedicine` SET `stock`=" + (stock - qty) + " WHERE id='" + id + "'";
//                                    stmt.addBatch(sql);
//                                    stmt.executeBatch();
//
//                                    return success = true;
//                                }
//                            }
//                        }
//
//                    }
//                    break;
//                }
//                con.close();
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//        return success;
//
//    }
//    --------------------------------------------------------------------------------------------
}
