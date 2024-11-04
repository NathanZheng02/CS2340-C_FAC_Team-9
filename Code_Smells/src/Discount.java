public class Discount {

    private DiscountType type;
    private double amount;

    public Discount(DiscountType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public enum DiscountType {
        PERCENTAGE {
            @Override
            public double applyDiscount(Item item) {
                return item.getDiscount().getAmount() * item.getPrice();
            } 
        },
        AMOUNT {
            @Override
            public double applyDiscount(Item item) {
                return item.getDiscount().getAmount();
            }
        };

        public abstract double applyDiscount(Item item);
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
