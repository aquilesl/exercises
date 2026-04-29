/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio4;

/**
 *
 * @author Alvaro
 */

public class EmailSender implements NotificationService {
    @Override
    public void send(String to, String subject, String body) {
        System.out.println("Sending email to " + to);
        System.out.println(subject);
        System.out.println(body);
    }
}
