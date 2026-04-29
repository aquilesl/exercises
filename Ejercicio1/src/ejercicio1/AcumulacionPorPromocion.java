/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author Alvaro
 */
public class AcumulacionPorPromocion implements ReglaAcumulacion {
    @Override
    public boolean sePuedenAcumular(Promocion p1, Promocion p2) {
        return p1.esAcumulableCon(p2) && p2.esAcumulableCon(p1);
    }
}
