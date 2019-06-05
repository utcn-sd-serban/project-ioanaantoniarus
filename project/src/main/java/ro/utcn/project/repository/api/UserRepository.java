package ro.utcn.project.repository.api;

import ro.utcn.project.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(int id);

    User save(User user);

    void delete(int id);

    void update(User user);

    User validateUser(String username, String password);

    Optional<User> findByUsername(String username);

    String getUserType(User user);

}
