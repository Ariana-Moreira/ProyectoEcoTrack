/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.logica;

import com.ecotrack.ed_p3_grupo7.datos.Residuo;
import com.ecotrack.ed_p3_grupo7.datos.Zona;
import com.ecotrack.ed_p3_grupo7.estructuras.ListEnlazada;
import com.ecotrack.ed_p3_grupo7.estructuras.PilaResiduo;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author arianamoreira
 */
public class SistemaEcoTrack implements Serializable{
    private static final long serialVersion = 2L;
    
    private ListEnlazada<Residuo> listaResiduos;
    public PriorityQueue<Zona> colaRutas;
    private PilaResiduo pilaCentroReciclaje;
    
    private Map<String, Zona> mapaZonas;
    private Map<String, Double> estadisticas;
    
    public SistemaEcoTrack(){
        this.listaResiduos = new ListEnlazada<>();
        this.pilaCentroReciclaje = new PilaResiduo();
        
        this.colaRutas = new PriorityQueue<>(new ComparadorPorUtilidad());
        
        this.mapaZonas = new HashMap<>();
        this.estadisticas = new HashMap<>();
    }
    
    public void registrarResiduo(Residuo r){
        listaResiduos.agregar(r);
        Zona zonaAfectada =r.getZona();
        if(zonaAfectada != null){
            zonaAfectada.registraResiduo(r.getPeso());
            if(colaRutas.contains(zonaAfectada)){
                colaRutas.remove(zonaAfectada);
            }
            colaRutas.add(zonaAfectada);
        }
        
        String tipo = r.getTipoResiduo();
        double pesoActual = estadisticas.getOrDefault(tipo, 0.0);
        estadisticas.put(tipo,pesoActual = r.getPeso());
    }
    
    
    public Zona despachaVehiculo(){
        Zona zonaIr = colaRutas.poll();
        if(zonaIr != null){
            System.out.println("Despachando auto a: " +zonaIr.getNombre() + " (Utilidad: " +zonaIr.getUtilidad() + ")");
        }
        return zonaIr;
    }
    
    
    public void moverReciclaje(Residuo r){
        pilaCentroReciclaje.push(r);
        
        Zona zonaRecogida = r.getZona();
        if(zonaRecogida != null){
            zonaRecogida.completarRecoleccion(r.getPeso());
            if(colaRutas.contains(zonaRecogida)){
                colaRutas.remove(zonaRecogida);
            }
            colaRutas.add(zonaRecogida);
        }
    }
    
    public static void guardarDatos(SistemaEcoTrack sistema, String nombreArchivo) {
    try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
        
        objectOut.writeObject(sistema);
        System.out.println("Datos de EcoTrack guardados");
        
        } catch (IOException e) {
        System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }
    
    public static SistemaEcoTrack cargarDatos(String nombreArchivo) {
    try (FileInputStream fileIn = new FileInputStream(nombreArchivo);
         ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        
        System.out.println("Datos cargados exitosamente.");
        return (SistemaEcoTrack) objectIn.readObject();
        
    } catch (FileNotFoundException e) {
        System.out.println("⚠️ Archivo de datos no encontrado. Inicializando sistema nuevo.");
    } catch (ClassNotFoundException e) {
        System.err.println("Error de compatibilidad al cargar datos. Inicializando sistema nuevo.");
        //e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Error de al cargar datos. Inicializando sistema nuevo. " + e.getMessage());
        //e.printStackTrace();
    }
    
    return new SistemaEcoTrack();
}
    
    public ListEnlazada<Residuo> getListaResiduos(){
        return listaResiduos;
    }
    
    public Map<String, Zona> getMapaZonas() { 
        return mapaZonas; 
    }
    public PilaResiduo getPilaCentroReciclaje() { 
        return pilaCentroReciclaje; 
    }
}
