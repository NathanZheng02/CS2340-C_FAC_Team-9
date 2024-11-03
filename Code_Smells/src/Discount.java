public class Discount {

    private DiscountType type;
    private double amount;

    public Discount(DiscountType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public DiscountType getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }
}
