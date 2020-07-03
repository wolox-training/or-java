package wolox.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import wolox.training.exceptions.UserIdMismatchException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.services.BookService;
import wolox.training.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;


    @GetMapping("/me")
    public User currentUserName(Principal principal) {

        String username = principal.getName();
        return userService.findByUsername(username);
    }

    @GetMapping
    public Iterable findAll() {
        return userService.findAll();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.save(user);
    }


    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        if (newUser.getId() != id) {
            throw new UserIdMismatchException();
        }

        User user = userService.findById(id);

        return userService.updateUser(user, newUser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/{user_id}/books/{book_id}")
    public User addBookToUser(@PathVariable Long user_id, @PathVariable Long book_id) {
        return userService.addBookToUser(user_id, book_id);
    }

    @DeleteMapping("/{user_id}/books/{book_id}")
    public User deleteBookToUser(@PathVariable Long user_id, @PathVariable Long book_id) {
        return userService.deleteBookToUser(user_id, book_id);
    }
}
