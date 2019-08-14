import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		// XXX Test JDBC
		String databaseUrl = "jdbc:mysql://localhost:3306/jdbc_test";
		String databaseUser = "root";
		String password = "";
		String driverDb = "com.mysql.jdbc.Driver";
		Connection connection = null;
		try {
			Class.forName(driverDb);
			connection = DriverManager.getConnection(databaseUrl, databaseUser, password);
			//insert(connection);
			print(connection);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void insert(Connection conn) {
		String sql = "insert into student (Name,Age,Address) values (?,?,?)";
		try {
			for (int i = 0; i < 5; i++) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, "Thuan" + i);
				statement.setInt(2, 29);
				statement.setString(3, "138 Tran Huy Lieu");
				statement.execute();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void print(Connection conn) {
		String sql = "select * from student";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();	
			while (rs.next()) {
				System.out.println("Id = " + rs.getInt("Id") + " & Name = " + rs.getString(2) + " & Age = " + rs.getInt(3) + " & Address = " + rs.getString(4));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
