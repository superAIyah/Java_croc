package task19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner console = new Scanner(System.in);

        String connectionUrl = "jdbc:h2:tcp://localhost/~/test/test"; // переменные для подключения к бд
        String user = "sa";
        String passwd = "";
        Class.forName("org.h2.Driver"); // загрузка класса драйвера

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, passwd)) {
            StoreDao dao = new StoreDao(connection);
            while (true) {
                System.out.println("Введите команды: ТОВАР | ИЗМЕНИТЬ | УДАЛИТЬ | ЗАКАЗ | КОНЕЦ");
                String line;
                line = console.nextLine();
                String[] words = line.split(" ");
                String action = words[0];
                String article;
                String name;
                int price;
                if (action.equals("КОНЕЦ"))
                    return;
                switch (action) {
                    case "ТОВАР" -> {
                        article = words[1];
                        name = words[2];
                        price = Integer.parseInt(words[3]);
                        dao.createProduct(new Product(article, name, price));
                    }
                    case "ИЗМЕНИТЬ" -> {
                        article = words[1];
                        name = words[2];
                        price = Integer.parseInt(words[3]);
                        dao.updateProduct(new Product(article, name, price));
                    }
                    case "УДАЛИТЬ" -> {
                        article = words[1];
                        dao.deleteProduct(article);
                    }
                    case "ЗАКАЗ" -> {
                        name = words[1];
                        ArrayList<Product> order = new ArrayList<>();
                        for (int i = 2; i < words.length; i++)
                            order.add(dao.findProduct(words[i]));
                        dao.createOrder(name, order);
                    }
                }
            }
        }
    }
}
