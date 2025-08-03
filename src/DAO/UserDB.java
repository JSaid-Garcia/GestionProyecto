/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jainer Said Garcia 
 */
public class UserDB {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionProyectos";
    private static final String USUARIO = "Said"; 
    private static final String CONTRASENA = "123456"; 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    // Agregar un nuevo usuario
    public void agregarUsuario(User usuario) {
        String sql = "INSERT INTO User (nombreUsuario, nombre, correo) VALUES (?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    // Obtener todos los usuarios
    public List<User> obtenerUsuarios() {
        List<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User usuario = new User(
                    rs.getInt("idUser"),
                    rs.getString("nombreUsuario"),
                    rs.getString("nombre"),
                    rs.getString("correo")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    // Actualizar un usuario existente
    public void actualizarUsuario(User usuario) {
        String sql = "UPDATE User SET nombreUsuario = ?, nombre = ?, correo = ? WHERE idUser = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setInt(4, usuario.getIdUser());
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    // Eliminar un usuario
    public void eliminarUsuario(int idUser) {
        String sql = "DELETE FROM Usuario WHERE idUser = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    // Registrar errores en la base de datos
    private void registrarError(String mensaje) {
        String sql = "INSERT INTO RegistroErrores (mensaje_error) VALUES (?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mensaje);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

