package task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class Main {

    // проверяем нашлось ли значение по запросу
    public static boolean find(Connection connection, String sql, String thing) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, thing);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    // добавляем нового покупателя к таблице
    public static void insertBuyer(Connection connection, String sql, int id, String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.execute();
        }
    }

    // добавляем новый продукт к таблице
    public static void insertProduct(Connection connection, String sql, String article, String name, int price) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, article);
            statement.setString(2, name);
            statement.setInt(3, price);
            statement.execute();
        }
    }

    // добавляем новую покупку в таблцу
    public static void insertPurchase(Connection connection, String sql, int id, String article) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, article);
            statement.execute();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        Реализуем свзять многие ко многим через промежуточную таблицу purchase - "покупка":
        Покупатель может покупать много товаров по артиклу;
        Товар по артиклу может быть заказан несолькими пользователями
        */
        Scanner console = new Scanner(System.in);
        System.out.println("Введите путь к файлу относительно текущего пакета");
        String path = console.nextLine(); // data.csv
        String prefix = "src/task18/";  // по умолчанию JVM считает путь от папки корневого модуля
        path = prefix + path; // получаем путь к файлу csv с данными

        String connectionUrl = "jdbc:h2:tcp://localhost/~/test/test"; // переменные для подключения к бд
        String user = "sa";
        String passwd = "";

        Class.forName("org.h2.Driver"); // загрузка класса драйвера

        // создание таблиц
        String CREATE_BUYER = "CREATE TABLE Buyer(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(100)" +
                ");";
        String CREATE_PRODUCT = "CREATE TABLE Product(" +
                "article VARCHAR(100) PRIMARY KEY," +
                "name VARCHAR(100)," +
                "price INT" +
                ");";
        String CREATE_PURCHASE = "CREATE TABLE Purchase(" +
                "buyer_id INT," +
                "product_id VARCHAR(100)," +
                "FOREIGN KEY (buyer_id) REFERENCES Buyer(id)," +
                "FOREIGN KEY (product_id) REFERENCES Product(article)" +
                ");";
        String CREATE_ALL = CREATE_BUYER + CREATE_PRODUCT + CREATE_PURCHASE;
        String DROP_ALL = "DROP TABLE IF EXISTS Purchase;" +
                "DROP TABLE IF EXISTS Buyer;" +
                "DROP TABLE IF EXISTS Product;";

        // буфферизация потока файла
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            // создание соединения
            try (Connection connection = DriverManager.getConnection(connectionUrl, user, passwd)) {
                // создание запроса
                try (Statement statement = connection.createStatement()) {
                    statement.execute(DROP_ALL); // очистка существующих таблиц
                    statement.execute(CREATE_ALL); // создание всех таблиц

                    String line;
                    // параметризированный запросы
                    String findBuyer = "SELECT * FROM Buyer WHERE name = ?";
                    String insertBuyer = "INSERT INTO Buyer values(?, ?)";
                    String findProduct = "SELECT * FROM Product WHERE article = ?";
                    String insertProduct = "INSERT INTO Product values(?, ?, ?)";
                    String insertPurchase = "INSERT INTO Purchase values(?, ?)";

                    while ((line = r.readLine()) != null) {
                        String[] words = line.split(",");

                        int id = Integer.parseInt(words[0]);
                        String name = words[1];
                        String article = words[2];
                        String product = words[3];
                        int price = Integer.parseInt(words[4]);

                        if (!find(connection, findBuyer, name)) // если покупателя еще нет
                            insertBuyer(connection, insertBuyer, id, name); // добавим его в бд
                        if (!find(connection, findProduct, article)) // если продукта еще нет
                            insertProduct(connection, insertProduct, article, product, price); // добавим его в бд
                        insertPurchase(connection, insertPurchase, id, article); // добавляем покупку
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл не найден");
        }
    }
}
