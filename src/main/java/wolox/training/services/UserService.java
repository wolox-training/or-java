package wolox.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(BookNotFoundException::new);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user, User modifiedUser) {
        user.setUsername(modifiedUser.getUsername());
        user.setName(modifiedUser.getName());
        user.setBirthDate(modifiedUser.getBirthDate());
        return userRepository.save(user);
    }
}
