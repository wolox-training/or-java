package wolox.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BookService bookService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(BookNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user, User modifiedUser) {
        user.setUsername(modifiedUser.getUsername());
        user.setName(modifiedUser.getName());
        user.setBirthDate(modifiedUser.getBirthDate());
        return userRepository.save(user);
    }

    public User deleteBookToUser(Long user_id, Long book_id) {
        User user = this.findById(user_id);
        Book book = bookService.findById(book_id);
        user.addBook(book);
        return this.save(user);
    }

    public User addBookToUser(Long user_id, Long book_id) {
        User user = this.findById(user_id);
        Book book = bookService.findById(book_id);
        user.deleteBook(book);
        return this.save(user);
    }
}
