package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Customer;

@SuppressWarnings("serial")
public class CustomerForm extends JPanel {

	private JTextField customerIdField;
	private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipcodeField;
    private JLabel errorLabel;
    
    /**
     * Creates a default instance of the ProductForm class.
     */
    
    public CustomerForm() {
        this(null);
    }
    
    /**
     * Creates an instance of the ProductForm class.
     * 
     * @param product the source product
     */
    
    public CustomerForm(Customer customer) {
        this.init(customer);
    }
    
    /**
     * Updates fields with actual product data.
     * 
     * @param product the product with which to update the fields
     */
    
    public void updateFields(Customer customer) {
        if (customer == null) {
            clearFields();
            
            return;
        }
        
        customerIdField.setText(String.valueOf(customer.getCustomerId()));
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        phoneNumberField.setText(String.valueOf(customer.getPhone()));
        emailField.setText(String.valueOf(customer.getEmail()));
        addressField.setText(String.valueOf(customer.getStreetAddress()));
        cityField.setText(String.valueOf(customer.getCity()));
        stateField.setText(String.valueOf(customer.getState()));
        zipcodeField.setText(String.valueOf(customer.getPostalCode()));
    }
    
    /**
     * Returns a product built from the form fields.
     * 
     * @return a product
     */
    
    public Customer getCustomerFromFields() {
        if (customerIdField.getText().trim().isEmpty()) {
            long id = Customer.lastCustomerId++;
            return new Customer(
            		id, 
            		firstNameField.getText(),
            		lastNameField.getText(),
            		Long.parseLong(phoneNumberField.getText()),
            		emailField.getText(),
            		addressField.getText(),
            		cityField.getText(),
            		stateField.getText(),
            		zipcodeField.getText()
            		);
            
        } else {
            return new Customer(
                Long.parseLong(customerIdField.getText()),
        		firstNameField.getText(),
        		lastNameField.getText(),
        		Long.parseLong(phoneNumberField.getText()),
        		emailField.getText(),
        		addressField.getText(),
        		cityField.getText(),
        		stateField.getText(),
        		zipcodeField.getText()
            );
        }
    }
    
    /**
     * Updates the form error message.
     * 
     * @param message the new message
     */
    
    public void updateErrorMessage(String message) {
        errorLabel.setText(message);
    }
    
    private void init(Customer customer) {
        this.setLayout(null);
        
        initCustomerId(customer);
        initFirstName(customer);
        initLastName(customer);
        initPhone(customer);
        initEmail(customer);
        initAddress(customer);
        initCity(customer);
        initState(customer);
        initZipcode(customer);
        initErrorMessage();
    }

    private void initCustomerId(Customer customer) {
        JLabel label = new JLabel("Customer ID");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 0, 100, 40);
        label.setLabelFor(customerIdField);
        
        customerIdField = new JTextField(10);
        customerIdField.setBounds(20, 30, 710, 40);;
        customerIdField.setEditable(false);
        
        this.add(label);
        this.add(customerIdField);
    }
    
    private void initFirstName(Customer customer) {
        JLabel label = new JLabel("First Name");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 60, 100, 40);
        label.setLabelFor(firstNameField);
        
        firstNameField = new JTextField(10);
        firstNameField.setBounds(20, 90, 710, 40);
        
        this.add(label);
        this.add(firstNameField);
    }
    
    private void initLastName(Customer customer) {
        JLabel label = new JLabel("Last Name");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 120, 100, 40);
        label.setLabelFor(lastNameField);
        
        lastNameField = new JTextField(10);
        lastNameField.setBounds(20, 150, 710, 40);
        
        this.add(label);
        this.add(lastNameField);
    }
    
    private void initPhone(Customer customer) {
        JLabel label = new JLabel("Phone number");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 180, 100, 40);
        label.setLabelFor(phoneNumberField);
        
        phoneNumberField = new JTextField(10);
        phoneNumberField.setBounds(20, 210, 710, 40);
        phoneNumberField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });
        this.add(label);
        this.add(phoneNumberField);
    }
    
    private void initEmail(Customer customer) {
        JLabel label = new JLabel("Email");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 240, 100, 40);
        label.setLabelFor(emailField);
        
        emailField = new JTextField(20);
        emailField.setBounds(20, 270, 710, 40);
        
        this.add(label);
        this.add(emailField);
    }
    
    private void initAddress(Customer customer) {
        JLabel label = new JLabel("Address");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 300, 100, 40);
        label.setLabelFor(addressField);
        
        addressField = new JTextField(30);
        addressField.setBounds(20, 330, 710, 40);
        
        this.add(label);
        this.add(addressField);
    }
    
    private void initCity(Customer customer) {
        JLabel label = new JLabel("City");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 360, 100, 40);
        label.setLabelFor(cityField);
        
        cityField = new JTextField(15);
        cityField.setBounds(20, 390, 710, 40);

        
        this.add(label);
        this.add(cityField);
    }
    
    private void initState(Customer customer) {
        JLabel label = new JLabel("State");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 420, 100, 40);
        label.setLabelFor(stateField);
        
        stateField = new JTextField(10);
        stateField.setBounds(20, 450, 710, 40);
        
        stateField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		if (stateField.getText().length() > 1) {
        			e.consume();
        		}
        	}
        });
        
        this.add(label);
        this.add(stateField);
    }
    
    private void initZipcode(Customer customer) {
        JLabel label = new JLabel("Zip code");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 480, 100, 40);
        label.setLabelFor(zipcodeField);
        
        zipcodeField = new JTextField(15);
        zipcodeField.setBounds(20, 510, 710, 40);
        
        zipcodeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });
        
        this.add(label);
        this.add(zipcodeField);
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
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        addressField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipcodeField.setText("");
    }
    
}
