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
public class DescuentoAplicado {
    private String nombrePromocion;
    private double montoDescontado;
    private String descripcion;

    public DescuentoAplicado(String nombrePromocion, double montoDescontado, String descripcion) {
        this.nombrePromocion = nombrePromocion;
        this.montoDescontado = montoDescontado;
        this.descripcion = descripcion;
    }

    public String getNombrePromocion() { return nombrePromocion; }
    public double getMontoDescontado() { return montoDescontado; }
    public String getDescripcion() { return descripcion; }
}
