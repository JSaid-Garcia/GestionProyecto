/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public class Task {
    //Atributos
    private int idTask;
    private int idProject;
    private String titulo;
    private String descripcion;
    private int idUser;
    private String prioridad;
    private String estado;
    private int progreso;
    private Date fechaLimite;

    // Constructor
    public Task(int idTask, int idProject, String titulo, String descripcion, int idUser, String prioridad, String estado, int progreso, Date fechaLimite) {
        this.idTask = idTask;
        this.idProject = idProject;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idUser = idUser;
        this.prioridad = prioridad;
        this.estado = estado;
        this.progreso = progreso;
        this.fechaLimite = fechaLimite;
    }

    // Setter y Getters
    public void setidTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setidProyecto(int idProject) {
        this.idProject = idProject;
    }

    public int getIdProyecto() {
        return idProject;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    public int getidUser() {
        return idUser;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

}
