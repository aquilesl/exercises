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
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DatabaseRepository repository = new DatabaseRepository();
        EmailSender emailSender = new EmailSender();
        PdfGenerator pdfGenerator = new PdfGenerator();
        
        
        LoanService loanService = new LoanService(repository, emailSender, pdfGenerator);
        
        
        User user = new User("U-1", "Laura", "laura@example.com");
        Book book = new Book("978-1234567890", "Clean Code", "Robert C. Martin");
        
        
        loanService.borrowBook(user, book);
        loanService.returnBook(user, book);
    }
    
}
