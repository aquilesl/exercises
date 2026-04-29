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
public class DataSourceRegistry {
    private Map<String, DataSource> sources = new HashMap<>();
    
    public void registerDataSource(DataSource source) {
        sources.put(source.getSourceId(), source);
    }
    
    public DataSource getDataSource(String id) {
        return sources.get(id);
    }
}
