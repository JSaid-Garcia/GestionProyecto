/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jainer Said Garcia
 */

public class ProjectDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionProyectos";
    private static final String USUARIO = "Said"; 
    private static final String CONTRASENA = "123456"; 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public void agregarProyecto(Project proyecto) {
        String sql = "INSERT INTO Proyecto (nombre, descripcion, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proyecto.getNombre());
            stmt.setString(2, proyecto.getDescripcion());
            stmt.setDate(3, new java.sql.Date(proyecto.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(proyecto.getFechaFin().getTime()));
            stmt.setString(5, proyecto.getEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Project> obtenerProyectos() {
        List<Project> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM Proyecto";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Project proyecto = new Project(
                        rs.getInt("idProject"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("estado")
                );
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
        return proyectos;
    }

    public void actualizarProyecto(Project project) {
        String sql = "UPDATE Project SET nombre = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, estado = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getNombre());
            stmt.setString(2, project.getDescripcion());
            stmt.setDate(3, new java.sql.Date(project.getFechaInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getFechaFin().getTime()));
            stmt.setString(5, project.getEstado());
            stmt.setInt(6, project.getIdProject());
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarProyecto(int idProject) {
        String sql = "DELETE FROM Project WHERE idProject = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProject);
            stmt.executeUpdate();
        } catch (SQLException e) {
            registrarError(e.getMessage());
            e.printStackTrace();
        }
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

