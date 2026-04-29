/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class DashboardBuilder {
    private WidgetFactoryRegistry factoryRegistry;
    private DataSourceRegistry dataSourceRegistry;
    
    public DashboardBuilder(WidgetFactoryRegistry factoryRegistry, 
                            DataSourceRegistry dataSourceRegistry) {
        this.factoryRegistry = factoryRegistry;
        this.dataSourceRegistry = dataSourceRegistry;
    }
    
    public Widget buildFromConfig(Map<String, Object> config) {
        String type = (String) config.get("type");
        
        if ("panel".equals(type)) {
            String id = (String) config.get("id");
            PanelWidget panel = new PanelWidget(id);
            List<Map<String, Object>> children = (List<Map<String, Object>>) config.get("children");
            
            for (Map<String, Object> childConfig : children) {
                Widget childWidget = buildFromConfig(childConfig);
                panel.addWidget(childWidget);
            }
            return panel;
        } else {
            WidgetFactory factory = factoryRegistry.getFactory(type);
            return factory.createWidget(config, dataSourceRegistry, null);
        }
    }
}
