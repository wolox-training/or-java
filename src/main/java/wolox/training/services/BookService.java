package wolox.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }


    public Book deleteById(Long id) {
        return bookRepository.deleteById(id);
    }

    ;
}
