/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class BD {
    
    private final String ENCABEZADO = "CODIGO\tNOMBRE\t\tID LOCALIZACION\tID MANAGER" + System.lineSeparator();
    private final String SUBRAYADO = "=======\t=======\t\t==============\t===========" + System.lineSeparator();
    
    public String darAlta (int codigo, String nombre, int idLocalizacion, int idManager){
        String mensaje = "Departamento insertado";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/26819001n","daniel_miranda","F0m3nt0");
            String sql = "INSERT INTO departamentos VALUES(?, ?, ?, ?)";
            PreparedStatement sentencia = cn.prepareStatement(sql);
            sentencia.setInt (1, codigo);
            sentencia.setString (2, nombre);
            sentencia.setInt (3, idLocalizacion);
            sentencia.setInt (4, idManager);
            sentencia.executeUpdate();
            sentencia.close();
            cn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
    public String[] buscar (int codigo){
        String[] datos = new String[4];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/26819001n","daniel_miranda","F0m3nt0");
            String sql = "SELECT * FROM departamentos WHERE codigo = ?";
            PreparedStatement sentencia = cn.prepareStatement(sql);
            sentencia.setInt(1, codigo);
            ResultSet busqueda = sentencia.executeQuery();
            while (busqueda.next()) {
                datos[0] = Integer.toString(busqueda.getInt(1));
                datos[1] = busqueda.getString(2);
                datos[2] = Integer.toString(busqueda.getInt(3));
                datos[3] = Integer.toString(busqueda.getInt(4));
            }
            busqueda.close();
            sentencia.close();
            cn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
    
    public void modificar (int codigo, String nombre, int idLocalizacion, int idManager){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/26819001n","daniel_miranda","F0m3nt0");
            String sql = "UPDATE departamentos SET nombre = ?, id_localizacion = ?, id_manager = ? WHERE codigo = ?";
            PreparedStatement sentencia =cn.prepareStatement(sql);
            sentencia.setString (1, nombre);
            sentencia.setInt (2, idLocalizacion);
            sentencia.setInt (3, idManager);
            sentencia.setInt (4, codigo);
            sentencia.executeUpdate();
            sentencia.close();
            cn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrar (int codigo){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/26819001n","daniel_miranda","F0m3nt0");
            String sql = "DELETE FROM departamentos WHERE codigo = ?";
            PreparedStatement sentencia = cn.prepareStatement(sql);
            sentencia.setInt(1, codigo);
            sentencia.executeUpdate();
            sentencia.close();
            cn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String mostrar (){
        String filas = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/26819001n","daniel_miranda","F0m3nt0");
            String sql = "SELECT * FROM departamentos";
            Statement sentencia = cn.createStatement();
            ResultSet busqueda = sentencia.executeQuery(sql);
            while (busqueda.next()) {
                filas += busqueda.getInt(1) + "\t" +
                busqueda.getString(2) + "\t\t" +
                busqueda.getInt(3) + "\t\t" +
                busqueda.getInt(4) + "\n";
            }
            busqueda.close();
            sentencia.close();
            cn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ENCABEZADO + SUBRAYADO + filas;
    }
    
}
