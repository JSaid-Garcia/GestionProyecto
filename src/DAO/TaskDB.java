/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaine
 */



public class TaskDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionProyectos";
    private static final String USUARIO = "Said"; 
    private static final String CONTRASENA = "123456"; 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public void agregarTask(Task tarea) {
        String sql = "INSERT INTO Task (IdProject, titulo, descripcion, idUser, prioridad, estado, progreso, fechaLimite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tarea.getIdProyecto());
            stmt.setString(2, tarea.getTitulo());
            stmt.setString(3, tarea.getDescripcion());
            stmt.setInt(4, tarea.getidUser());
            stmt.setString(5, tarea.getPrioridad());
            stmt.setString(6, tarea.getEstado());
            stmt.setInt(7, tarea.getProgreso());
            stmt.setDate(8, new java.sql.Date(tarea.getFechaLimite().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Task> obtenerTaskPorProject(int idProject) {
        List<Task> tareas = new ArrayList<>();
        String sql = "SELECT * FROM Task WHERE idProject = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProject);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task tarea = new Task(
                    rs.getInt("idTask"),
                    rs.getInt("idProject"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getInt("idUser"),
                    rs.getString("prioridad"),
                    rs.getString("estado"),
                    rs.getInt("progreso"),
                    rs.getDate("fechaLimite")
                );
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
        return tareas;
    }

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
