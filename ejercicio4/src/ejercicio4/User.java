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
import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int MAX_BORROW_LIMIT = 5;
    
    private String id;
    private String name;
    private String email;
    private boolean blocked;
    private List<Book> borrowedBooks;
    private double unpaidFines;
    
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.blocked = false;
        this.borrowedBooks = new ArrayList<>();
        this.unpaidFines = 0;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isBlocked() { return blocked; }
    public List<Book> getBorrowedBooks() { return new ArrayList<>(borrowedBooks); }
    public double getUnpaidFines() { return unpaidFines; }
    
    public boolean canBorrow() {
        return !blocked && 
               borrowedBooks.size() < MAX_BORROW_LIMIT && 
               unpaidFines <= 0;
    }
    
    public void addBorrowedBook(Book book) {
        if (canBorrow()) {
            borrowedBooks.add(book);
        }
    }
    
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }
    
    public void addFine(double amount) {
        this.unpaidFines += amount;
    }
    
    public void block() {
        this.blocked = true;
    }
    
    public boolean hasBorrowedBook(Book book) {
        return borrowedBooks.contains(book);
    }
}
