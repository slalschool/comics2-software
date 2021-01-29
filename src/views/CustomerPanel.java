package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import GUI.MidtownComics;
import controller.ViewManager;
import model.Customer;
import model.OrderItem;
import model.Product;

@SuppressWarnings("serial")
public class CustomerPanel extends JPanel implements ActionListener {
    
    private ViewManager manager;

    /**
     * Creates an instance of the InventoryItemPanel class.
     * 
     * @param manager the controller
     * @param product the product represented by this panel
     */
    
    public CustomerPanel(ViewManager manager, Customer customer) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.init(customer);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
    }

    private void init(Customer c) {
        JPanel content = getContentPanel(c);
        JPanel actions = getActionPanel(c);
        
        this.add(content, BorderLayout.CENTER);
        this.add(actions, BorderLayout.EAST);
        this.add(new JSeparator(), BorderLayout.SOUTH);
    }
    
    private JPanel getContentPanel(Customer c) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
       
        JLabel name = new JLabel(c.getFirstName() + " " + c.getLastName());
        JLabel address = new JLabel("Address: " + (c.getStreetAddress() + " " + c.getCity() + ", " + c.getState() + ", " + c.getPostalCode()));
        JLabel email = new JLabel("Email: " + c.getEmail());
        JLabel phone = new JLabel("Phone: " + c.getPhone());
        
        name.setFont(new Font("DialogInput", Font.BOLD, 18));
        email.setFont(new Font("DialogInput", Font.ITALIC, 12));
        phone.setFont(new Font("DialogInput", Font.ITALIC, 12));
        address.setFont(new Font("DialogInput", Font.ITALIC, 12));
        
        panel.add(name);
        panel.add(address);
        panel.add(phone);
        panel.add(email);
        return panel;
    }
    
    private JPanel getActionPanel(Customer c) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        JButton edit = new JButton("Edit");
        edit.putClientProperty("id", c.getCustomerId());
        edit.putClientProperty("type", "EDIT");
        edit.addActionListener(this);

        panel.add(edit);
        
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Long id = (Long) source.getClientProperty("id");
        String type = (String) source.getClientProperty("type");
        
        for (Customer c : manager.getCustomers()) {
            if (id.longValue() == c.getCustomerId() && type.equals("EDIT")) {
                manager.attachCustomer(c);
                manager.switchTo(MidtownComics.CustomerView);
            }
        }
        
    }
}
