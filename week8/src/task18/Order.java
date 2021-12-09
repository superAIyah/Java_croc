package task18;

public class Order {
    int buyer_id;
    String product_id;

    public Order(int buyer_id, String product_id) {
        this.buyer_id = buyer_id;
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return buyer_id + " | " + product_id;
    }
}
