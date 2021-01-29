package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.CustomerDAO;
import model.Order;
import model.OrderItem;

@SuppressWarnings("serial")
public class OrderForm extends JPanel {

    private JTextField orderIdField;
    private JTextField customerIdField;
    private JTextField orderDateField;
    private JTextField orderStatusField;
    private JTextField orderTotalField;
    private JLabel errorLabel;
    
    /**
     * Creates a default instance of the ProductForm class.
     */
    
    public OrderForm() {
        this(null);
    }
    
    /**
     * Creates an instance of the ProductForm class.
     * 
     * @param product the source product
     */
    
    public OrderForm(Order order) {
        this.init(order);
    }
    
    /**
     * Updates fields with actual product data.
     * 
     * @param product the product with which to update the fields
     */
    
    public void updateFields(Order order) {
        if (order == null) {
            clearFields();
            
            return;
        }
        
        orderIdField.setText(String.valueOf(order.getOrderId()));
        orderDateField.setText(String.valueOf(parseDate(order.getOrderDate())));
        orderStatusField.setText(order.getStatus());
        orderTotalField.setText(String.valueOf(order.getTotal()));
        customerIdField.setText(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName());
        
    }
    
    /**
     * Returns a product built from the form fields.
     * 
     * @return a product
     * @throws SQLException 
     * @throws NumberFormatException 
     */
    
    public Order getOrderFromFields() throws NumberFormatException, SQLException {
            return new Order(
                Long.parseLong(orderIdField.getText()),
                CustomerDAO.getCustomer(Long.valueOf(customerIdField.getText())),
                Long.parseLong(orderDateField.getText()),
                orderStatusField.getText(),
                new ArrayList<OrderItem>(),
                parseTotal()
            );
    }
    
    /**
     * Updates the form error message.
     * 
     * @param message the new message
     */
    
    public void updateErrorMessage(String message) {
        errorLabel.setText(message);
    }
    
    private void init(Order order) {
        this.setLayout(null);
        
        initOrderId(order);
        initOrderDate(order);
        initStatus(order);
        initTotal(order);
        initCustomerField(order);
        initErrorMessage();
    }
    
    private void initOrderId(Order order) {
        JLabel label = new JLabel("Order Id");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(orderIdField);
        
        orderIdField = new JTextField(10);
        orderIdField.setBounds(20, 45, 710, 40);;
        orderIdField.setEditable(false);
        
        this.add(label);
        this.add(orderIdField);
    }
    
    private void initOrderDate(Order order) {
        JLabel label = new JLabel("Order Date");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 85, 100, 40);
        label.setLabelFor(orderDateField);
        
        orderDateField = new JTextField(10);
        orderDateField.setBounds(20, 115, 710, 40);;
        orderDateField.setEditable(false);
        
        this.add(label);
        this.add(orderDateField);
    }
    
    private void initStatus(Order order) {
        JLabel label = new JLabel("Order Status");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 155, 100, 40);
        label.setLabelFor(orderStatusField);
        
        orderStatusField = new JTextField(10);
        orderStatusField.setBounds(20, 185, 710, 40);;
        orderStatusField.setEditable(false);
        
        this.add(label);
        this.add(orderStatusField);
    }
    
    private void initTotal(Order order) {
        JLabel label = new JLabel("Order Status");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 225, 100, 40);
        label.setLabelFor(orderTotalField);
        
        orderTotalField = new JTextField(10);
        orderTotalField.setBounds(20, 255, 710, 40);;
        orderTotalField.setEditable(false);
        
        this.add(label);
        this.add(orderTotalField);
    }
    
    private void initCustomerField(Order order) {
        JLabel label = new JLabel("Customer");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 295, 100, 40);
        label.setLabelFor(customerIdField);
        
        customerIdField = new JTextField(10);
        customerIdField.setBounds(20, 325, 710, 40);;
        customerIdField.setEditable(false);
        
        this.add(label);
        this.add(customerIdField);
    }
    
    private void initErrorMessage() {
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(20, 540, 710, 40);
        
        this.add(errorLabel);
    }
    
    /*
     * Resets all UI fields to their default values.
     */
    
    private void clearFields() {
        orderIdField.setText("");
        orderDateField.setText("");
        orderStatusField.setText("");
        orderTotalField.setText("");
        customerIdField.setText("");
    }
    
    /*
     * Parses a date value into YYYYMMDD format.
     */
    
    private long parseDate(Long numberDate) {
    	String date = String.valueOf(numberDate);
        int year = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(2,4));
        int day = Integer.parseInt(date.substring(4));
        
        return Long.parseLong(String.format("%d%02d%02d", year, month, day));
    }
    
    /*
     * Parses a numeric price from the textfield.
     */
    
    private double parseTotal() {
        return Double.parseDouble(orderTotalField.getText());
    }
}
