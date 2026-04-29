/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Alvaro
 */
public class BookingService {

    private final ConcurrentHashMap<String, Set<Booking>> bookingsByResource;

    private final ConcurrentHashMap<String, Booking> bookingsById;

    private final ConcurrentHashMap<String, ReentrantReadWriteLock> resourceLocks;

    private final AtomicLong idGenerator;

    private final NotificationService notificationService;

    private String defaultChannel;
    
    public BookingService(NotificationService notificationService) {
        this.bookingsByResource = new ConcurrentHashMap<>();
        this.bookingsById = new ConcurrentHashMap<>();
        this.resourceLocks = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong(1);
        this.notificationService = notificationService;
        this.defaultChannel = "EMAIL";
    }
    
    public void setDefaultChannel(String channelName) {
        this.defaultChannel = channelName;
    }
    
    public Booking reserve(BookingRequest request) {

        if (request == null || request.getResourceId() == null || 
            request.getTimeSlot() == null || request.getUserId() == null) {
            throw new IllegalArgumentException("Invalid booking request");
        }
        
        String resourceId = request.getResourceId();
        
        ReentrantReadWriteLock lock = resourceLocks.computeIfAbsent(
            resourceId, 
            k -> new ReentrantReadWriteLock(true)
        );
        
        lock.writeLock().lock();
        
        try {

            Set<Booking> existingBookings = bookingsByResource.get(resourceId);
            if (existingBookings != null) {
                for (Booking existing : existingBookings) {
                    if (existing.getTimeSlot().overlaps(request.getTimeSlot())) {
                        throw new IllegalStateException(
                            "Time slot overlaps with existing booking for resource " + resourceId
                        );
                    }
                }
            }
            
            String bookingId = generateBookingId();
            Booking newBooking = new Booking(
                bookingId,
                resourceId,
                request.getTimeSlot(),
                request.getUserId()
            );
            
            bookingsByResource.computeIfAbsent(resourceId, k -> 
                ConcurrentHashMap.newKeySet()
            ).add(newBooking);
            bookingsById.put(bookingId, newBooking);
            
            String message = String.format(
                "Booking confirmed: %s to resource %s from %s to %s",
                bookingId, resourceId, 
                request.getTimeSlot().getStart(), 
                request.getTimeSlot().getEnd()
            );
            notificationService.sendNotification(
                request.getUserId(), 
                message, 
                defaultChannel
            );
            
            return newBooking;
            
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public void cancel(String bookingId) {
        if (bookingId == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }
        
        Booking booking = bookingsById.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found: " + bookingId);
        }
        
        String resourceId = booking.getResourceId();
        ReentrantReadWriteLock lock = resourceLocks.get(resourceId);
        
        if (lock == null) {
            throw new IllegalStateException("Lock not found for resource");
        }
        
        lock.writeLock().lock();
        
        try {
            booking = bookingsById.get(bookingId);
            if (booking == null) {
                throw new IllegalArgumentException("Booking already cancelled: " + bookingId);
            }
            
            Set<Booking> resourceBookings = bookingsByResource.get(resourceId);
            if (resourceBookings != null) {
                resourceBookings.remove(booking);

                if (resourceBookings.isEmpty()) {
                    bookingsByResource.remove(resourceId);
                    resourceLocks.remove(resourceId, lock);
                }
            }
            bookingsById.remove(bookingId);
            
            String message = String.format(
                "Booking %s canceled to resource %s",
                bookingId, resourceId
            );
            notificationService.sendNotification(
                booking.getUserId(), 
                message, 
                defaultChannel
            );
            
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public Booking getBooking(String bookingId) {
        return bookingsById.get(bookingId);
    }
    
    public Set<Booking> getBookingsForResource(String resourceId) {
        Set<Booking> bookings = bookingsByResource.get(resourceId);
        return bookings != null ? new HashSet<>(bookings) : Collections.emptySet();
    }
    
    private String generateBookingId() {
        return "BK-" + System.currentTimeMillis() + "-" + idGenerator.getAndIncrement();
    }
}