import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MattressSales extends JFrame {

    private final JTextField txtName = new JTextField(15);
    private final JComboBox<String> cmbProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(15);
    private final JButton btnProcess = new JButton("Process");

    public MattressSales() {
        setTitle("Mattress Sales");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 8, 8));

        add(new JLabel("Customer name:"));
        add(txtName);
        add(new JLabel("Product:"));
        add(cmbProduct);
        add(new JLabel("Price (R):"));
        add(txtPrice);
        add(new JLabel());
        add(btnProcess);

        btnProcess.addActionListener(e -> processSale());

        pack();
        setLocationRelativeTo(null);
    }

    private void processSale() {
        try {
            String name = txtName.getText().trim();
            String productName = cmbProduct.getSelectedItem().toString();
            double price = Double.parseDouble(txtPrice.getText().trim());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the customer's name.");
                return;
            }

            IProduct product = new Product(name, productName, price);
            IProductCalculations calc = new ProductCalculations(price);

            PrintProduct.saveToFile(product, calc);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not write to data.txt.");
        }
    }
}