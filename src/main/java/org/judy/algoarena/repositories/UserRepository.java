package org.judy.algoarena.repositories;

import java.util.Optional;

import org.judy.algoarena.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
