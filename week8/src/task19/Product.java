package task19;

public class Product {
    public String article;
    public String name;
    public int price;

    public Product(String article, String name, int price) {
        this.article = article;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return article + " | " + name + " | " + price;
    }
}
