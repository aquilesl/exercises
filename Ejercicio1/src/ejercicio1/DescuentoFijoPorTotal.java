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
public class DescuentoFijoPorTotal implements Promocion {
    private double umbral;
    private double descuentoFijo;
    private boolean acumulable;

    public DescuentoFijoPorTotal(double umbral, double descuentoFijo, boolean acumulable) {
        this.umbral = umbral;
        this.descuentoFijo = descuentoFijo;
        this.acumulable = acumulable;
    }

    @Override
    public String getNombre() {
        return "$" + descuentoFijo + " descuento por compra > $" + umbral;
    }

    @Override
    public DescuentoAplicado calcularDescuento(CarritoContexto contexto) {
        if (contexto.getTotalSinDescuentos() > umbral) {
            return new DescuentoAplicado(getNombre(), descuentoFijo,
                "Compra supera $" + umbral + ", se descuentan $" + descuentoFijo);
        }
        return new DescuentoAplicado(getNombre(), 0, "No aplica: total no supera umbral");
    }

    @Override
    public boolean esAcumulableCon(Promocion otra) {
        if (!acumulable) return false;
        
        if (otra instanceof DescuentoFijoPorTotal) return false;
            return true;
    }
}
