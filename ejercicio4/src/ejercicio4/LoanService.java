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
public class LoanService {
    private static final int LOAN_DAYS = 15;
    private static final double FINE_PER_DAY = 0.50;
    
    private final Repository repository;
    private final NotificationService notificationService;
    private final DocumentGenerator documentGenerator;
    private final LoanValidator validator;
    
    public LoanService(Repository repository, 
                       NotificationService notificationService,
                       DocumentGenerator documentGenerator) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.documentGenerator = documentGenerator;
        this.validator = new LoanValidator();
    }
    
    public void borrowBook(User user, Book book) {
        if (!validator.canBorrow(user, book)) {
            return;
        }
        
        book.borrow(user, java.time.LocalDate.now(), LOAN_DAYS);
        user.addBorrowedBook(book);
        
        repository.saveBook(book);
        repository.saveUser(user);
        
        String receipt = documentGenerator.generateLoanReceipt(user, book);
        notificationService.send(user.getEmail(), "Loan confirmed", receipt);
    }
    
    public void returnBook(User user, Book book) {
        if (!user.hasBorrowedBook(book)) {
            System.out.println("This user did not borrow this book");
            return;
        }
        
        long lateDays = book.calculateLateDays(java.time.LocalDate.now());
        if (lateDays > 0) {
            user.addFine(lateDays * FINE_PER_DAY);
        }
        
        book.returnBook();
        user.removeBorrowedBook(book);
        
        repository.saveBook(book);
        repository.saveUser(user);
        
        notificationService.send(user.getEmail(), "Book returned", 
                                "Thanks for returning " + book.getTitle());
    }
    
    public void blockUser(User user) {
        user.block();
        repository.saveUser(user);
        notificationService.send(user.getEmail(), "Account blocked", 
                                "Your account has been blocked");
    }
}