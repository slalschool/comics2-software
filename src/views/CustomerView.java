package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import GUI.MidtownComics;
import controller.ViewManager;
import model.Customer;

@SuppressWarnings("serial")
public class CustomerView extends JPanel implements ActionListener {

    private ViewManager manager;
    private Customer customer;
    private CustomerForm customerForm;
    private JButton save;
    private JButton remove;
    private JButton cancel;

    /**
     * Creates an instance of the ProductView class.
     * 
     * @param manager the controller
     */
    
    public CustomerView(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.customerForm = new CustomerForm();
        this.init();
    }
    
    /**
     * Sets the Product associated with this view.
     *
     * @param product the new product
     */

    public void setCustomer(Customer customer) {
        this.customer = customer;
        
        remove.setEnabled(true);
        customerForm.updateFields(customer);
    }

    private void init() {        
        initHeader();
        initCustomerForm();
        initFooter();
    }

    private void initHeader() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Midtown Comics");
        label.setFont(new Font("DialogInput", Font.BOLD, 21));
        label.setBorder(new EmptyBorder(15, 15, 10, 0));

        panel.add(label, BorderLayout.WEST);
        this.add(panel, BorderLayout.NORTH);
    }
    private void initCustomerForm() {
        this.add(new JScrollPane(customerForm), BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        
        remove = new JButton("Remove");
        remove.setEnabled(false);
        remove.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);

        panel.add(cancel);
        panel.add(remove);
        panel.add(save);
        this.add(panel, BorderLayout.SOUTH);
    }

    /*
     * Handles button clicks in this view.
     *
     * @param e the event that triggered this action
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        
        if (source.equals(save)) {
            if (customer == null) {
                manager.addCustomerToClients(customerForm.getCustomerFromFields());
            } else {
                manager.modifyCustomerInClients(customerForm.getCustomerFromFields());
            }
            manager.switchTo(MidtownComics.ClientsView);
        } else if (source.equals(remove)) {
            manager.removeCustomerFromClients(customer);
        } else if (source.equals(cancel)) {
            manager.detachCustomer();
            manager.switchTo(MidtownComics.ClientsView);
        }
    }
}