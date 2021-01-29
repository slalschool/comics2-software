package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.MidtownComics;
import controller.ViewManager;

@SuppressWarnings("serial")
public class PaymentView extends JPanel implements ActionListener {

    private ViewManager manager;
    private PaymentForm form;
    private JLabel total;
    private JButton submit;
    private JButton back;

    public PaymentView(ViewManager manager) {
        super(new BorderLayout());

        this.manager = manager;
        this.init();
    }

    public void updateOrderTotal(double total) {
        this.total.setText("Order Total: $" + total);
    }

    public void clearOrder() {
        total.setText("");
        form.clearFields();
    }

    private void init() {
        JPanel buttonpanel = new JPanel();

        back = new JButton("Back");
        back.addActionListener(this);


        submit = new JButton("Submit");
        submit.addActionListener(this);

        total = new JLabel("Order Total: $", SwingConstants.RIGHT);
        total.setFont(new Font("DialogInput", Font.BOLD, 16));
        total.setBorder(new EmptyBorder(10, 10, 10, 10));

        form = new PaymentForm();

        buttonpanel.add(back, BorderLayout.WEST);
        buttonpanel.add(submit, BorderLayout.EAST);

        this.add(total, BorderLayout.NORTH);
        this.add(form, BorderLayout.CENTER);
        this.add(buttonpanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.equals(submit)) {
            try {
                manager.submitOrder();
                manager.switchTo(MidtownComics.InventoryView);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if(source.equals(back)) {
            manager.switchTo(MidtownComics.InventoryView);
        }
    }
}