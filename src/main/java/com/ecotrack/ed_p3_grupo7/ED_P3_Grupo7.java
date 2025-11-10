/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ecotrack.ed_p3_grupo7;

import com.ecotrack.ed_p3_grupo7.datos.Residuo;
import com.ecotrack.ed_p3_grupo7.datos.Zona;
import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;

/**
 *
 * @author arianamoreira
 */


public class ED_P3_Grupo7 {
    
    private static final String Archivo_Datos = "EcoTrack.ser";

    public static void main(String[] args) {
        //Comienza la logica del sistema
        SistemaEcoTrack sistema = SistemaEcoTrack.cargarDatos(Archivo_Datos);
        System.out.println("Sistema EcoTrack iniciado. Residuos cargados: " 
                            + sistema.getListaResiduos().getLongitud());
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SistemaEcoTrack.guardarDatos(sistema, Archivo_Datos);
        }));
        System.out.println("El sistema está listo para ser gestionado");
        
        System.out.println("Datos de prueba");

        Zona z1 = new Zona("Z1", "Zona Norte - Residencial");
        Zona z2 = new Zona("Z2", "Zona Industrial - Crítica");
        Zona z3 = new Zona("Z3", "Zona Centro - Comercial");

        sistema.getMapaZonas().put("Z1", z1);
        sistema.getMapaZonas().put("Z2", z2);
        sistema.getMapaZonas().put("Z3", z3);
        Residuo r1 = new Residuo("R001", "Desechos Orgánicos", 50.0, "Orgánico", z2, 5);
        Residuo r2 = new Residuo("R002", "Desechos Industriales", 100.0, "Metal", z2, 4);
    
        Residuo r3 = new Residuo("R003", "Botellas de Plástico", 5.0, "Plástico", z1, 2);
        Residuo r4 = new Residuo("R004", "Cartón y Papel", 15.0, "Papel", z3, 3);
    
        sistema.registrarResiduo(r1);
        sistema.registrarResiduo(r2);
        sistema.registrarResiduo(r3);
        sistema.registrarResiduo(r4);
    
        sistema.moverReciclaje(r3); 

        System.out.println("\nEstado Inicial");
        System.out.println("Total Residuos en Lista: " + sistema.getListaResiduos().getLongitud());
        System.out.println("Residuos en Pila (Reciclaje): " + sistema.getPilaCentroReciclaje().getSize());
        System.out.println("Utilidad Z1 (Residencial): " + z1.getUtilidad()); 
        System.out.println("Utilidad Z2 (Industrial): " + z2.getUtilidad()); 
    
        Zona zonaMasUrgente = sistema.colaRutas.peek(); 
        System.out.println("\nZONA con MÁXIMA PRIORIDAD (peek): " + zonaMasUrgente.getNombre() + 
                       " (Utilidad: " + zonaMasUrgente.getUtilidad() + ")");
}}
