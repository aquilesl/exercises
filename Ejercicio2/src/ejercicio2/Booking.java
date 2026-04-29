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
public class Booking {
    private final String id;
    private final String resourceId;
    private final TimeSlot timeSlot;
    private final String userId;

    public Booking(String id, String resourceId, TimeSlot timeSlot, String userId) {
        this. id = id;
        this. resourceId = resourceId;
        this. timeSlot = timeSlot;
        this. userId = userId;
    }
    public String getId() {
        return id;
    }
    public String getResourceId() {
        return resourceId;
    }
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
    public String getUserId() {
        return userId;
    }
}
