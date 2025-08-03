/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */


public class User {
    private int idUser;
    private String nombreUsuario;
    private String nombre;
    private String correo;

    // Constructor
    public User(int idUser, String nombreUsuario, String nombre, String correo) {
        this.idUser = idUser;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
     
    public int getIdUser() {
        return idUser;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
  
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getCorreo() {
        return correo;
    }

    
}