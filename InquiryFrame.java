import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InquiryFrame extends JFrame {
    private final CustomerManager manager = new CustomerManager();
    private final DefaultTableModel model;

    public InquiryFrame() {
        super("Customer Inquiries");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        String[] columns = {"Name", "Contact", "Message"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        loadTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JTextField nameF = new JTextField(), contactF = new JTextField();
        JTextArea messageA = new JTextArea(3, 20);
        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("Name:")); form.add(nameF);
        form.add(new JLabel("Contact:")); form.add(contactF);
        form.add(new JLabel("Message:")); form.add(new JScrollPane(messageA));
        add(form, BorderLayout.NORTH);

        JButton addButton = new JButton("Add Inquiry");
        add(addButton, BorderLayout.SOUTH);
        addButton.addActionListener(e -> {
            if (nameF.getText().isEmpty() || contactF.getText().isEmpty() || messageA.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields required", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CustomerInquiry inquiry = new CustomerInquiry(nameF.getText(), contactF.getText(), messageA.getText());
            manager.addInquiry(inquiry);
            manager.saveToFile();
            model.addRow(new Object[]{inquiry.getName(), inquiry.getContact(), inquiry.getMessage()});
        });
    }

    private void loadTable() {
        for (CustomerInquiry i : manager.listInquiries()) {
            model.addRow(new Object[]{i.getName(), i.getContact(), i.getMessage()});
        }
    }
}
