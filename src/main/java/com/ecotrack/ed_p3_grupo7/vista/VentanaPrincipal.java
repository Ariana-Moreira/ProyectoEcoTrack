/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.vista;
/**
 *
 * @author arianamoreira
 */
import com.ecotrack.ed_p3_grupo7.datos.Residuo;
import com.ecotrack.ed_p3_grupo7.datos.Zona;
import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorPeso; 
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorPrioridad; 
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorTipo; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.UUID; 
import java.time.LocalDate;
import java.util.Iterator;

public class VentanaPrincipal extends JFrame {
    
    private final SistemaEcoTrack sistema;
    
    private JTextArea areaListado;
    private JTextArea areaEstadisticas;
    private JComboBox<String> selectorOrden;
    private JButton btnDespachar;
    private JButton btnRegistrar;
    private JButton btnIterarSiguiente;
    private JButton btnIterarAnterior;
    
    private ListIterator<Residuo> iteradorResiduos; 

    //Constructor
    public VentanaPrincipal(SistemaEcoTrack sistema) {
        this.sistema = sistema;
        inicializarIterador(); 
        
        setTitle("EcoTrack - Sistema de Gestión de Residuos Urbanos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        actualizarPantalla();
    }
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel Principal
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        // Panel de Control y Acciones
        JPanel controlPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        
        btnRegistrar = new JButton("1. Registrar Nuevo Residuo");
        btnDespachar = new JButton("2. Despachar Vehículo (Prioridad)");
        
        selectorOrden = new JComboBox<>(new String[]{"ID", "Peso", "Tipo", "Prioridad Ambiental"});
        JButton btnOrdenar = new JButton("3. Ordenar y Mostrar Lista");
        
        btnIterarSiguiente = new JButton("4. Siguiente Residuo >>");
        btnIterarAnterior = new JButton("<< 5. Anterior Residuo");
        
        controlPanel.add(btnRegistrar);
        controlPanel.add(btnDespachar);
        controlPanel.add(new JLabel("Criterio de Ordenamiento:"));
        controlPanel.add(selectorOrden);
        controlPanel.add(btnOrdenar);
        
        JPanel iteracionPanel = new JPanel(new GridLayout(1, 2));
        iteracionPanel.add(btnIterarAnterior);
        iteracionPanel.add(btnIterarSiguiente);
        
        // Área de Listado e Iteración
        areaListado = new JTextArea("Contenido de la Lista Enlazada Circular");
        areaListado.setEditable(false);
        JScrollPane scrollListado = new JScrollPane(areaListado);
        
        // Área de Estadísticas (Pila y Mapas)
        areaEstadisticas = new JTextArea("Estadísticas y Pila del Centro de Reciclaje");
        areaEstadisticas.setEditable(false);
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);

        // Layout final
        mainPanel.add(scrollListado);
        mainPanel.add(scrollEstadisticas);
        
        add(controlPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(iteracionPanel, BorderLayout.SOUTH);

        // --- Asignación de Eventos ---
        btnRegistrar.addActionListener(this::registrarResiduoAction);
        btnDespachar.addActionListener(this::despacharVehiculoAction);
        btnOrdenar.addActionListener(this::cambiarCriterioOrdenamientoAction);
        btnIterarSiguiente.addActionListener(this::iterarSiguienteAction);
        btnIterarAnterior.addActionListener(this::iterarAnteriorAction);

        pack();
        setLocationRelativeTo(null); // Centrar
    }
    
    // Inicializa el Iterador Bidireccional
    private void inicializarIterador() {
        // Obtenemos el ListIterator de la lista enlazada (debe ser bidireccional)
        Iterator<Residuo> iter = sistema.getListaResiduos().iterator();
        if (iter instanceof ListIterator) {
            this.iteradorResiduos = (ListIterator<Residuo>) iter;
        }
    }

    private void registrarResiduoAction(ActionEvent e) {
        try {
            String id = UUID.randomUUID().toString().substring(0, 5);
            String nombre = JOptionPane.showInputDialog(this, "Nombre del Residuo:");
            if (nombre == null) return;
            
            double peso = Double.parseDouble(JOptionPane.showInputDialog(this, "Peso (kg):"));
            String tipo = JOptionPane.showInputDialog(this, "Tipo (Orgánico/Plástico/Vidrio):");
            int prioridad = Integer.parseInt(JOptionPane.showInputDialog(this, "Nivel de Prioridad (1-5):"));
            
            Zona zonaEjemplo = sistema.getMapaZonas().getOrDefault("Z1", new Zona("Z1", "Norte"));
            if (!sistema.getMapaZonas().containsKey("Z1")) {
                sistema.getMapaZonas().put("Z1", zonaEjemplo);
            }
            
            Residuo nuevoResiduo = new Residuo(id, nombre, peso, tipo, zonaEjemplo, prioridad);
            sistema.registrarResiduo(nuevoResiduo);
            
            JOptionPane.showMessageDialog(this, "Residuo registrado con éxito: " + nuevoResiduo.getID());
            actualizarPantalla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en formato de número/prioridad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void despacharVehiculoAction(ActionEvent e) {
        Zona zonaDespachada = sistema.despachaVehiculo(); //
        
        if (zonaDespachada != null) {
            JOptionPane.showMessageDialog(this, 
                "Vehículo despachado a la zona: " + zonaDespachada.getNombre() + 
                "\nUtilidad: " + zonaDespachada.getUtilidad() + " (Máxima Prioridad)", 
                "Despacho Automático (PriorityQueue)", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            JOptionPane.showMessageDialog(this, "No hay zonas pendientes por atender.", 
                "Despacho Automático", JOptionPane.WARNING_MESSAGE);
        }
        actualizarPantalla();
    }

    private void iterarSiguienteAction(ActionEvent e) {
        if (iteradorResiduos != null) {
            if (iteradorResiduos.hasNext()) {
                Residuo r = iteradorResiduos.next();
                areaListado.setText("ITERANDO >> SIGUIENTE:\n" + r.toString());
            } else {
                areaListado.setText("Fin de la lista (volviendo al inicio)."); 
            }
        }
    }

    private void iterarAnteriorAction(ActionEvent e) {
        if (iteradorResiduos != null) {
            if (iteradorResiduos.hasPrevious()) {
                Residuo r = iteradorResiduos.previous();
                areaListado.setText("<< ITERANDO ANTERIOR:\n" + r.toString());
            } else {
                areaListado.setText("Inicio de la lista (volviendo al final)."); 
            }
        }
    }
    
    private void cambiarCriterioOrdenamientoAction(ActionEvent e) {
        String criterio = (String) selectorOrden.getSelectedItem();
        Comparator<Residuo> comparador = null;

        switch (criterio) {
            case "Peso":
                comparador = new ComparadorPorPeso();
                break;
            case "Tipo":
                comparador = new ComparadorPorTipo();
                break;
            case "Prioridad Ambiental":
                comparador = new ComparadorPorPrioridad();
                break;
            // "ID" utiliza el Comparable natural de la clase Residuo
            default:
        }
        java.util.List<Residuo> listaAux = new java.util.ArrayList<>();
        for (Residuo r : sistema.getListaResiduos()) {
            listaAux.add(r);
        }

        if (comparador != null) {
            listaAux.sort(comparador);
        } else {
            // Ordena por ID (comparable natural)
            java.util.Collections.sort(listaAux);
        }
        
        mostrarListaOrdenada(listaAux, criterio);
        
    }
    
    private void mostrarListaOrdenada(java.util.List<Residuo> lista, String criterio) {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA ORDENADA POR: ").append(criterio).append("\n\n");
        for (Residuo r : lista) {
            sb.append(r.toString()).append("\n");
        }
        areaListado.setText(sb.toString());
    }
    
    private void actualizarPantalla() {
        StringBuilder sbEstadisticas = new StringBuilder();
        sbEstadisticas.append("CENTRO DE RECICLAJE\n");
        sbEstadisticas.append("Residuos en espera: ").append(sistema.getPilaCentroReciclaje().getSize()).append("\n");
        
        if (!sistema.getPilaCentroReciclaje().estaVacia()) {
             try {
                sbEstadisticas.append("Tope (LIFO): ").append(sistema.getPilaCentroReciclaje().peek().getNombre()).append("\n\n");
             } catch (java.util.EmptyStackException e) {
             }
        }

        sbEstadisticas.append("--- ESTADÍSTICAS GLOBALES ---\n");
        
        areaEstadisticas.setText(sbEstadisticas.toString());
        inicializarIterador();
    }
}
