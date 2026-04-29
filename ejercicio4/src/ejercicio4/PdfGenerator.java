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

public class PdfGenerator implements DocumentGenerator {
    @Override
    public String generateLoanReceipt(User user, Book book) {
        return "Receipt for " + user.getName() + " - Book: " + book.getTitle();
    }
}
