import java.io.*;
import java.util.*;

public class InventoryManager implements DealerOperations {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private static final String FILE_NAME = "inventory.txt";

    public InventoryManager() {
        loadFromFile();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public List<Vehicle> listVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    public void saveToFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle v : vehicles) {
                out.write(v.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        vehicles.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length < 6) continue;

                String id = parts[0], make = parts[1], model = parts[2];
                int year = Integer.parseInt(parts[3]);
                double price = Double.parseDouble(parts[4]);
                String type = parts[5];

                Vehicle v = "Vehicle".equals(type)
                        ? new Sedan(id, make, model, year, price)
                        : new Coupe(id, make, model, year, price);
                          new Motorcycle(id, make, model, year, price);
                          new Truck(id, make, model, year, price);
                          new SUV (id, make, model, year, price);
                vehicles.add(v);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override public void buyVehicle(Vehicle v) { addVehicle(v); }
    @Override public void sellVehicle(Vehicle v) { vehicles.remove(v); }
    @Override public void serviceVehicle(Vehicle v) { /* Placeholder */ }
}
