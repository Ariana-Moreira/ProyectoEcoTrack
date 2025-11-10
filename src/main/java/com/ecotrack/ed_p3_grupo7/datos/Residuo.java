/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.datos;

import java.time.LocalDate;
import java.io.Serializable;
/**
 *
 * @author arianamoreira
 */
public class Residuo implements Comparable<Residuo>, Serializable{
    //Atributos
    private String id;
    private String nombre;
    private String tipoResiduo; // organicos,plasticos,vidrio,etc
    private double peso;
    private LocalDate fechaRecoleccion;
    private Zona zona;
    private int nivelPrioridad;
    
    private static final long serialVersion = 1L;
    
    public Residuo(String id, String nombre, double peso, String tipo, Zona zona, int prioridad){
        this.id = id;
        this.nivelPrioridad = prioridad;
        this.nombre = nombre;
        this.peso = peso;
        this.tipoResiduo = tipo;
        this.zona = zona;
        this.fechaRecoleccion = LocalDate.now();
    }
    
    public double getPeso(){
        return peso;
    }
    public double setPeso(){
        return peso;
    }
    
    public int getNivelPrioridad(){
        return nivelPrioridad;
    }
    public int setNivelPrioridad(){
        return nivelPrioridad;
    }
    
    public String getID(){
        return id;
    }
    public String setID(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String setNombre(){
        return nombre;
    }
    
    public String getTipoResiduo(){
        return tipoResiduo;
    }
    public String setTipoResiduo(){
        return tipoResiduo;
    }
    
    public Zona getZona(){
        return zona;
    }
    public Zona setZona(){
        return zona;
    }
    
    public LocalDate getFechaRecoleccion(){
        return fechaRecoleccion;
    }
    public LocalDate setFechaRecoleccion(){
        return fechaRecoleccion;
    }
    
    @Override
    public String toString(){
        return "ID del Residuo: "+id+ ",Tipo: " +tipoResiduo+ ", Peso: "+peso+ " kg, Zona: " + zona.getNombre(); 
    }
    
    @Override
    public int compareTo(Residuo otro){
        return this.id.compareTo(otro.id);
    }
}
