package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	// Exemplo de conexao com o MySQL
	private static final String USUARIO = "root";
	private static final String SENHA = "q1w2e3r4";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";

	public static Connection conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
}
