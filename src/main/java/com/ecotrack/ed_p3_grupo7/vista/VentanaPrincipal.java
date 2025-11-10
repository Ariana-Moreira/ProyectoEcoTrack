/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.vista;

/**
 *
 * @author arianamoreira
 */

import java.awt.event.ActionListener; // Para manejar los clicks de botones
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

    private SistemaEcoTrack sistema;
    private JTextArea txtResiduos;
    private JButton btnRegistrar;
    private JComboBox<String> cmbOrdenar;
    private JButton btnDespachar;

    public VentanaPrincipal(SistemaEcoTrack sistema) {
        this.sistema = sistema;
        
        // Configuración básica de la ventana
        setTitle("EcoTrack - Gestión de Residuos Urbanos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        
        inicializarComponentes();
        agregarListeners();
        
        // Llamada inicial para cargar datos al iniciar
        actualizarVista(); 
    }

    private void inicializarComponentes() {
        // Usa un layout simple para empezar (ej. BorderLayout o FlowLayout)
        setLayout(new java.awt.BorderLayout());
        
        // Área para mostrar la lista de residuos y el estado del sistema
        txtResiduos = new JTextArea("Cargando datos...");
        txtResiduos.setEditable(false);
        add(new JScrollPane(txtResiduos), BorderLayout.CENTER);
        
        // Panel para controles (Botones)
        JPanel panelControles = new JPanel();
        
        btnRegistrar = new JButton("1. Registrar Nuevo Residuo");
        panelControles.add(btnRegistrar);

        btnDespachar = new JButton("2. Despachar Vehículo");
        panelControles.add(btnDespachar);
        
        // Componente para Ordenamiento (Requisito 4)
        String[] criterios = {"Peso", "Tipo", "Prioridad Ambiental"};
        cmbOrdenar = new JComboBox<>(criterios);
        panelControles.add(new JLabel("Ordenar por:"));
        panelControles.add(cmbOrdenar);
        
        add(panelControles, BorderLayout.NORTH);
    }
    
    private void agregarListeners() {
        // Ejemplo de cómo enlazar el botón con una acción del sistema
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se llama a la lógica. Se debería obtener datos de campos de texto (no implementados aquí)
                // EJEMPLO: Simular el registro de un residuo
                // sistema.registrarResiduo("R005", "Metal", 5.0, ...); 
                
                // Luego, actualizamos la vista
                actualizarVista(); 
            }
        });
        
        // Ejemplo de Listener para el Combo Box
        cmbOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterio = (String) cmbOrdenar.getSelectedItem();
                // sistema.ordenarResiduos(criterio); 
                actualizarVista();
            }
        });
    }

    /**
     * Muestra el estado actual de los residuos en la lista enlazada (usando el Iterador).
     */
    public void actualizarVista() {
        // En un proyecto real, se usaría JTable, pero JTextArea es más simple para estructuras.
        StringBuilder sb = new StringBuilder("--- Residuos Registrados (Lista Circular) ---\n");
        
        // Aquí se usa la característica Iterable de tu Lista Enlazada
        // Esto automáticamente usa el método iterator() que implementaste.
        // for (Residuo r : sistema.getListaResiduos()) {
        //     sb.append(r.toString()).append("\n");
        // }
        
        txtResiduos.setText(sb.toString());
    }
}
