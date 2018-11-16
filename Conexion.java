package com.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Conexion {

	private Conexion con;
	
	public Conexion(){
		performConexion();
	}
	
	public void performConexion() {
		String host = "127.0.0.1";
		String user = "encuesta";
		String pass = "root";
		String dtbs = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String newConexionURL = "jdbc:mysql://" + host + "/" + dtbs
					+ "?" + "user=" + user + "&password=" + pass;
					con = (Conexion) DriverManager.getConnection(newConexionURL);
		}catch (Exception e) {
			System.out.println("Error en l'obertura de la connexió.");
		}
		
	}
	
	public void closeConexion() {
		try {
			con.clone();
		}catch (Exception e) {
			System.out.println("Error en la conexión.");
		}
	}
	
	public ArrayList<String> retornaUsuario() throws SQLException{
		ArrayList<String> ls = new ArrayList<String>();
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM usuario");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ls.add(rs.getString("nombre"));
		}
		rs.close();
		return ls;
	}

	private PreparedStatement prepareStatement(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertaUsuario(int id, String nombre, String estado, String identificacion) throws SQLException {
		String seleccio = "INSERT INTO `usuario` (id` ,`nombre` ,`estado` ,`identificacion`)" +
				"VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setInt(1, id);
		ps.setString(2, nombre);
		ps.setString(3, estado);
		ps.setString(4, identificacion);
		ps.executeUpdate();
	}

	public void editaUsuario(int numlic, String nom) throws SQLException {
		String seleccio = "UPDATE `usuario` SET `nomatleta` = ? WHERE `identificacion` =?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setString(1, nom); //emplenem el primer interrogant
		int id = 0;
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void eliminaUsuario(int numlic) throws SQLException {
		String seleccio = "DELETE FROM `usuario` WHERE `id` = ?";
		PreparedStatement ps = con.prepareStatement(seleccio);
		ps.setInt(1, numlic);
		ps.executeUpdate();
	}
}
