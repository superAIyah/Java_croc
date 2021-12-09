package task19;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // отправим нашу строку в уже готовую базу данных
        String HW = "Hello, world!";

        String connectionUrl = "jdbc:h2:tcp://localhost/~/test/test"; // переменные для подключения к бд
        String user = "sa";
        String passwd = "";
        Class.forName("org.h2.Driver"); // загрузка класса драйвера

        String sql = "INSERT INTO Product VALUES(?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, passwd)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "T0");
                statement.setString(2, HW);
                statement.setInt(3, 0);
                statement.execute();
            }
        }
    }
}
