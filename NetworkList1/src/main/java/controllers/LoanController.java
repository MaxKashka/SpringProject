package controllers;

import networklist1.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import repositories.BookRepository;
import repositories.LoanRepository;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanController(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody Loan loan) {
        return bookRepository.findById(loan.getBook().getBookId())
                .map(book -> {
                    loanRepository.save(loan);
                    return ResponseEntity.ok().body("User borrow the book!");
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No book like this!"));
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<?> returnBook(@PathVariable Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loanRepository.delete(loan);
                    return ResponseEntity.ok().body("User returned the book!");
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No loan like this!"));
    }
}




