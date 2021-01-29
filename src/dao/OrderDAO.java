package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;

public class OrderDAO {

    /**
     * Retrieves a Product from the database.
     * 
     * @param productId the productId of the product to retrieve
     * @return the product retrieved from the database
     * @throws SQLException 
     */
    
    public static Order getOrder(long orderId) throws SQLException {
        Order order = null;
        
        // first, we need to establish a connection to the database. we
        // call getConnection, which will return a new connection object.
        
        Connection conn = DAO.getConnection();
        
        // a statement is a query we'll execute on the database. this
        // includes select, insert, update, and delete statements. a
        // prepared statement is a parameterized statement that allows
        // us to pass in values to predefined placeholders.
        
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE orderId = ?");
        
        // we need to provide an actual value for our placeholder.
        
        pstmt.setLong(1, orderId);
        
        // when we execute our query (a select statement), it's going to
        // return zero or more rows. we'll store that result in what is
        // called a result set.
        
        ResultSet rs = pstmt.executeQuery();
        
        // a result set has something called a cursor that points at the
        // current row. initially, this cursor is positioned before the
        // first row (i.e., it points at nothing). we need to call next
        // to tell the cursor to advance to the first row.
        //
        // next returns a boolean value. if it returns true, that means
        // the cursor successfully advanced to the next row (which, in this
        // case, is the first row).
        //
        // our query is designed to return a single row. if next returns
        // true, that means we've got a row. we'll use that row to build
        // a product object.
            
        if (rs.next()) {
            order = new Order();
            
            order.setOrderId(rs.getLong(1));
            order.setOrderDate(rs.getLong(2));
            order.setStatus(rs.getString(3));
            order.setTotal(rs.getDouble(4));
            order.setCustomer((Customer)(rs.getRef(5)).getObject());
            
        }
        
        // we're done with the result set, prepared statement, and connection
        // objects, so we should close them. this is a form of memory management.
                
        rs.close();
        pstmt.close();
        conn.close();
        
        return order;
    }
    
    /**
     * Retrieves all Products from the database.
     * 
     * @return a list of products retrieved from the database
     * @throws SQLException
     */
    
    public static List<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = DAO.getConnection();
        
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
                
        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getLong(1));
            order.setOrderDate(rs.getLong(2));
            order.setStatus(rs.getString(3));
            order.setTotal(rs.getDouble(4));
            order.setCustomer(CustomerDAO.getCustomer(rs.getLong(5)));
            
            orders.add(order);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
        return orders;
    }
    
    public static List<Order> getOrders(long customerId) throws SQLException {
    	List<Order> orders = new ArrayList<>();
        Connection conn = DAO.getConnection();
        
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM orders WHERE customerId = ?");
        
        
        pstmt.setLong(1, customerId);
        
        ResultSet rs = pstmt.executeQuery();
                
        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getLong(1));
            order.setOrderDate(rs.getLong(2));
            order.setStatus(rs.getString(3));
            order.setTotal(rs.getDouble(4));
            order.setCustomer(CustomerDAO.getCustomer(rs.getLong(5)));
            
            orders.add(order);
        }
        
        rs.close();
        pstmt.close();
        conn.close();
        
        return orders;
    }
    
    /**
     * Inserts a Product into the database.
     * 
     * @param product the product to insert into the database
     * @throws SQLException
     */
        
    public static void insertOrder(Order order) throws SQLException {        
        Connection conn = DAO.getConnection();        
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO orders (" +
            "   orderdate, " +
            "   status, " +
            "   total, " +
            "   customerid " +
            ") VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
        
        pstmt.setLong(1, order.getOrderDate());
        pstmt.setString(2, order.getStatus());
        pstmt.setDouble(3, order.getTotal());
        pstmt.setLong(4, order.getCustomer().getCustomerId());
        
        pstmt.executeUpdate();
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                order.setOrderId(generatedKeys.getLong(1));
            }
            else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        }
        pstmt.close();
        conn.close();
    }
    
    /**
     * Updates an existing Product in the database
     * 
     * @param product the new product used to update the old one
     * @throws SQLException
     */
    
    public static void updateOrder(Order order) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE orders SET " +
            "   orderDate = ?, " +
            "   status = ?, " +
            "   total = ?, " +
            "   customerid = ?, " +
            "WHERE id = ?"
        );
                
        pstmt.setLong(1, order.getOrderDate());
        pstmt.setString(2, order.getStatus());
        pstmt.setDouble(3, order.getTotal());
        pstmt.setLong(4, order.getCustomer().getCustomerId());
        pstmt.setLong(5,  order.getOrderId());
        
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    
    /**
     * Deletes an existing Product from the database.
     * 
     * @param product the product to delete
     * @throws SQLException
     */
    
    public static void deleteOrder(Order order) throws SQLException {
        Connection conn = DAO.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM orders WHERE id = ?");
        
        // we're deleting a product from the table, so we only need the primary key
        // (in this case, the id column). a primary key, which can be a combination
        // of columns (called a composite key) is the value that is guaranteed to
        // uniquely identify a row in the table.
        
        pstmt.setLong(1, order.getOrderId());
        
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}


