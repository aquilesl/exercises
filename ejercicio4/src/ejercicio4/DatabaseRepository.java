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

public class DatabaseRepository implements Repository {
    @Override
    public void saveUser(User user) {
        System.out.println("Saving user " + user.getId());
    }
    
    @Override
    public void saveBook(Book book) {
        System.out.println("Saving book " + book.getIsbn());
    }
}
