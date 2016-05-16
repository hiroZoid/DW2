package hiroZoid.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public enum DBConnection {
    INSTANCE;

    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/saude",
                    "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String qry) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qry);
        return resultSet;
    }

    public int executeUpdate(String qry) throws SQLException {
        Statement statement = connection.createStatement();
        int affectedLines = statement.executeUpdate(qry);
        statement.close();
        return affectedLines;
    }

    public int executeScalar(String qry) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qry);
        resultSet.next();
        int scalar = resultSet.getInt(1);
        resultSet.close();
        statement.close();
        return scalar;
    }

}
