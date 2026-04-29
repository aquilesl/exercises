/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class WidgetFactoryRegistry {
    private Map<String, WidgetFactory> factories = new HashMap<>();
    
    public void registerFactory(String type, WidgetFactory factory) {
        factories.put(type, factory);
    }
    
    public WidgetFactory getFactory(String type) {
        return factories.getOrDefault(type, 
            (conf, reg, fallback) -> 
                { 
                    throw new IllegalArgumentException("Unknown type: " + type); 
                }
        );
    }
}
