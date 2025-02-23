package dev.twominutes.notetaking.repository.user;

import dev.twominutes.notetaking.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void deleteAll();
}
