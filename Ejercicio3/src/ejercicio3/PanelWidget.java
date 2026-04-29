/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alvaro
 */
public class PanelWidget implements Widget {
    private String id;
    private List<Widget> children = new ArrayList<>();
    
    public PanelWidget(String id) {
        this.id = id;
    }
    
    public void addWidget(Widget widget) {
        children.add(widget);
    }
    
    @Override
    public void update(DataValue newValue) {
        for (Widget child : children) {
            // update changes to child alements....
        }
        render();
    }
    
    @Override
    public void render() {
        System.out.println("Render panel " + id);
        for (Widget child : children) {
            child.render();
        }
    }
    
    @Override
    public String getId() { return id; }
}
