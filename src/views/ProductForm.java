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

import model.Product;

@SuppressWarnings("serial")
public class ProductForm extends JPanel {

    private JTextField productIdField;
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox<String> monthDropdown;
    private JComboBox<String> dayDropdown;
    private JComboBox<String> yearDropdown;
    private JTextField issueField;
    private JTextField priceField;
    private JTextField copiesField;
    private JLabel errorLabel;
    
    /**
     * Creates a default instance of the ProductForm class.
     */
    
    public ProductForm() {
        this(null);
    }
    
    /**
     * Creates an instance of the ProductForm class.
     * 
     * @param product the source product
     */
    
    public ProductForm(Product product) {
        this.init(product);
    }
    
    /**
     * Updates fields with actual product data.
     * 
     * @param product the product with which to update the fields
     */
    
    public void updateFields(Product product) {
        if (product == null) {
            clearFields();
            
            return;
        }
        
        productIdField.setText(String.valueOf(product.getProductId()));
        titleField.setText(product.getTitle());
        authorField.setText(product.getAuthor());
        
        String date = String.valueOf(product.getReleaseDate());
        String year = date.substring(0, 4);
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6));
        
        monthDropdown.setSelectedIndex(month);
        dayDropdown.setSelectedIndex(day);
        yearDropdown.setSelectedItem(year);
        issueField.setText(String.valueOf(product.getIssue()));
        priceField.setText(String.format("%.2f", product.getUnitPrice()));
        copiesField.setText(String.valueOf(product.getCopies()));
    }
    
    /**
     * Returns a product built from the form fields.
     * 
     * @return a product
     */
    
    public Product getProductFromFields() {
        if (productIdField.getText().trim().isEmpty()) {
            return new Product(
                titleField.getText(),
                authorField.getText(),
                parseReleaseDate(),
                Integer.parseInt(issueField.getText()),
                parseUnitPrice(),
                Integer.parseInt(copiesField.getText())
            );
        } else {
            return new Product(
                Long.parseLong(productIdField.getText()),
                titleField.getText(),
                authorField.getText(),
                parseReleaseDate(),
                Integer.parseInt(issueField.getText()),
                parseUnitPrice(),
                Integer.parseInt(copiesField.getText())
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
    
    private void init(Product product) {
        this.setLayout(null);
        
        initProductId(product);
        initTitle(product);
        initAuthor(product);
        initReleaseDate(product);
        initIssue(product);
        initPrice(product);
        initCopies(product);
        initErrorMessage();
    }
    
    private void initProductId(Product product) {
        JLabel label = new JLabel("Product ID");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 15, 100, 40);
        label.setLabelFor(productIdField);
        
        productIdField = new JTextField(10);
        productIdField.setBounds(20, 45, 710, 40);;
        productIdField.setEditable(false);
        
        this.add(label);
        this.add(productIdField);
    }
    
    private void initTitle(Product product) {
        JLabel label = new JLabel("Title");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 85, 100, 40);
        label.setLabelFor(titleField);
        
        titleField = new JTextField(10);
        titleField.setBounds(20, 115, 710, 40);
        
        this.add(label);
        this.add(titleField);
    }
    
    private void initAuthor(Product product) {
        JLabel label = new JLabel("Author");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 155, 100, 40);
        label.setLabelFor(titleField);
        
        authorField = new JTextField(10);
        authorField.setBounds(20, 185, 710, 40);
        
        this.add(label);
        this.add(authorField);
    }
    
    private void initReleaseDate(Product product) {
        JLabel label = new JLabel("Release Date");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 225, 100, 40);
        label.setLabelFor(monthDropdown);
        
        monthDropdown = new JComboBox<>(getMonths());
        monthDropdown.setBounds(20, 255, 320, 40);
        
        dayDropdown = new JComboBox<>(getDays());
        dayDropdown.setBounds(350, 255, 160, 40);
        
        yearDropdown = new JComboBox<>(getYears());
        yearDropdown.setBounds(520, 255, 210, 40);
        
        this.add(label);
        this.add(monthDropdown);
        this.add(dayDropdown);
        this.add(yearDropdown);
    }
    
    private void initIssue(Product product) {
        JLabel label = new JLabel("Issue Number");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 295, 100, 40);
        label.setLabelFor(issueField);
        
        issueField = new JTextField(10);
        issueField.setBounds(20, 325, 710, 40);
        issueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });
        
        this.add(label);
        this.add(issueField);
    }
    
    private void initPrice(Product product) {
        JLabel label = new JLabel("Unit Price");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 365, 100, 40);
        label.setLabelFor(priceField);
        
        priceField = new JTextField(10);
        priceField.setBounds(20, 395, 710, 40);
        priceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 46) {
                    if (priceField.getText().contains(".")) {
                        e.consume();
                    }
                } else if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                } else {
                    int index = priceField.getText().indexOf(".");
                    
                    if (index != -1) {
                        if (priceField.getText().substring(index).length() > 2) {
                            e.consume();
                        }
                    }
                }
            }
        });
        
        this.add(label);
        this.add(priceField);
    }
    
    private void initCopies(Product product) {
        JLabel label = new JLabel("Copies");
        label.setFont(new Font("DialogInput", Font.BOLD, 14));
        label.setBounds(25, 435, 100, 40);
        label.setLabelFor(copiesField);
        
        copiesField = new JTextField(10);
        copiesField.setBounds(20, 465, 710, 40);
        copiesField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });
        
        this.add(label);
        this.add(copiesField);
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
        productIdField.setText("");
        titleField.setText("");
        authorField.setText("");
        monthDropdown.setSelectedIndex(0);
        dayDropdown.setSelectedIndex(0);
        yearDropdown.setSelectedIndex(0);
        issueField.setText("");
        priceField.setText("");
        copiesField.setText("");
    }
    
    /*
     * Gets the list of values for the months dropdown menu.
     */
    
    private String[] getYears() {
        String[] years = new String[73];
        
        for (int i = 1949; i <= 2021; i++) {
            if (i == 1949) {
                years[0] = "Year";
            } else {
                years[i - 1949] = String.valueOf(i);
            }
        }
        
        return years;
    }
    
    private String[] getMonths() {
        return new String[]{
            "Month",
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
    }
    
    /*
     * Gets the list of values for the days dropdown menu.
     */

    private String[] getDays() {
        String[] days = new String[32];
        
        for (int i = 0; i <= 31; i++) {
            if (i == 0) {
                days[i] = "Day";
            } else {
                days[i] = String.valueOf(i);
            }
        }
        
        return days;
    }
    
    /*
     * Parses a date value into YYYYMMDD format.
     */
    
    private long parseReleaseDate() {
        int year = Integer.parseInt(yearDropdown.getSelectedItem().toString());
        int month = monthDropdown.getSelectedIndex();
        int day = dayDropdown.getSelectedIndex();
        
        return Long.parseLong(String.format("%d%02d%02d", year, month, day));
    }
    
    /*
     * Parses a numeric price from the textfield.
     */
    
    private double parseUnitPrice() {
        return Double.parseDouble(priceField.getText());
    }
}
