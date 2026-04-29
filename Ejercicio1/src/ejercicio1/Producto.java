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
public class Producto {
    private String sku;
    private String nombre;
    private String categoria;
    private double precioUnitario;

    public Producto(String sku, String nombre, String categoria, double precioUnitario) {
        this.sku = sku;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
    }

    public String getSku() { return sku; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecioUnitario() { return precioUnitario; }
}
