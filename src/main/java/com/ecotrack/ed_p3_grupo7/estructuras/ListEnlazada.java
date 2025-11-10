/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.estructuras;

import com.ecotrack.ed_p3_grupo7.datos.NodeList;
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
    
    //Metodos basicos
    
    public boolean estaVacia(){
        return cabeza == null;
    }
    
    public void agregar(E dato){
        NodeList<E> nuevoNodo = NodeList<>(dato);
        if(estaVacia()){
            cabeza = nuevoNodo;
            cabeza.setSiguiente(cabeza);
        }else{
            NodeList<E> actual = cabeza;
            while(actual.getSiguiente() != cabeza){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(cabeza);
        }
        longitud ++;
    }
    @Override 
    public Iterator<E> iterator(){
        return new IteradorCircular();
    }
    
    private class IteradorCicular implements Iterator<E>{
        private NodeList<E> actual;
        private boolean primerIteracion = true;
        
        public IteradorCircular(){
            actual = actual;
        }
        @Override
        public boolean hasNext(){
            return !estaVacia() && (actual != cabeza || primerIteracion);
        }
        
        @Override
        public E next(){
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            E dato = actual.getDato();
            actual = actual.getSiguiente();
            primerIteracion = false;
            return dato;
        }
    }
}
