package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDAO {

    
    
    public static Customer getCustomer(long customerID) throws SQLException {
        Customer customer = null;
        
        Connection conn = DAO.getConnection();
        
        
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
        
        
        pstmt.setLong(1, customerID);

        
        ResultSet rs = pstmt.executeQuery();
            
        if (rs.next()) {
            customer = new Customer();
            
            customer.setCustomerId(rs.getLong(1));
            customer.setFirstName(rs.getString(2));
            customer.setLastName(rs.getString(3));
            customer.setPhone(rs.getLong(4));
            customer.setEmail(rs.getString(5));
            customer.setStreetAddress(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setState(rs.getString(8));
            customer.setPostalCode(rs.getString(9));
            
            
        }
                
        rs.close();
        pstmt.close();
        conn.close();
        
        return customer;
    }
    
    
    
    public static List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = DAO.getConnection();
        
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
                
        while (rs.next()) {
        	Customer customer = new Customer();
            customer.setCustomerId(rs.getLong(1));
            customer.setFirstName(rs.getString(2));
            customer.setLastName(rs.getString(3));
            customer.setPhone(rs.getLong(4));
            customer.setEmail(rs.getString(5));
            customer.setStreetAddress(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setState(rs.getString(8));
            customer.setPostalCode(rs.getString(9));
            customers.add(customer);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return customers;
    }
    
    
        
    public static void insertCustomer(Customer customer) throws SQLException {        
        Connection conn = DAO.getConnection();        
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO customers (" +
            "   firstname, " +
            "   lastname, " +
            "   phone, " +
            "   email, " +
            "   street, " +
            "   city, " +
            "   state, " +
            "   postalcode" + 
            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
        
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2,  customer.getLastName());
        pstmt.setLong(3, customer.getPhone());
        pstmt.setString(4, customer.getEmail());
        pstmt.setString(5, customer.getStreetAddress());
        pstmt.setString(6, customer.getCity());
        pstmt.setString(7, customer.getState());
        pstmt.setString(8, customer.getPostalCode());
        
        
        pstmt.executeUpdate();
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                customer.setCustomerId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }
        }

        pstmt.close();
        conn.close();
    }
    
    
    
    public static void updateCustomer(Customer customer) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE customers SET " +
                "   firstname = ?, " +
                "   lastname = ?, " +
                "   phone = ?, " +
                "   email = ?, " +
                "   street = ?, " +
                "   city = ?, " +
                "   state = ?, " +
                "   postalcode = ? " +
                "WHERE id = ?"
            );
                
        pstmt.setString(1, customer.getFirstName());
        pstmt.setString(2,  customer.getLastName());
        pstmt.setLong(3, customer.getPhone());
        pstmt.setString(4, customer.getEmail());
        pstmt.setString(5, customer.getStreetAddress());
        pstmt.setString(6, customer.getCity());
        pstmt.setString(7, customer.getState());
        pstmt.setString(8, customer.getPostalCode());
        pstmt.setLong(9, customer.getCustomerId());
        
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    
    
    
    public static void deleteCustomer(Customer customer) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM customers WHERE id = ?");
        
        pstmt.setLong(1, customer.getCustomerId());
        
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}


