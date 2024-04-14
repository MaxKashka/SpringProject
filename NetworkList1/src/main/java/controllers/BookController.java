package controllers;

import networklist1.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import repositories.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    public  BookController(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book addBook(@RequestBody Book book)
    {
        bookRepository.findByIsbn(book.getIsbn())
                .ifPresent(b -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No book with this ISBN!!! " + book.getIsbn());
                });
        return bookRepository.save(book);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<Book> getAll()
    {
        return bookRepository.findAll();
    }
}
