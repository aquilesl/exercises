/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Alvaro
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // configuring channels
        NotificationChannelFactory factory = new NotificationChannelFactory();
        factory.registerChannel("EMAIL", new EmailNotificationChannel());
        factory.registerChannel("SMS", new SmsNotificationChannel());
        factory.registerChannel("PUSH", new PushNotificationChannel());
        
        // adding new channel
        factory.registerChannel("WHATSAPP", new NotificationChannel() {
            @Override
            public void send(String userId, String message) {
                System.out.printf("[WHATSAPP] for User %s: %s%n", userId, message);
            }
            
            @Override
            public String getChannelName() {
                return "WHATSAPP";
            }
        });
        
        NotificationService notificationService = new NotificationService(factory);
        BookingService bookingService = new BookingService(notificationService);
        
        // defaultchannel
        bookingService.setDefaultChannel("WHATSAPP");
        
        // concurrency exampl e
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 10; i++) {
            final int userId = i;
            executor.submit(() -> {
                BookingRequest request = new BookingRequest(
                    "SALA-001",
                    new TimeSlot(
                        LocalDateTime.now().plusHours(userId),
                        LocalDateTime.now().plusHours(userId + 1)
                    ),
                    "USER-" + userId
                );
                
                try {
                    Booking booking = bookingService.reserve(request);
                    System.out.println("Booking created: " + booking.getId());
                } catch (IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });
        }
        
        executor.shutdown();
    }
    
}
