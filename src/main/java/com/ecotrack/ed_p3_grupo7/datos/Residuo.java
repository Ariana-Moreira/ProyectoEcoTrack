/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.datos;

/**
 *
 * @author arianamoreira
 */
public class Residuo {
    //Atributos
    String id;
    String nombre;
    String tipoResiduo; // organicos,plasticos,vidrio,etc
    double peso;
    //Date fechaRecoleccion;
    Zona zona;
    int nivelPrioridad;
    
    public Residuo(String id, String nombre, double peso, String tipo, Zona zona, int prioridad){
        this.id = id;
        this.nivelPrioridad = prioridad;
        this.nombre = nombre;
        this.peso = peso;
        this.tipoResiduo = tipo;
        this.zona = zona;
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
    
    @Override
    public String toString(){
        return "ID del Residuo: "+id+ ",Tipo: " +tipoResiduo+ ", Peso: "+peso+ " kg, Zona: " + zona.getNombre(); 
    }
}
