/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.estructuras;

import java.util.Iterator;

/**
 *
 * @author arianamoreira
 */
public class ListEnlazada<E> implements Iterable<E> {
    private NodeList<E> cabeza;
    private int longitud;
    public ListEnlazada(){
        cabeza = null;
        longitud = 0;
    }
    public boolean estaVacia(){
        return cabeza == null;
    }
    public void agregar(E dato){
        NodeList<E> nuevoNodo = new NodeList<>(dato);
        if(estaVacia()){
            cabeza = nuevoNodo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        }else{
            NodeList<E> ulti = cabeza.getAnterior();
            
            nuevoNodo.setSiguiente(cabeza);
            nuevoNodo.setAnterior(ulti);
            
            ulti.setSiguiente(nuevoNodo);
            cabeza.setAnterior(nuevoNodo);
        }
        longitud ++;
    }
    @Override 
    public Iterator<E> iterator(){
        return new IteradorCircular(cabeza, longitud);
    }
    
    public int getLongitud(){
        return longitud;
    }
    
    public NodeList<E> getCabeza(){
        return cabeza;
    }
}
