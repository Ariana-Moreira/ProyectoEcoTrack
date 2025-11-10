/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ecotrack.ed_p3_grupo7;

import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;
import com.ecotrack.ed_p3_grupo7.vista.VentanaPrincipal;

/**
 *
 * @author arianamoreira
 */
public class ED_P3_Grupo7 {

    public static void main(String[] args) {
        SistemaEcoTrack sistema = new SistemaEcoTrack();
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        ventana.setVisible(true);
    }
}
