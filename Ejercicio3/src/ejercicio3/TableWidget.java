/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

/**
 *
 * @author Alvaro
 */
public class TableWidget extends SimpleWidget {
    public TableWidget(String id, DataSource dataSource) {
        super(id, dataSource);
    }
    
    @Override
    public void render() {
        System.out.println("Render table " + id + " with data: " + currentValue);
    }
}
