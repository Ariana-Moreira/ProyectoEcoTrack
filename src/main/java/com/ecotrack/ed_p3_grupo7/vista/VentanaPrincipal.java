/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.vista;

import com.ecotrack.ed_p3_grupo7.datos.Residuo;
import com.ecotrack.ed_p3_grupo7.datos.Zona;
import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorPeso;
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorPrioridad;
import com.ecotrack.ed_p3_grupo7.logica.ComparadorPorTipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList; 
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Collections;
import java.util.UUID;
import java.util.Iterator;

public class VentanaPrincipal extends JFrame {
    
    private final SistemaEcoTrack sistema;
    
    // --- Componentes GUI ---
    private JTextArea areaListado;
    private JTextArea areaEstadisticas;
    private JComboBox<String> selectorOrden;
    private JButton btnDespachar;
    private JButton btnRegistrar;
    private JButton btnIterarSiguiente;
    private JButton btnIterarAnterior;
    
    // --- Elemento de Interacción para Recorrido Bidireccional (Requisito 5) ---
    private ListIterator<Residuo> iteradorResiduos; 

    // --- Constructor ---
    public VentanaPrincipal(SistemaEcoTrack sistema) {
        this.sistema = sistema;
        
        setTitle("EcoTrack - Gestión de Residuos Urbanos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        inicializarIterador();
        actualizarPantalla(); // Mostrar el estado inicial cargado
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnRegistrar = new JButton("① Registrar Residuo");
        btnDespachar = new JButton("② Despachar Vehículo (Prioridad)");
        
        controlPanel.add(btnRegistrar);
        controlPanel.add(btnDespachar);
        JPanel iteracionOrdenPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        JPanel ordenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectorOrden = new JComboBox<>(new String[]{"ID (Defecto)", "Peso", "Tipo", "Prioridad Ambiental"});
        JButton btnOrdenar = new JButton("③ Ordenar Lista");
        ordenPanel.add(new JLabel("Ordenar por:"));
        ordenPanel.add(selectorOrden);
        ordenPanel.add(btnOrdenar);
        JPanel iteracionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIterarAnterior = new JButton("<< Anterior");
        btnIterarSiguiente = new JButton("Siguiente >>");
        iteracionPanel.add(btnIterarAnterior);
        iteracionPanel.add(btnIterarSiguiente);
        
        iteracionOrdenPanel.add(ordenPanel);
        iteracionOrdenPanel.add(iteracionPanel);
        areaListado = new JTextArea(15, 40);
        areaListado.setEditable(false);
        JScrollPane scrollListado = new JScrollPane(areaListado);

        areaEstadisticas = new JTextArea(15, 20);
        areaEstadisticas.setEditable(false);
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
        
        add(controlPanel, BorderLayout.NORTH);
        add(scrollListado, BorderLayout.CENTER);
        add(scrollEstadisticas, BorderLayout.EAST);
        add(iteracionOrdenPanel, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(this::registrarResiduoAction);
        btnDespachar.addActionListener(this::despacharVehiculoAction);
        btnOrdenar.addActionListener(this::cambiarCriterioOrdenamientoAction);
        btnIterarSiguiente.addActionListener(this::iterarSiguienteAction);
        btnIterarAnterior.addActionListener(this::iterarAnteriorAction);

        pack();
        setLocationRelativeTo(null); 
    }

    private void registrarResiduoAction(ActionEvent e) {
        try {
            String zonaID = (String) JOptionPane.showInputDialog(this, "Seleccione la Zona:", 
                                                                 "Registro", JOptionPane.QUESTION_MESSAGE, 
                                                                 null, sistema.getMapaZonas().keySet().toArray(), "Z1");
            if (zonaID == null) return;
            Zona zonaAfectada = sistema.getMapaZonas().get(zonaID);
            
            String nombre = JOptionPane.showInputDialog(this, "Nombre (ej. Desechos Orgánicos):");
            if (nombre == null) return;
            
            double peso = Double.parseDouble(JOptionPane.showInputDialog(this, "Peso (kg):"));
            String tipo = JOptionPane.showInputDialog(this, "Tipo (Orgánico, Plástico, etc.):");
            int prioridad = Integer.parseInt(JOptionPane.showInputDialog(this, "Nivel de Prioridad (1=Bajo, 5=Alto):"));
            
            Residuo nuevoResiduo = new Residuo(UUID.randomUUID().toString().substring(0, 5), 
                                                nombre, peso, tipo, zonaAfectada, prioridad);
            
            sistema.registrarResiduo(nuevoResiduo);
            
            JOptionPane.showMessageDialog(this, "Residuo registrado en: " + zonaAfectada.getNombre());
            actualizarPantalla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void despacharVehiculoAction(ActionEvent e) {
        Zona zonaDespachada = sistema.despachaVehiculo(); 
        
        if (zonaDespachada != null) {
            double pesoRecoger = zonaDespachada.getPesoPendiente();
            zonaDespachada.completarRecoleccion(pesoRecoger); 

            JOptionPane.showMessageDialog(this, 
                "Vehículo despachado y recolectado: " + zonaDespachada.getNombre() + 
                "\nPeso Recolectado: " + pesoRecoger + " kg." +
                "\nNueva Utilidad: " + zonaDespachada.getUtilidad(), 
                "Despacho Automático", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            JOptionPane.showMessageDialog(this, "No hay zonas pendientes por atender.", 
                "Despacho Automático", JOptionPane.WARNING_MESSAGE);
        }
        actualizarPantalla();
    }
    private void inicializarIterador() {
        Iterator<Residuo> iter = sistema.getListaResiduos().iterator();
        if (iter instanceof ListIterator) {
            this.iteradorResiduos = (ListIterator<Residuo>) iter;
        }
    }
    
    private void iterarSiguienteAction(ActionEvent e) {
        if (iteradorResiduos != null) {
            if (iteradorResiduos.hasNext()) {
                Residuo r = iteradorResiduos.next();
                areaListado.setText("RECORRIDO CIRCULAR >> (Índice: " + iteradorResiduos.nextIndex() + ")\n" + r.toString());
            } else {
                areaListado.setText("Fin del recorrido. Vuelva al inicio o use << Anterior."); 
            }
        } else {
             areaListado.setText("Lista vacía o iterador no inicializado.");
        }
    }

    private void iterarAnteriorAction(ActionEvent e) {
        if (iteradorResiduos != null) {
            if (iteradorResiduos.hasPrevious()) {
                Residuo r = iteradorResiduos.previous();
                areaListado.setText("<< RECORRIDO CIRCULAR (Índice: " + iteradorResiduos.previousIndex() + ")\n" + r.toString());
            } else {
                areaListado.setText("Inicio del recorrido. Vuelva al final o use Siguiente >>."); 
            }
        } else {
             areaListado.setText("Lista vacía o iterador no inicializado.");
        }
    }
    
    private void cambiarCriterioOrdenamientoAction(ActionEvent e) {
        java.util.List<Residuo> listaAux = new ArrayList<>();
        for (Residuo r : sistema.getListaResiduos()) {
            listaAux.add(r);
        }

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
            default:
        }
        
        if (comparador != null) {
            listaAux.sort(comparador);
        } else {
            Collections.sort(listaAux);
        }
        
        mostrarListaOrdenada(listaAux, criterio);
    }
    
    private void mostrarListaOrdenada(java.util.List<Residuo> lista, String criterio) {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA DE RESIDUOS ORDENADA POR: ").append(criterio).append("\n\n");
        for (Residuo r : lista) {
            sb.append(r.toString()).append("\n");
        }
        areaListado.setText(sb.toString());
    }
    
    private void actualizarPantalla() {
        StringBuilder sbEstadisticas = new StringBuilder();
        sbEstadisticas.append("CENTRO DE RECICLAJE\n");
        sbEstadisticas.append("Elementos en Pila: ").append(sistema.getPilaCentroReciclaje().getSize()).append("\n");
        
        if (!sistema.getPilaCentroReciclaje().estaVacia()) {
             try {
                sbEstadisticas.append("Siguiente a Procesar (PEEK): ").append(sistema.getPilaCentroReciclaje().peek().getNombre()).append("\n");
             } catch (java.util.EmptyStackException e) {
             }
        }
        sbEstadisticas.append("GESTIÓN DE RUTAS (COLA DE PRIORIDAD)\n");
        for (Zona z : sistema.getMapaZonas().values()) {
             sbEstadisticas.append(String.format("Zona %s: Pendiente %.1f kg | Utilidad: %.2f\n", 
                                                z.getID(), z.getPesoPendiente(), z.getUtilidad()));
        }
        
        areaEstadisticas.setText(sbEstadisticas.toString());
        cambiarCriterioOrdenamientoAction(null);
        inicializarIterador();
    }
}