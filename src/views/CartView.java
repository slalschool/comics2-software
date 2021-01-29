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
public class CartView extends JPanel implements ActionListener {
    
    private ViewManager manager;
    private JScrollPane scroll;
    private JButton back;
    private JButton checkout;
    
    /**
     * Creates an instance of the CartView class.
     * 
     * @param manager the controller
     */
    
    public CartView(ViewManager manager) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.init();
    }
    
    /**
     * Refreshes the contents of the cart.
     */
    
    public void refreshCart() {
        this.remove(scroll);
        
        initCart();
    }
    
    private void init() {        
        initHeader();
        initCart();
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
    
    private void initCart() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        if (manager.getOrder() != null) {
            for (int i = 0; i < manager.getOrder().getItems().size(); i++) {            
                body.add(new CartItemPanel(manager, manager.getOrder().getItems().get(i).getProduct()));
            }
        }
        
        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        back = new JButton("Continue Shopping");
        back.addActionListener(this);
        
        checkout = new JButton("Checkout");
        checkout.addActionListener(this);
        
        panel.add(back, BorderLayout.WEST);
        panel.add(checkout, BorderLayout.EAST);
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
        
        if (source.equals(back)) {
            manager.switchTo(MidtownComics.InventoryView);
        } else if (source.equals(checkout)) {
            if (manager.getOrder() != null && manager.getOrder().getItems().size() > 0) {
            	manager.updateTotal();
                manager.switchTo(MidtownComics.PaymentView);
            }
        }
    }
}
