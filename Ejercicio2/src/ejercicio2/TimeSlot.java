/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.time.LocalDateTime;

/**
 *
 * @author Alvaro
 */
public class TimeSlot {
    private final LocalDateTime start;
    private final LocalDateTime end;
    
    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("Invalid time slot");
        }
        this.start = start;
        this.end = end;
    }
    
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    
    public boolean overlaps(TimeSlot other) {
        if (other == null) return false;
        return !this.end.isBefore(other.start) && !this.start.isAfter(other.end);
    }
}
