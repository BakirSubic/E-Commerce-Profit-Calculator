import java.util.ArrayList;

class Order {
    private final String customerName;
    private final String shirtSize;
    private final boolean hasDesign;
    private final boolean hasHoodie;
    private final String paymentType;

    public static final ArrayList<Order> orderList = new ArrayList<>();

    public Order(String customerName, String tshirtSize, boolean hasDesign, boolean hasHoodie, String paymentType) {
        this.customerName = customerName;
        this.shirtSize = tshirtSize;
        this.hasDesign = hasDesign;
        this.hasHoodie = hasHoodie;
        this.paymentType = paymentType;
    }

    public boolean hasDesign() {
        return hasDesign;
    }

    public boolean hasHoodie() {
        return hasHoodie;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerName='" + customerName + '\'' +
                ", tshirtSize='" + shirtSize + '\'' +
                ", hasDesign=" + hasDesign +
                ", hasHoodie=" + hasHoodie +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
