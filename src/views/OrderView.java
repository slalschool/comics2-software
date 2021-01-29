//package views;
//
//import java.awt.BorderLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//import model.Order;
//import controller.ViewManager;
//
//@SuppressWarnings("serial")
//public class OrderView extends JPanel implements ActionListener {
//    
//    private ViewManager manager;
//    private PaymentForm form;
//    private JLabel total;
//    private JButton submit;
//    private Order order;
//    private OrderForm orderForm;
//    
//    public OrderView(ViewManager manager) {
//        super(new BorderLayout());
//        
//        this.manager = manager;
//        this.orderForm = new OrderForm();
//        this.init();
//    }
//    
//
//
//    /**
//     * Updates the order total label.
//     * 
//     * @param total the new total
//     */
//    
//    public void updateOrderTotal(double total) {
//        this.total.setText("Order Total: $" + total);
//    }
//    
//    public void setOrder(Order order) {
//    	this.order = order;
//    	orderForm.updateFields(order);
//    }
//    
//    /**
//     * Clears all fields.
//     */
//    
//    public void clearOrder() {
//        total.setText("");
//        form.clearFields();
//    }
//    
//    /*
//     * Initializes all UI components.
//     */
//
//    private void init() {
//        submit = new JButton("Submit");
//        submit.addActionListener(this);
//        
//        total = new JLabel("Order Total: $", SwingConstants.RIGHT);
//        total.setFont(new Font("DialogInput", Font.BOLD, 16));
//        total.setBorder(new EmptyBorder(10, 10, 10, 10));
//        
//        form = new PaymentForm();
//        
//        this.add(total, BorderLayout.NORTH);
//        this.add(form, BorderLayout.CENTER);
//        this.add(submit, BorderLayout.SOUTH);
//    }
//    
//    /*
//     * Handles button clicks in this view.
//     *
//     * @param e the event that triggered this action
//     */
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JButton source = (JButton) e.getSource();
//        
//        if (source.equals(submit)) {
//            try {
//				manager.submitOrder();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//        }
//    }
//}

package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import GUI.MidtownComics;
import controller.ViewManager;
import dao.OrderDAO;
import model.Order;
import views.CustomerForm;

@SuppressWarnings("serial")
public class OrderView extends JPanel implements ActionListener {
    
    private ViewManager manager;
    private JScrollPane scroll;
    private OrderForm orderForm;
    private Order order;
    private JButton cancel;
    private JButton remove;
    private JButton save;
    
    /**
     * Creates an instance of the InventoryView class.
     * 
     * @param manager the controller
     */
    
    public OrderView(ViewManager manager) {
        super(new BorderLayout());
        
        this.manager = manager;
        this.orderForm = new OrderForm();
        this.init();
    }
    
    public void setOrder(Order order) {
    	this.order = order;
    	
    	remove.setEnabled(true);
    	orderForm.updateFields(order);
    }
    
    /**
     * Refreshes the inventory list.
     */
    
    public void refreshOrderView() {
        this.remove(scroll);
        
        initOrderForm();
    }
    
    private void init() {        
        initHeader();
        initOrderForm();
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
    
    	private void initOrderForm() {
            this.add(new JScrollPane(orderForm), BorderLayout.CENTER);
    }
    
    private void initFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));
        cancel = new JButton("cancel");
        cancel.putClientProperty("id", -1L);
        cancel.addActionListener(this);
        
        remove = new JButton("remove");
        remove.putClientProperty("id", -1L);
        remove.addActionListener(this);
        
        save = new JButton("save");
        save.putClientProperty("id",  -1L);
        save.addActionListener(this);
        
        panel.add(cancel, BorderLayout.WEST);
        panel.add(remove, BorderLayout.CENTER);
        panel.add(save, BorderLayout.EAST);
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
        
        if (source.equals(cancel)) {
        	manager.detachOrder();
            manager.switchTo(MidtownComics.OrderListView);
        } else if (source.equals(remove)) {
        	manager.removeOrderFromOrderList(order);
        	manager.switchTo(MidtownComics.OrderListView);
        } else if (source.equals(save)) {
        	try {
				manager.modifyOrderInOrderList(orderForm.getOrderFromFields());
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
}
