/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.estructuras;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author arianamoreira
 */
public class IteradorCircular<E> implements ListIterator<E> {

    private NodeList<E> actual;
    private boolean primeraIteracion = true;
    private final NodeList<E> cabeza = null;
    
    private int indice = 0;
    private int size;
    
    public IteradorCircular(NodeList<E> cabeza, int size){
        this.actual = cabeza;
        this.size = size;
    }
    public IteradorCircular(){
        actual = cabeza;
    }
    
    //Implementar Metodo de avance 
    
    @Override
    public boolean hasNext(){
        if(cabeza == null) return false;
        return actual != cabeza || primeraIteracion;
    }
    
    @Override
    public E next(){
        if(!hasNext()){
            throw new NoSuchElementException("Se completo el ciclo");
        }
        E dato = actual.getContenido();
        
        actual = actual.getSiguiente();
        primeraIteracion = false;
        indice++;
        return dato;
    }
    
    @Override
    public int nextIndex(){
        return indice % size;
    }
    
    //Implementar mEtodo retroceso
    
    @Override
    public boolean hasPrevious(){
        return !estaVacia();
    }
    
    @Override
    public E previous(){
        if(!hasPrevious()){
            throw new NoSuchElementException("No hay elemento previo");
        }
        actual = actual.getAnterior();
        primeraIteracion = false;
        
        return actual.getContenido();
    }
    
    @Override
    public int previousIndex(){
        return (indice ==0) ?(size-1) : (indice -1);
    }
    
    @Override
    public void remove() { 
        throw new UnsupportedOperationException("Remover no está implementado."); 
    }
    
    @Override
    public void set(E e) { 
        throw new UnsupportedOperationException("Setear no está implementado."); 
    }
    
    @Override
    public void add(E e) { 
        throw new UnsupportedOperationException("Añadir no está implementado."); 
    }

    private boolean estaVacia() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
