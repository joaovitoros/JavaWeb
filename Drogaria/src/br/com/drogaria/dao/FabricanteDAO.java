package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.factory.ConexaoFactory;
import br.com.drogaria.model.Fabricante;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void editar(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		
		Fabricante retorno = null;
		
		if(resultado.next()){
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fabricante> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()){
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		
		return lista;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" +f.getDescricao() +"%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()){
			Fabricante fab = new Fabricante();
			fab.setCodigo(resultado.getLong("codigo"));
			fab.setDescricao(resultado.getString("descricao"));
			
			lista.add(fab);
		}
		
		return lista;
	}
}
