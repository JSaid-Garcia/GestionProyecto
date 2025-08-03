/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

/**
 *
 * @author jaine
 */


import DAO.UserDB;
import Modelo.User;
import java.util.List;





public class ServicioUser {
    private UserDB ServicioUser = new UserDB(); // Cambié el nombre a algo más claro

    // Crear un nuevo usuario
    public void crearUsuario(User usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (usuario.getCorreo() == null || !usuario.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("El correo no es válido");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío");
        }

        ServicioUser.agregarUsuario(usuario); 
    }

    // Obtener todos los usuarios
    public List<User> obtenerUsuarios() {
        return ServicioUser.obtenerUsuarios(); 
    }

    // Actualizar un usuario existente
    public void actualizarUsuario(User usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (usuario.getCorreo() == null || !usuario.getCorreo().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("El correo no es válido");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío");
        }

        ServicioUser.actualizarUsuario(usuario); 
    }

    // Eliminar un usuario
    public void eliminarUsuario(int idUser) {
        ServicioUser.eliminarUsuario(idUser); 
    }
}

