package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
        
    private static Connection conn;
    private static DatabaseMetaData metadata;
    
    public static void buildDatabase() throws SQLException {
        conn = DAO.getConnection();
        metadata = conn.getMetaData();
        DAO.createProductsTable();
        DAO.createCustomersTable();
        DAO.createOrdersTable();
        DAO.createOrderItemsTable();
                
        conn.close();
    }
    
    
    
    private static void createProductsTable() throws SQLException {
        ResultSet rs = metadata.getTables(null, "USER1", "PRODUCTS", null);
        if (!rs.next()) {
            Statement stmt = conn.createStatement();
            
            stmt.execute(
                "CREATE TABLE products (" +
                "   id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "   title VARCHAR(128) NOT NULL, " +
                "   author VARCHAR(64) NOT NULL, " +
                "   releasedate BIGINT NOT NULL, " +
                "   issue INT NOT NULL, " +
                "   unitprice DECIMAL(10, 2) NOT NULL, " +
                "   copies INT NOT NULL" +
                ")"
            );
            
            insertSampleProducts();
        }
    }
    
    
    
    private static void createCustomersTable() throws SQLException {
        ResultSet rs = metadata.getTables(null, "USER1", "CUSTOMERS", null);
        if (!rs.next()) {
            Statement stmt = conn.createStatement();
            
            stmt.execute(
                "CREATE TABLE customers (" +
                "   id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "   firstname VARCHAR(64) NOT NULL, " +
                "   lastname VARCHAR(64) NOT NULL, " +
                "   phone BIGINT NOT NULL, " +
                "   email VARCHAR(255) NOT NULL, " +
                "   street VARCHAR(255) NOT NULL, " +
                "   city VARCHAR(255) NOT NULL, " +
                "   state CHAR(2) NOT NULL, " +
                "   postalcode CHAR(5) NOT NULL" +
                ")"
            );
        }
    }
    
    
    
    public static void createOrdersTable() throws SQLException {
        ResultSet rs = metadata.getTables(null, "USER1", "ORDERS", null);
        if (!rs.next()) {
            Statement stmt = conn.createStatement();
            
            stmt.execute(
                "CREATE TABLE orders (" +
                "   id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "   orderdate BIGINT NOT NULL, " +
                "   status CHAR(1) NOT NULL, " +
                "   total DECIMAL(20, 2) NOT NULL, " +
                "   customerid BIGINT references customers(id)" +
                ")"
            );
        }
    }
    
    public static void createOrderItemsTable() throws SQLException {
        ResultSet rs = metadata.getTables(null, "USER1", "ORDERITEMS", null);
                
        if (!rs.next()) {
            Statement stmt = conn.createStatement();
            
            stmt.execute(
                "CREATE TABLE orderitems (" +
                "   id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "   quantity BIGINT NOT NULL, " +
                "   orderid BIGINT references orders(id), " +
                "   productid BIGINT references products(id)" +
                ")"
            );
        }
    }
    
    private static void insertSampleProducts() throws SQLException {
        Statement stmt = conn.createStatement();
        
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19630301, 1, 19.99, 3)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19630510, 2, 19.99, 7)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19630710, 3, 19.99, 9)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19630910, 4, 19.99, 12)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19631010, 5, 19.99, 3)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19631110, 6, 19.99, 7)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19631210, 7, 19.99, 9)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19640110, 8, 19.99, 12)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19640210, 9, 19.99, 3)");
        stmt.executeUpdate("INSERT INTO products (title, author, releasedate, issue, unitprice, copies) VALUES ('The Amazing Spider-Man', 'Stan Lee', 19640310, 10, 19.99, 7)");
        
        stmt.close();
    }
    
    
    
    static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.put("user", "user1");
        props.put("password", "user1");
        return DriverManager.getConnection("jdbc:derby:midtowncomicsdb;create=true", props);
    }
}
