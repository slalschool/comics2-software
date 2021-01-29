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
import model.Product;

@SuppressWarnings("serial")
public class ProductView extends JPanel implements ActionListener {

    private ViewManager manager;
    private Product product;
    private ProductForm productForm;
    private JButton save;
    private JButton remove;
    private JButton cancel;

    /**
     * Creates an instance of the ProductView class.
     * 
     * @param manager the controller
     */
    
    public ProductView(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.productForm = new ProductForm();
        this.init();
    }
    
    /**
     * Sets the Product associated with this view.
     *
     * @param product the new product
     */

    public void setProduct(Product product) {
        this.product = product;
        
        remove.setEnabled(true);
        productForm.updateFields(product);
    }

    private void init() {        
        initHeader();
        initProductForm();
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

    private void initProductForm() {
        this.add(new JScrollPane(productForm), BorderLayout.CENTER);
    }

    private void initFooter() {
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

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
            if (product == null) {
                manager.addProductToInventory(productForm.getProductFromFields());
            } else {
                manager.modifyProductInInventory(productForm.getProductFromFields());
            }
        } else if (source.equals(remove)) {
            manager.removeProductFromInventory(product);
        } else if (source.equals(cancel)) {
            manager.detachProduct();
            manager.switchTo(MidtownComics.InventoryView);
        }
    }
}