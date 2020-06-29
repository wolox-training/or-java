package wolox.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

import java.util.List;

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    ;
}
