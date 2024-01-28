package org.judy.algoarena.repositories;

import org.judy.algoarena.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
}
