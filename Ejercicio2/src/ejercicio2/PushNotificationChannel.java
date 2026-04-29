/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

/**
 *
 * @author Alvaro
 */
public class PushNotificationChannel implements NotificationChannel {
    @Override
    public void send(String userId, String message) {
        System.out.printf("[PUSH] for user %s: %s%n", userId, message);
        // send push
    }
    
    @Override
    public String getChannelName() {
        return "PUSH";
    }
}
