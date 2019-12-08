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
               ResultSet rs1= stmt.executeQuery("SELECT * FROM `tbladmin` where username = '" + username + "' and password = '" + password + "'");
                if (rs1.next()) {
                    if (rs1.getString("password").equals(password)) {
                        finish = true;
                        return done = 600; // success as customer
                    }
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

}
