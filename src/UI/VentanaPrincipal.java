/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author jaine
 */


import Modelo.Project;
import Modelo.Task;
import Servicio.ServicioProject;
import Servicio.ServicioTask;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private ServicioProject projectservicio = new ServicioProject();
    private ServicioTask taskServicio = new ServicioTask();
    private JTable tablaProyectos, tablaTareas;
    private JTextField campoNombreProyecto, campoDescripcionProyecto;
    private JTextField campoTituloTarea, campoDescripcionTarea, campoProgresoTarea;
    private JComboBox<String> comboPrioridad, comboEstadoTarea, comboUsuario;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Proyectos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Proyectos", crearPanelProyectos());
        pestanas.addTab("Tareas", crearPanelTareas());
        add(pestanas, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelProyectos() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        campoNombreProyecto = new JTextField();
        panelFormulario.add(campoNombreProyecto);
        panelFormulario.add(new JLabel("Descripción:"));
        campoDescripcionProyecto = new JTextField();
        panelFormulario.add(campoDescripcionProyecto);
        JButton botonAgregar = new JButton("Agregar Proyecto");
        botonAgregar.addActionListener(e -> agregarProyecto());
        panelFormulario.add(botonAgregar);
        JButton botonEliminar = new JButton("Eliminar Proyecto");
        botonEliminar.addActionListener(e -> eliminarProyecto());
        panelFormulario.add(botonEliminar);
        panel.add(panelFormulario, BorderLayout.NORTH);

        tablaProyectos = new JTable();
        actualizarTablaProyectos();
        panel.add(new JScrollPane(tablaProyectos), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelTareas() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("Título:"));
        campoTituloTarea = new JTextField();
        panelFormulario.add(campoTituloTarea);
        panelFormulario.add(new JLabel("Descripción:"));
        campoDescripcionTarea = new JTextField();
        panelFormulario.add(campoDescripcionTarea);
        panelFormulario.add(new JLabel("Progreso (0-100):"));
        campoProgresoTarea = new JTextField();
        panelFormulario.add(campoProgresoTarea);
        panelFormulario.add(new JLabel("Prioridad:"));
        comboPrioridad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        panelFormulario.add(comboPrioridad);
        panelFormulario.add(new JLabel("Usuario Asignado:"));
        comboUsuario = new JComboBox<>(new String[]{"admin", "usuario1"});
        panelFormulario.add(comboUsuario);
        JButton botonAgregarTarea = new JButton("Agregar Tarea");
        botonAgregarTarea.addActionListener(e -> agregarTarea());
        panel.add(panelFormulario, BorderLayout.NORTH);

        tablaTareas = new JTable();
        actualizarTablaTareas();
        panel.add(new JScrollPane(tablaTareas), BorderLayout.CENTER);

        return panel;
    }

    private void agregarProyecto() {
        Project proyecto = new Project(0, campoNombreProyecto.getText(), campoDescripcionProyecto.getText(),
                new Date(), new Date(), "Activo");
        projectservicio.crearProyecto(proyecto); // CORREGIDO
        actualizarTablaProyectos();
        campoNombreProyecto.setText("");
        campoDescripcionProyecto.setText("");
    }

    private void eliminarProyecto() {
        int filaSeleccionada = tablaProyectos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = (int) tablaProyectos.getValueAt(filaSeleccionada, 0);
            projectservicio.eliminarProyecto(id); // CORREGIDO
            actualizarTablaProyectos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un proyecto para eliminar.");
        }
    }

    private void agregarTarea() {
        int filaSeleccionada = tablaProyectos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idProject = (int) tablaProyectos.getValueAt(filaSeleccionada, 0);
            int progreso;
            try {
                progreso = Integer.parseInt(campoProgresoTarea.getText());
            } catch (NumberFormatException e) {
                progreso = 0;
            }
            Task tarea = new Task(0, idProject, campoTituloTarea.getText(), campoDescripcionTarea.getText(),
                    comboUsuario.getSelectedIndex() + 1, (String) comboPrioridad.getSelectedItem(),
                    "Pendiente", progreso, new Date());
            taskServicio.crearTask(tarea); // CORREGIDO
            actualizarTablaTareas();
            campoTituloTarea.setText("");
            campoDescripcionTarea.setText("");
            campoProgresoTarea.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un proyecto primero.");
        }
    }

    private void actualizarTablaProyectos() {
        List<Project> proyectos = projectservicio.obtenerProyectos(); // CORREGIDO
        String[] columnas = {"idProject", "Nombre", "Descripción", "Estado"};
        Object[][] datos = new Object[proyectos.size()][4];
        for (int i = 0; i < proyectos.size(); i++) {
            Project p = proyectos.get(i);
            datos[i][0] = p.getIdProject();
            datos[i][1] = p.getNombre();
            datos[i][2] = p.getDescripcion();
            datos[i][3] = p.getEstado();
        }
        tablaProyectos.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void actualizarTablaTareas() {
        int filaSeleccionada = tablaProyectos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int proyectoId = (int) tablaProyectos.getValueAt(filaSeleccionada, 0);
            List<Task> tareas = taskServicio.obtenerTaskPorProject(proyectoId); // CORREGIDO
            String[] columnas = {"ID", "Título", "Progreso", "Prioridad", "Usuario"};
            Object[][] datos = new Object[tareas.size()][5];
            for (int i = 0; i < tareas.size(); i++) {
                Task t = tareas.get(i);
                datos[i][0] = t.getIdTask();
                datos[i][1] = t.getTitulo();
                datos[i][2] = t.getProgreso();
                datos[i][3] = t.getPrioridad();
                datos[i][4] = t.getidUser() == 1 ? "admin" : "usuario1";
            }
            tablaTareas.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}