/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Alvaro
 */
public class NotificationService {
    private final NotificationChannelFactory channelFactory;
    private final ExecutorService notificationExecutor;
    
    public NotificationService(NotificationChannelFactory factory) {
        this.channelFactory = factory;
        this.notificationExecutor = Executors.newCachedThreadPool();
    }
    
    public void sendNotification(String userId, String message, String channelName) {
        NotificationChannel channel = channelFactory.getChannel(channelName);
        //async
        notificationExecutor.submit(() -> {
            try {
                channel.send(userId, message);
            } catch (Exception e) {
                System.err.println("Error sending notification: " + e.getMessage());
            }
        });
    }
    
    public void shutdown() {
        notificationExecutor.shutdown();
    }
}
