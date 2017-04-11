/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eBank;

import javax.sql.rowset.WebRowSet;
import com.sun.rowset.WebRowSetImpl; // Reference implementation
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ramzan Dieze
 */
@WebService(serviceName = "manageCustData")
public class manageCustData {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addCustomer")
    public String addCustomer(@WebParam(name = "name") String name, @WebParam(name = "dob") String dob, @WebParam(name = "address") String address, @WebParam(name = "mobile") int mobile, @WebParam(name = "email") String email, @WebParam(name = "acntType") String acntType, @WebParam(name = "acntNumber") int acntNumber, @WebParam(name = "sortCode") String sortCode, @WebParam(name = "balance") double balance, @WebParam(name = "card") String card) {
        //TODO write your implementation code here:
        Connection con = null;
        try {
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "INSERT INTO `customer`(`name`, `dob`, `address`, `mobile`, `email`, `acntType`, `acntNmb`, `sortCode`, `balance`, `card`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, dob);
            preparedStmt.setString(3, address);
            preparedStmt.setInt(4, mobile);
            preparedStmt.setString(5, email);
            preparedStmt.setString(6, acntType);
            preparedStmt.setInt(7, acntNumber);
            preparedStmt.setString(8, sortCode);
            preparedStmt.setDouble(9, balance);
            preparedStmt.setString(10, card);
            // execute the preparedstatement
//            boolean result = preparedStmt.execute();
//
//            if (!result) {
//                return "Successfully added the customer to the database.";
//            }
            int affectedRows = preparedStmt.executeUpdate();

                if (affectedRows > 0) {
                    return "Successfully added the customer to the database.";
                }
            con.close();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "This account number is already taken";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageCustData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Unexpected error occured.";
    }

    @WebMethod(operationName = "editCustomer")
    public String editCustomer(@WebParam(name = "name") String name, @WebParam(name = "dob") String dob, @WebParam(name = "address") String address, @WebParam(name = "mobile") int mobile, @WebParam(name = "email") String email, @WebParam(name = "acntType") String acntType, @WebParam(name = "acntNumber") int acntNumber, @WebParam(name = "sortCode") String sortCode, @WebParam(name = "balance") double balance, @WebParam(name = "card") String card) {
        //TODO write your implementation code here:
        Connection con = null;
        try {
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            //here sonoo is database name, root is username and password

//             the mysql insert statement
//            "UPDATE `employeedata` SET `name`=name,`position`=position,`userName`=userName,`password`= password WHERE `userName`=userName";
            String query = "UPDATE `customer` SET `name`= ?,`dob`=?,`address`=?,`mobile`=?,`email`=?,`acntType`=?,`acntNmb`=?,`sortCode`=?,`balance`=?,"
                    + "`card`=? WHERE acntNmb = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, dob);
            preparedStmt.setString(3, address);
            preparedStmt.setInt(4, mobile);
            preparedStmt.setString(5, email);
            preparedStmt.setString(6, acntType);
            preparedStmt.setInt(7, acntNumber);
            preparedStmt.setString(8, sortCode);
            preparedStmt.setDouble(9, balance);
            preparedStmt.setString(10, card);
            preparedStmt.setInt(11, acntNumber);
            // execute the preparedstatement
            boolean result = preparedStmt.execute();

            if (!result) {
                return "Successfully added the employee to the database.";
            }
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return "Unexpected error occured.";
    
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteCustomer")
    public String deleteCustomer(@WebParam(name = "acntNumber") int acntNumber) {
////        try {
////            Class.forName("com.mysql.jdbc.Driver");
////            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
////
////                String query = "DELETE FROM `customer` WHERE acntNmb = ?";
////                final PreparedStatement ps = con.prepareStatement(query);
////                ps.setInt(1, acntNumber);
////                final ResultSet resultSet = ps.executeQuery();
////                if (resultSet.next()) {
////                    return "Successfully deleted";
////                }
////            }
////        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
////            
////        }
////        return "Username not found, please check the username.";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
//
//                String query = "DELETE FROM customer where acntNmb = ? ";
//                final PreparedStatement ps = con.prepareStatement(query);
//                ps.setInt(1, acntNumber);
//                final ResultSet resultSet = ps.executeQuery();
//                if (resultSet.next()) {
//                    return "Successfully deleted";
//                }
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//
//        }
//        return "Username not found, please check the username.";
//        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {

                String query = "DELETE FROM `customer` WHERE acntNmb = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, acntNumber);
                // execute the preparedstatement
//                boolean flag = preparedStmt.execute();
//                    System.out.println(flag);
//                if(flag){
//                    return "Deleted";
//                }
                int affectedRows = preparedStmt.executeUpdate();
                
                if(affectedRows > 0){
                    return "Successfully deleted";
                }
                
                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {

        }
        return "Account number not found, please check the account number.";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findCustomerAcnt")
    public boolean findCustomerAcnt(@WebParam(name = "acntNmb") int acntNmb) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {

                String query = "SELECT * FROM customer where acntNmb = ? ";
                final PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, acntNmb);
                final ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {

        }
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "findcustdata")
    public String findcustdata(@WebParam(name = "acntNmb") int acntNmb) {
        String details = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");

                String query = "SELECT * FROM customer where acntNmb = ? ";
                final PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, acntNmb);
                final ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                if (resultSet.getInt(7) == acntNmb) {
                    details = resultSet.getString(1)+"&"+resultSet.getString(2)+"&"+resultSet.getString(3)+"&"+resultSet.getInt(4)+"&"+
                              resultSet.getString(5)+"&"+resultSet.getString(6)+"&"+resultSet.getInt(7)+"&"+resultSet.getString(8)+"&"+
                              resultSet.getDouble(9)+"&"+resultSet.getString(10);
                }
                else details = "Account not found";
            
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
               details = " exception";
        }
        return details;
    }

//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "getCustDetails")
//    public WebRowSet getCustDetails(@WebParam(name = "acntNmb") String acntNmb) {
//        WebRowSet wrs = null;
//        try {
//            Connection con = null;
//            ResultSet rs = null;
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
//                PreparedStatement statement = con.prepareStatement("select * from employeeData where userName = ?");
//                statement.setString(1, acntNmb);
//                rs = statement.executeQuery();
//                rs.next();
//
//                con.close();
//            } catch (SQLException e) {
//                System.out.println("Username not found, please check the username.");
//            } catch (ClassNotFoundException e) {
//                System.err.println("Got an exception!");
//                System.err.println(e.getMessage());
//            }
//
//            wrs = new WebRowSetImpl();
//            wrs.populate(rs);
//        } catch (SQLException ex) {
//            Logger.getLogger(manageCustData.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return wrs;
//    }
}
