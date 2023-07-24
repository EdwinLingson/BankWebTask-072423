package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	
	public static Connection getMyConnection(DbDetails dbDetails) {
        try {
            Class.forName(dbDetails.getDriver());
            Connection connection = DriverManager.getConnection(dbDetails.getUrl(), dbDetails.getUname(), dbDetails.getPassword());
            if (connection == null) {
                System.out.println("Connection Failed");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {	
            throw new RuntimeException(e);
        }
    }
}
