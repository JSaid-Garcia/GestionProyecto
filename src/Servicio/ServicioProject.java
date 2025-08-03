/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;
import DAO.ProjectDB;
import Modelo.Project;
import java.util.List;
/**
 *
 * @author jaine
 */



public class ServicioProject {
    private ProjectDB projectservice = new ProjectDB();

    public void crearProyecto(Project proyecto) {
        projectservice.agregarProyecto(proyecto);
    }

    public List<Project> obtenerProyectos() {
        return projectservice.obtenerProyectos();
    }

    public void actualizarProyecto(Project proyecto) {
       projectservice.actualizarProyecto(proyecto);
    }

    public void eliminarProyecto(int idProject) {
        projectservice.eliminarProyecto(idProject);
    }
}

