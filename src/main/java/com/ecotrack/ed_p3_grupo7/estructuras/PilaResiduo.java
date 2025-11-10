/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.estructuras;

import com.ecotrack.ed_p3_grupo7.datos.Residuo;

/**
 *
 * @author arianamoreira
 */
public class PilaResiduo {
    //El tope es el ultimo q se agrego LIFO
    private NodeList<Residuo> tope;
    private int size;
    
    public PilaResiduo(){
        this.tope = null;
        this.size = 0;
    }
    
    public boolean estaVacia(){
        return tope == null;
    }
    
    public int getSize(){
        return size;
    }
    
    public void push(Residuo residuo){
        NodeList<Residuo> nuevoNodo = new NodeList<>(residuo);
        nuevoNodo.setSiguiente(this.tope);
        tope = nuevoNodo;
        size++;
    }
    
    public Residuo pop() throws java.util.EmptyStackException {
        if(estaVacia()){
            throw new java.util.EmptyStackException();
        }
        Residuo datoDevuelto = tope.getContenido();
        tope = tope.getSiguiente();
        size--;
        return datoDevuelto;
    }
    
    public Residuo peek() throws java.util.EmptyStackException {
        if (estaVacia()) {
            throw new java.util.EmptyStackException();
        }
        
        return this.tope.getContenido();
    }
}
