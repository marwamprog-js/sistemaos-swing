package br.com.sistema_os.dal;
import java.sql.*;


public class ModuloConexao {

	public static Connection conector() {
		
		Connection conexao = null;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_infox";
		String user = "root";
		String password = "root";
		
		try {
			
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
