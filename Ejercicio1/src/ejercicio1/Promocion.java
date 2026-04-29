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
public interface Promocion {
    String getNombre();
    DescuentoAplicado calcularDescuento(CarritoContexto contexto);
    boolean esAcumulableCon(Promocion otra);
}
