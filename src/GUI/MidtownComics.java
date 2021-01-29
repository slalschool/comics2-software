package GUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ViewManager;
import views.CartView;
import views.InventoryView;
import views.ProductView;
import views.OrderView;
import views.CustomerView;
import views.ClientsView;
import views.OrderListView;
import views.PaymentView;

@SuppressWarnings("serial")
public class MidtownComics extends JFrame {

    public static final int InventoryViewIndex = 0;
    public static final int ProductViewIndex = 1;
    public static final int CartViewIndex = 2;
    public static final int OrderViewIndex = 3;
    public static final int CustomerViewIndex = 4;
    public static final int ClientsViewIndex = 5;
    public static final int OrderListViewIndex = 6;
    public static final int PaymentViewIndex = 7;
    
    public static final String InventoryView = "InventoryView";
    public static final String ProductView = "ProductView";
    public static final String CartView = "CartView";
    public static final String OrderView = "OrderView";
    public static final String CustomerView = "CustomerView";
    public static final String ClientsView = "Clientsview";
    public static final String OrderListView = "OrderListView";
    public static final String PaymentView = "PaymentView";
    
    /**
     * Initializes the application views and frame.
     */
    
    public void init() {
        JPanel views = new JPanel(new CardLayout());
        ViewManager manager = ViewManager.getInstance(views);

        // add child views to the parent container

        views.add(new InventoryView(manager), InventoryView);
        views.add(new ProductView(manager), ProductView);
        views.add(new CartView(manager), CartView);
        views.add(new OrderView(manager), OrderView);
        views.add(new CustomerView(manager), CustomerView);
        views.add(new ClientsView(manager), ClientsView);
        views.add(new OrderListView(manager), OrderListView);
        views.add(new PaymentView(manager), PaymentView);
        
        // configure application frame
        
        this.getContentPane().add(views);
        this.setBounds(0, 0, 750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    ////////// MAIN METHOD /////////////////////////////////////////////////////////
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MidtownComics().init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
