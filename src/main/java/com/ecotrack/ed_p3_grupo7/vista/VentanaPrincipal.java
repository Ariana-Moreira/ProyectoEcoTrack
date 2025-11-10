/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.vista;

/**
 *
 * @author arianamoreira
 */

import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    private SistemaEcoTrack sistema;
    private JTextArea txtResiduos;
    private JButton btnRegistrar;
    private JComboBox<String> cmbOrdenar;
    private JButton btnDespachar;

    public VentanaPrincipal(SistemaEcoTrack sistema) {
        this.sistema = sistema;
        
        setTitle("Gestión de Residuos Urbanos");
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
        btnRegistrar.addActionListener((ActionEvent e) -> {
            actualizarVista();
        });
        
        cmbOrdenar.addActionListener((ActionEvent e) -> {
            String criterio = (String) cmbOrdenar.getSelectedItem();
            // sistema.ordenarResiduos(criterio);
            actualizarVista();
        });
    }
    public void actualizarVista() {
        StringBuilder sb = new StringBuilder("--- Residuos Registrados (Lista Circular) ---\n");
        
        txtResiduos.setText(sb.toString());
    }
}
