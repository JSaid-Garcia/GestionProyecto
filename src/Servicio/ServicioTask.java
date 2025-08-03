/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

/**
 *
 * @author jaine
 */


import DAO.TaskDB;
import Modelo.Task;
import java.util.List;

public class ServicioTask {
    private TaskDB task = new TaskDB();

    public void crearTask(Task tarea) {
        task.agregarTask(tarea);
    }

    public List<Task> obtenerTaskPorProject(int idProject) {
        return task.obtenerTaskPorProject(idProject);
    }
}
