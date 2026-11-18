
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private final JTextField txtCustomer = new JTextField(20);
    private final JComboBox<String> cboProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildMenu();
        buildForm();
        pack();
        setLocationRelativeTo(null);
    }

    private void buildMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));
        file.add(exit);

        JMenu tools = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        process.addActionListener(e -> processSale());
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> clearForm());
        tools.add(process);
        tools.add(clear);

        bar.add(file);
        bar.add(tools);
        setJMenuBar(bar);
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomer);
        panel.add(new JLabel("Select Product:"));
        panel.add(cboProduct);
        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel(""));
        panel.add(btnProcess);
        btnProcess.addActionListener(e -> processSale());
        add(panel);
    }

    private void processSale() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cboProduct.getSelectedItem();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

            IProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        JOptionPane.showMessageDialog(this,
                "Customer Name: " + customer + "\n" +
                        "Product: " + product + "\n" +
                        "VAT: R " + vat + "\n" +
                        "COMMISSION: R " + commission + "\n" +
                        "TOTAL: R " + total);

        saveToFile(customer, product, vat, commission, total);
    }

    private void saveToFile(String customer, String product,
                            double vat, double commission, double total) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("*********************************");
            out.println("Customer Name: " + customer);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("*********************************");
            out.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save to data.txt",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtCustomer.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
