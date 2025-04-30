import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel panel;
    private JButton inventoryButton;
    private JButton customerServiceButton;

    public MainFrame() {
        super("Car Dealership");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton inventoryButton = new JButton("Manage Inventory");
        JButton inquiryButton = new JButton("Customer Service");

        inquiryButton.addActionListener(e -> new InquiryFrame().setVisible(true));
        inventoryButton.addActionListener(e -> new InventoryFrame().setVisible(true));


        panel.add(inventoryButton);
        panel.add(inquiryButton);
        add(panel);


            }
        }