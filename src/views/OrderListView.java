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
public class OrderListView extends JPanel implements ActionListener {
    
    private ViewManager manager;
    private JScrollPane scroll;
    private JButton goBack;
    
    /**
     * Creates an instance of the InventoryView class.
     * 
     * @param manager the controller
     */
    
    public OrderListView(ViewManager manager) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.init();
    }
    
    /**
     * Refreshes the inventory list.
     */
    
    public void refreshOrderList() {
        this.remove(scroll);
        
        initOrders();
    }
    
    private void init() {        
        initHeader();
        initOrders();
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
    
    private void initOrders() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        for (int i = 0; i < manager.getOrders().size(); i++) {            
            body.add(new OrderPanel(manager, manager.getOrders().get(i)));
        }
        
        scroll = new JScrollPane(body);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));
        
        goBack = new JButton("Return");
        goBack.putClientProperty("id", -1L);
        goBack.addActionListener(this);
        
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
        
        if (source.equals(goBack)) {
        	manager.switchTo(MidtownComics.InventoryView);
        }
    }
}
