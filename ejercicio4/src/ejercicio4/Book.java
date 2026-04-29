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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;
    private User borrowedBy;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private int timesBorrowed;
    
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
        this.timesBorrowed = 0;
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public User getBorrowedBy() { return borrowedBy; }
    public LocalDate getBorrowedAt() { return borrowedAt; }
    public LocalDate getDueDate() { return dueDate; }
    public int getTimesBorrowed() { return timesBorrowed; }
    
    // Métodos de negocio
    public void borrow(User user, LocalDate borrowDate, int loanDays) {
        this.available = false;
        this.borrowedBy = user;
        this.borrowedAt = borrowDate;
        this.dueDate = borrowDate.plusDays(loanDays);
        this.timesBorrowed++;
    }
    
    public void returnBook() {
        this.available = true;
        this.borrowedBy = null;
        this.borrowedAt = null;
        this.dueDate = null;
    }
    
    public boolean isOverdue(LocalDate currentDate) {
        return dueDate != null && currentDate.isAfter(dueDate);
    }
    
    public long calculateLateDays(LocalDate currentDate) {
        if (!isOverdue(currentDate)) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, currentDate);
    }
}