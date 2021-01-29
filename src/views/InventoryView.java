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

@SuppressWarnings("serial")
public class InventoryView extends JPanel implements ActionListener {
    
    private ViewManager manager;
    private JScrollPane scroll;
    private JButton addProduct;
    private JButton viewCart;
    private JButton customersButton;
    private JButton ordersButton;
    
    /**
     * Creates an instance of the InventoryView class.
     * 
     * @param manager the controller
     */
    
    public InventoryView(ViewManager manager) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.init();
    }
    
    /**
     * Refreshes the inventory list.
     */
    
    public void refreshInventoryList() {
        this.remove(scroll);
        
        initInventoryList();
    }
    
    /*
     * Initializes all UI components.
     */
    
    private void init() {        
        initHeader();
        initInventoryList();
        initFooter();
    }
    
    /*
     * Initializes the header UI components.
     */
    
    private void initHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel label = new JLabel("Midtown Comics");
        label.setFont(new Font("DialogInput", Font.BOLD, 21));
        label.setBorder(new EmptyBorder(15, 15, 10, 0));
                
        panel.add(label, BorderLayout.WEST);
        this.add(panel, BorderLayout.NORTH);
    }
    private void initInventoryList() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        for (int i = 0; i < manager.getInventory().size(); i++) {            
            body.add(new InventoryItemPanel(manager, manager.getInventory().get(i)));
        }
        
        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel subPanel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));
        
        addProduct = new JButton("Add Product");
        addProduct.putClientProperty("id", -1L);
        addProduct.addActionListener(this);
        
        customersButton = new JButton("Customers");
        customersButton.putClientProperty("id", -1L);
        customersButton.addActionListener(this);
        
        ordersButton = new JButton("Orders");
        ordersButton.putClientProperty("id", -1L);
        ordersButton.addActionListener(this);
        
        viewCart = new JButton("Proceed to Cart");
        viewCart.putClientProperty("id", -1L);
        viewCart.addActionListener(this);
        
        panel.add(addProduct, BorderLayout.WEST);
        panel.add(viewCart, BorderLayout.EAST);
        subPanel.add(ordersButton, BorderLayout.CENTER);
        subPanel.add(customersButton, BorderLayout.WEST);
        panel.add(subPanel, BorderLayout.CENTER);
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
        
        if (source.equals(addProduct)) {
            manager.switchTo(MidtownComics.ProductView);
        } else if (source.equals(viewCart)) {
            manager.switchTo(MidtownComics.CartView);
        } else if (source.equals(customersButton)) {
        	manager.switchTo(MidtownComics.ClientsView);
        } else if (source.equals(ordersButton)) {
        	manager.switchTo(MidtownComics.OrderListView);
        }
    }
}
