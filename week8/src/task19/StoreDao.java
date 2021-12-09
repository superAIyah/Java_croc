package task19;

import org.h2.mvstore.db.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {
    Connection connection;
    public StoreDao(Connection connection) {
        this.connection = connection;
    }

    Product findProduct(String productCode) throws SQLException { // ищем продукт в бд
        String sql = "SELECT * FROM Product WHERE article = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, productCode);
                try (ResultSet result = statement.executeQuery()) {
                    if (!result.next())
                        return null;
                    String article = result.getString("article");
                    String name = result.getString("name");
                    int price = result.getInt("price");
                    return new Product(article, name, price);
                }
            }
    }

    Product createProduct(Product product) throws SQLException { // создаем новый продукт
        if (findProduct(product.article) != null) {
            throw new SQLException("Добавляемый обьъект уже есть в бд!");
        }
        String sql = "INSERT INTO Product values(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.article);
            statement.setString(2, product.name);
            statement.setInt(3, product.price);
            statement.execute();
        }
        return product;
    }

    Product updateProduct(Product product) throws SQLException { // изменяем продукт
        if (findProduct(product.article) == null) {
            throw new SQLException("Изменяемого обьъекта нет в бд!");
        }
        String sql = "UPDATE Product " +
                "SET name = ?, price = ? " +
                "WHERE article = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, product.name);
            statement.setInt(2, product.price);
            statement.setString(3, product.article);
            statement.execute();
        }
        return product;
    }

    void deleteProduct(String productCode) throws SQLException { // удаляем продукт
        if (findProduct(productCode) == null) {
            throw new SQLException("Удаляемого объекта нет в бд!");
        }
        String sql_product = "DELETE FROM Product WHERE article = ?";
        String sql_purchase = "DELETE FROM Purchase WHERE product_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql_purchase)) { // удалим бд из которой мы ссылаемся
            statement.setString(1, productCode);
            statement.execute();
        }
        try (PreparedStatement statement = connection.prepareStatement(sql_product)) { // потом удалим из бд на которую мы ссылаемся
            statement.setString(1, productCode);
            statement.execute();
        }
    }

    Order createOrder(String userLogin, List<Product> products) throws SQLException {
        String sql_getId = "SELECT * FROM Buyer WHERE name = ?";
        int id = 0;
        String article = "0";
        try (PreparedStatement statement = connection.prepareStatement(sql_getId)) { // получим id пользователя по его логину
            statement.setString(1, userLogin);
            try (ResultSet result = statement.executeQuery()) {
                result.next();
                id = result.getInt("id");
            }
        }
        String sql_purchase = "INSERT INTO Purchase values(?, ?)";
        for (Product p : products) { // пройдемся по всем продуктам
            article = p.article;
            try (PreparedStatement statement = connection.prepareStatement(sql_purchase)) { // добавим запись в общую бд покупок
                statement.setInt(1, id);
                statement.setString(2, article);
                statement.execute();
            }
        }
        return new Order(id, article); // вернем последний заказ пользователя
    }
}
