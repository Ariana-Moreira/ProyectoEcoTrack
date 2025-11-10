/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.logica;

import com.ecotrack.ed_p3_grupo7.datos.Zona;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author arianamoreira
 */
public class ComparadorPorUtilidad implements Comparator<Zona>, Serializable{
    private static final long serialVersionUID = 1L;
    @Override
    public int compare(Zona z1, Zona z2){
        return Double.compare(z1.getUtilidad(), z2.getUtilidad());
    }
}
