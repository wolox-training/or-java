package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.exceptions.BookIdMismatchException;
import wolox.training.repositories.BookRepository;
import wolox.training.models.Book;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        try {
            return bookRepository.findByTitle(bookTitle);
        } catch (BookNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book Not Found", ex);
        }
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        try {
            return bookRepository.findById(id);
        } catch (BookIdMismatchException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found", ex);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
        return bookRepository.save(book);
    }

    public String book(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "book";
    }
}