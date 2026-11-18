import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintProduct {

    public static void saveToFile(IProduct product, IProductCalculations calc) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt"))) {
            out.println("DATA LOG");
            out.println("******************************");
            out.println("Customer Name: " + product.getCustomerName());
            out.println("Product: " + product.getProductName());
            out.println("VAT: R " + calc.calculateVat());
            out.println("COMMISSION: R " + calc.calculateCommission());
            out.println("TOTAL: R " + calc.calculateTotal());
            out.println("******************************");
        }
    }
}