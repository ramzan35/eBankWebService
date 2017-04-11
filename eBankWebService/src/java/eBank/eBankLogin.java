/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ramzan Dieze
 */
@WebService(serviceName = "eBankLogin")
public class eBankLogin {

     /**
     * Web service operation
     */
    @WebMethod(operationName = "empLogIn")
    public boolean empLogIn(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass, @WebParam(name = "isAdmin") boolean isAdmin) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebank", "root", "");
            if (isAdmin) {
                try {
                    PreparedStatement statement = con.prepareStatement("select password from employee "
                            + "where userName =  ?  AND empType = \"A\" ");
                    statement.setString(1, user);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    if (rs.getString(1).equals(pass)) {
                        return true;
                    }
                    con.close();
                } catch (SQLException e) {
//                    System.out.println(e);
                    return false;
                }
            } else {
                try {
                    PreparedStatement statement = con.prepareStatement("select password from employee "
                            + "where userName =  ? AND empType = \"M\" ");
                    statement.setString(1, user);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    if (rs.getString(1).equals(pass)) {
                        return true;
                    }
                    con.close();
                } catch (SQLException e) {
//                    System.out.println(e);
                    return false;
                }
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return false;
    }
}
