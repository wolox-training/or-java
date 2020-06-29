package wolox.training.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wolox.training.models.Book;

import java.util.List;


@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    public Book findById(Long id);

    public List<Book> findByTitle(String title);

    public Book deleteById(Long id);

}

