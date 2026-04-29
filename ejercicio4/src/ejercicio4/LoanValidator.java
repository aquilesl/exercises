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

public class LoanValidator {
    
    public boolean canBorrow(User user, Book book) {
        if (user.isBlocked()) {
            System.out.println("User is blocked");
            return false;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is not available");
            return false;
        }
        
        if (!user.canBorrow()) {
            System.out.println("User cannot borrow more books or has unpaid fines");
            return false;
        }
        
        return true;
    }
}
