/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jaine
 */


public class Resource {
    private int idResource;
    private int tareaId;
    private String nombre;
    private String rutaArchivo;

    // Constructor
    public Resource(int idResource, int tareaId, String nombre, String rutaArchivo) {
        this.idResource = idResource;
        this.tareaId = tareaId;
        this.nombre = nombre;
        this.rutaArchivo = rutaArchivo;
    }

    // Getters y Setters
    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public int getTareaId() {
        return tareaId;
    }

    public void setTareaId(int tareaId) {
        this.tareaId = tareaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
}

