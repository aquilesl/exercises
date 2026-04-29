/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class CounterWidgetFactory implements WidgetFactory {
    @Override
    public Widget createWidget(Map<String, Object> config, 
                               DataSourceRegistry registry, 
                               WidgetFactory fallbackFactory) {
        String id = (String) config.get("id");
        String sourceId = (String) config.get("sourceId");
        DataSource ds = registry.getDataSource(sourceId);
        return new CounterWidget(id, ds);
    }
}
