package wolox.training.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    Book deleteById(Long id);

}

