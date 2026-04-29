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
public class SmsNotificationChannel implements NotificationChannel {
    @Override
    public void send(String userId, String message) {
        System.out.printf("[SMS] to User %s: %s%n", userId, message);
        // Send sms...
    }
    
    @Override
    public String getChannelName() {
        return "SMS";
    }
}
