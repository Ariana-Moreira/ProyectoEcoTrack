/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.datos;

/**
 *
 * @author arianamoreira
 */
public class Zona { // la zona urbana u su prioridad
    private String id;
    private String nombre;
    private double utilidad; //ResRecolectado - ResPendiente
    private double pesoRecolectado;
    private double pesoPendiente;
    
    public Zona(String id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.pesoPendiente = 0.0;
        this.pesoRecolectado = 0.0;
        this.utilidad = 0.0;
    }
    
    private void calcularUtilidad(){
        utilidad = pesoRecolectado - pesoPendiente;
    }
    
    public void registraResiduo(double peso){
        pesoPendiente += peso;
        calcularUtilidad();
    }
    
    public void completarRecoleccion(double peso){
        if(pesoPendiente >= peso){
            pesoPendiente -= peso;
            pesoRecolectado += peso;
            calcularUtilidad();
        }else {
             this.pesoRecolectado += this.pesoPendiente;
             this.pesoPendiente = 0.0;
             calcularUtilidad();
        }
        
    }
    
    public double getUtilidad(){
        return utilidad;
    }
    public double setUtilidad(){
        return utilidad;
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
    public double getPesoRecolectado(){
        return pesoRecolectado;
    }
    public double setPesoRecolectado(){
        return pesoRecolectado;
    }
    public double getPesoPendiente(){
        return pesoPendiente;
    }
    public double setPesoPendiente(){
        return pesoPendiente;
    }
}
