/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Alvaro
 */
public class NotificationChannelFactory {
    private final Map<String, NotificationChannel> channels = new ConcurrentHashMap<>();
    
    public void registerChannel(String name, NotificationChannel channel) {
        channels.put(name, channel);
    }
    
    public NotificationChannel getChannel(String name) {
        NotificationChannel channel = channels.get(name);
        if (channel == null) {
            throw new IllegalArgumentException("Unknown channel: " + name);
        }
        return channel;
    }
}
