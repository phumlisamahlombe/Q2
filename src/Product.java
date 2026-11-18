public class Product implements IProduct {
    private final String customerName;
    private final String productName;
    private final double price;

    public Product(String customerName, String productName, double price) {
        this.customerName = customerName;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String getCustomerName() { return customerName; }

    @Override
    public String getProductName() { return productName; }

    @Override
    public double getPrice() { return price; }
}
