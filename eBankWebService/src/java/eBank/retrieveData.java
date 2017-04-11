/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eBank;

import java.awt.List;
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
@WebService(serviceName = "retrieveData")
public class retrieveData {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveEmployeeList")
    public ArrayList<String> retrieveEmployeeList() {
        ArrayList<String> rowList = new ArrayList<String>();
        String details = "";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            PreparedStatement statement = con.prepareStatement("select * from employeedata");
            ResultSet rs = statement.executeQuery();
//            rs.next();
            while (rs.next()) {

                for (int column = 1; column <= 3; column++) {
                    details += rs.getObject(column) + "&";
                }
                
                details = details.substring(0, details.length()-1);
                
                rowList.add(details);
                details = "";
            }

            con.close();
        } catch (SQLException e) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowList;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveCustomerList")
    public ArrayList<String> retrieveCustomerList() {
        ArrayList<String> rowList = new ArrayList<String>();
        String details = "";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            PreparedStatement statement = con.prepareStatement("select * from customer");
            
            ResultSet rs = statement.executeQuery();
            
//            rs.next();
            while (rs.next()) {

                for (int column = 1; column <= 10; column++) {
                    details += rs.getObject(column) + "&";
                }
                
                details = details.substring(0, details.length()-1);
                
                rowList.add(details);
                details = null;
            }

            con.close();
        } catch (SQLException e) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(manageEmpData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rowList;
    }

    /**
     * This is a sample web service operation
     */
}
