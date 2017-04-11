/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eBank;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ramzan Dieze
 */
@WebService(serviceName = "manageEmpData")
public class manageEmpData {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteEmployee")
    public String deleteEmployee(@WebParam(name = "userName") String userName) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
//
////                String query = "DELETE FROM `employeedata` WHERE userName = ?";
////                final PreparedStatement ps = con.prepareStatement(query);
////                ps.setString(1, userName);
////                final ResultSet resultSet = ps.executeQuery();
//
//                String query = "DELETE FROM `employeedata` WHERE userName = ?";
//                PreparedStatement preparedStmt = con.prepareStatement(query);
//                preparedStmt.setString(1, userName);
//                // execute the preparedstatement
//                preparedStmt.execute();
//
//                con.close();
////                if (resultSet.next()) {
////                    return "Successfully deleted";
////                }
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//
//        }
//        return "Username not found, please check the username.";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {

                String query = "DELETE FROM `employeedata` WHERE userName = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, userName);
                // execute the preparedstatement
//                boolean flag = preparedStmt.execute();
//                    System.out.println(flag);
//                if(flag){
//                    return "Deleted";
//                }
                int affectedRows = preparedStmt.executeUpdate();

                if (affectedRows > 0) {
                    return "Successfully deleted";
                }

                con.close();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {

        }
        return "Username not found, please check the username.";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createEmployee")
    public String createEmployee(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "userName") String userName, @WebParam(name = "password") String password) {
        Connection con = null;
        try {
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = " insert into employeeData (name, position, userName, password)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, position);
            preparedStmt.setString(3, userName);
            preparedStmt.setString(4, password);
            // execute the preparedstatement
//            boolean result = preparedStmt.execute();
//
//            if (!result) {
//                return "Successfully added the employee to the database.";
//            }
            int affectedRows = preparedStmt.executeUpdate();
//
            String query2 = " insert into employee (userName, password, empType) values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt2 = con.prepareStatement(query2);
            preparedStmt2.setString(1, userName);
            preparedStmt2.setString(2, password);
            
            if(position.equalsIgnoreCase("CEO") || position.equalsIgnoreCase("MANAGER"))
                preparedStmt2.setString(3, "A");
            else
                preparedStmt2.setString(3, "M");
            
            // execute the preparedstatement
            preparedStmt2.executeUpdate();

//            boolean result = preparedStmt.execute();
//
//            if (!result) {
//                return "Successfully added the employee to the database.";
//            }            
            
                if (affectedRows > 0) {
                    return "Successfully added.";
                }
            con.close();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return "This username is already taken";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Unexpected error occured.";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateEmp")
    public String updateEmp(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "username") String username, @WebParam(name = "pass") String pass) {
        Connection con = null;
        try {
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            //here sonoo is database name, root is username and password

//             the mysql insert statement
//            "UPDATE `employeedata` SET `name`=name,`position`=position,`userName`=userName,`password`= password WHERE `userName`=userName";
            String query = "UPDATE `employeedata` SET `name`= ?,`position`= ?,`userName`= ?,`password`= ? WHERE `userName`= ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, position);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, pass);
            preparedStmt.setString(5, username);
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
//    @WebMethod(operationName = "getEmpDetails")
//    public ResultSet getEmpDetails(@WebParam(name = "userName") String userName) {
//        Connection con = null;
//        ResultSet rs = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
//            PreparedStatement statement = con.prepareStatement("select * from employeeData where userName = ?");
//            statement.setString(1, userName);
//            rs = statement.executeQuery();
//            rs.next();
//            
//            con.close();
//        }catch(SQLException  e){
//            System.out.println("Username not found, please check the username.");
//        } catch (ClassNotFoundException e) {
//            System.err.println("Got an exception!");
//            System.err.println(e.getMessage());
//        }
//        return rs;
//    }
    /**
     * Web service operation
     */
//    @WebMethod(operationName = "updateEmployee")
//    public String updateEmployee(@WebParam(name = "name") String name, @WebParam(name = "position") String position, @WebParam(name = "userName") String userName, @WebParam(name = "password") String password) {
//        Connection con = null;
//        try {
//            // create a mysql database connection
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
//            //here sonoo is database name, root is username and password
//
//            // the mysql insert statement
//            //"UPDATE `employeedata` SET `name`=name,`position`=position,`userName`=userName,`password`= password WHERE `userName`=userName";
////            String query = "UPDATE `employeedata` SET `name`= ?,`position`= ?,`userName`= ?,`password`= ? WHERE `userName`= ?";
////
////            // create the mysql insert preparedstatement
////            PreparedStatement preparedStmt = con.prepareStatement(query);
////            preparedStmt.setString(1, name);
////            preparedStmt.setString(2, position);
////            preparedStmt.setString(3, userName);
////            preparedStmt.setString(4, password);
////            preparedStmt.setString(5, userName);
////            // execute the preparedstatement
////            boolean result = preparedStmt.execute();
//
////            if (!result) {
////                return "Successfully added the employee to the database.";
////            }
//            con.close();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Got an exception!");
//            System.err.println(e.getMessage());
//        }
//        return "Unexpected error occured.";
//    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "findEmpDetails")
    public boolean findEmpDetails(@WebParam(name = "userName") String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {

                String query = "SELECT * FROM customer where acntNmb = ? ";
                final PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, userName);
                final ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {

        }
        return false;
    }
    
//    @WebMethod(operationName = "findempDetail")
//    public ArrayList<String> findempDetail(@WebParam(name = "userName") String userName) {
//        
//        ArrayList<String> empDetails = new ArrayList<String>();
//        String details = null;
//        Employee emp;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
//
//                String query = "SELECT * FROM customer where acntNmb = ? ";
//                final PreparedStatement ps = con.prepareStatement(query);
//                ps.setString(1, userName);
//                final ResultSet resultSet = ps.executeQuery();
////                if (resultSet.next()) {
////                    return true;
////                }
//                emp = new Employee();
//                resultSet.next();
//                for(int i = 0; i < 4; i++){
//                    details+=resultSet.getString(i+"&");
//                }
//
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//
//        }
//        return empDetails;
//    }
    
    @WebMethod(operationName = "findempdata")
    public String findempdata(@WebParam(name = "userName") String userName) {
        
        String details = "";
        
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
//
//                String query = "SELECT * FROM employeedata where userName = ? ";
//                final PreparedStatement ps = con.prepareStatement(query);
//                ps.setString(1, userName);
//                final ResultSet resultSet = ps.executeQuery();
////                if (resultSet.next()) {
////                    return true;
////                }
//                
//                String temp = null;
//                for(int i = 1; i <= 4; i++){
//                    temp = resultSet.getString(i);
//                    details += temp;
//                    System.out.print(temp);
//                }
//
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//            System.out.print(details);
//        }
            System.out.print("user name : "+userName);
            Connection con = null;
            try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            PreparedStatement statement = con.prepareStatement("select * from employeeData where userName = ?");
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            if (rs.getString(3).equals(userName)) {
                details = rs.getString(1)+"&"+rs.getString(2)+"&"+rs.getString(3)+"&"+rs.getString(4);
            }
            else{
                details = "user not found";
            }
            con.close();
        }catch(SQLException  e){
            System.out.println("Username not found, please check the username.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return details;
    }

//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "empData")
//    public Employee empData(@WebParam(name = "userName") String userName) {
//        
//        Employee emp = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "")) {
//
//                String query = "SELECT * FROM customer where acntNmb = ? ";
//                final PreparedStatement ps = con.prepareStatement(query);
//                ps.setString(1, userName);
//                final ResultSet resultSet = ps.executeQuery();
//                
//                emp = new Employee();
//                resultSet.next();
//                emp.setName(resultSet.getString(1));
//                emp.setPosition(resultSet.getString(2));
//                emp.setUserName(resultSet.getString(3));
//                emp.setPassword(resultSet.getString(4));
//
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//
//        }
//        return emp;
//    }
//    
}
