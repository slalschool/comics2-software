package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import GUI.MidtownComics;
import controller.ViewManager;
import views.CustomerForm;

@SuppressWarnings("serial")
public class ClientsView extends JPanel implements ActionListener {
    
    private ViewManager manager;
    private JScrollPane scroll;
    private JButton addCustomer;
    private JButton goBack;
    
    /**
     * Creates an instance of the InventoryView class.
     * 
     * @param manager the controller
     */
    
    public ClientsView(ViewManager manager) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.init();
    }
    
    /**
     * Refreshes the inventory list.
     */
    
    public void refreshClientsList() {
        this.remove(scroll);  
        initClients();
    }
    
    private void init() {        
        initHeader();
        initClients();
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
    
    private void initClients() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        for (int i = 0; i < manager.getCustomers().size(); i++) {            
            body.add(new CustomerPanel(manager, manager.getCustomers().get(i)));
        }
        
        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));
        addCustomer = new JButton("Add Customer");
        addCustomer.putClientProperty("id", -1L);
        addCustomer.addActionListener(this);
        
        goBack = new JButton("Return");
        goBack.putClientProperty("id", -1L);
        goBack.addActionListener(this);
        
        panel.add(addCustomer, BorderLayout.WEST);
        panel.add(goBack, BorderLayout.EAST);
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
        
        if (source.equals(addCustomer)) {
            manager.switchTo(MidtownComics.CustomerView);
        } else if (source.equals(goBack)) {
        	manager.switchTo(MidtownComics.InventoryView);
        }
    }
}
