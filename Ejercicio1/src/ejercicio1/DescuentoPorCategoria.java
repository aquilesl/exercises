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
public class DescuentoPorCategoria implements Promocion {
    private String categoria;
    private double porcentaje;
    private boolean acumulable;

    public DescuentoPorCategoria(String categoria, double porcentaje, boolean acumulable) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
        this.acumulable = acumulable;
    }

    @Override
    public String getNombre() {
        return porcentaje + "% descuento en " + categoria;
    }

    @Override
    public DescuentoAplicado calcularDescuento(CarritoContexto contexto) {
        double montoCategoria = contexto.getItems().stream()
            .filter(item -> item.getProducto().getCategoria().equals(categoria))
            .mapToDouble(item -> item.getSubtotal())
            .sum();

        double descuento = montoCategoria * (porcentaje / 100.0);
        return new DescuentoAplicado(getNombre(), descuento,
            "Aplicado " + porcentaje + "% de descuento en categoría " + categoria);
    }

    @Override
    public boolean esAcumulableCon(Promocion otra) {
        if (!acumulable) 
            return false;
        
        if (otra instanceof DescuentoPorCategoria) return false;
            return true;
    }
}
