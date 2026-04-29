/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alvaro
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Producto laptop = new Producto("SKU001", "Laptop", "Electrónica", 800.0);
        Producto mouse = new Producto("SKU002", "Mouse", "Electrónica", 20.0);
        Producto libro = new Producto("SKU003", "Libro Java", "Libros", 50.0);
        
        // Crear carrito
        List<ItemCarrito> items = Arrays.asList(
            new ItemCarrito(laptop, 1),
            new ItemCarrito(mouse, 3),
            new ItemCarrito(libro, 2)
        );
        
        // Contexto
        CarritoContexto contexto = new CarritoContexto(items, true);
        
        // Promociones
        List<Promocion> promociones = Arrays.asList(
            new DescuentoPorCategoria("Electrónica", 10, true),
            new DescuentoFijoPorTotal(900, 50, true),
            new Promocion2x1(Arrays.asList("SKU002"), true),
            new EnvioGratisPremium(15, true)
        );
        
        // Procesador
        ProcesadorPromociones procesador = new ProcesadorPromociones();
        ResumenDescuentos resultado = procesador.aplicarPromociones(promociones, contexto);
        
        // Mostrar resultados
        System.out.println("=== RESULTADO DESCUENTOS ===");
        System.out.printf("Total original: $%.2f%n", resultado.getTotalOriginal());
        System.out.printf("Total final: $%.2f%n", resultado.getTotalFinal());
        System.out.printf("Ahorro total: $%.2f%n", 
            resultado.getTotalOriginal() - resultado.getTotalFinal());
        System.out.println("\nDescuentos aplicados:");
        
        for (DescuentoAplicado d : resultado.getDescuentosAplicados()) {
            System.out.printf(" - %s: $%.2f%n", d.getDescripcion(), d.getMontoDescontado());
        }
    }
    
}
