import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryFrame extends JFrame {
    private final InventoryManager manager = new InventoryManager();
    private final DefaultTableModel model;

    public InventoryFrame() {
        super("Inventory Management");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        String[] columns = {"ID", "Make", "Model", "Year", "Price", "Type"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        loadTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JTextField idF = new JTextField(), makeF = new JTextField(), modelF = new JTextField(),
                yearF = new JTextField(), priceF = new JTextField();
        JComboBox<String> typeCB = new JComboBox<>(new String[]{"Sedan", "Coupe", "Motorcycle", "Truck", "SUV"});

        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));
        form.add(new JLabel("ID:")); form.add(idF);
        form.add(new JLabel("Make:")); form.add(makeF);
        form.add(new JLabel("Model:")); form.add(modelF);
        form.add(new JLabel("Year:")); form.add(yearF);
        form.add(new JLabel("Price:")); form.add(priceF);
        form.add(new JLabel("Type:")); form.add(typeCB);
        add(form, BorderLayout.NORTH);

        JButton addButton = new JButton("Add Vehicle");
        add(addButton, BorderLayout.SOUTH);
        addButton.addActionListener(e -> {
            try {
                Vehicle v = "Vehicle".equals(typeCB.getSelectedItem())
                        ? new Sedan(idF.getText(), makeF.getText(), modelF.getText(), Integer.parseInt(yearF.getText()), Double.parseDouble(priceF.getText()))
                        : new Coupe(idF.getText(), makeF.getText(), modelF.getText(), Integer.parseInt(yearF.getText()), Double.parseDouble(priceF.getText()));
                         new Motorcycle(idF.getText(), makeF.getText(), modelF.getText(), Integer.parseInt(yearF.getText()), Double.parseDouble(priceF.getText()));
                         new Truck(idF.getText(), makeF.getText(), modelF.getText(), Integer.parseInt(yearF.getText()), Double.parseDouble(priceF.getText()));
                         new SUV(idF.getText(), makeF.getText(), modelF.getText(), Integer.parseInt(yearF.getText()), Double.parseDouble(priceF.getText()));

                manager.addVehicle(v);
                manager.saveToFile();
                model.addRow(new Object[]{v.getId(), v.getMake(), v.getModel(), v.getYear(), v.getPrice(), v.getType()});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void loadTable() {
        for (Vehicle v : manager.listVehicles()) {
            model.addRow(new Object[]{v.getId(), v.getMake(), v.getModel(), v.getYear(), v.getPrice(), v.getType()});
        }
    }
}
