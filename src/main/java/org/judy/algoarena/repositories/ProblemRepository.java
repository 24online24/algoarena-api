package org.judy.algoarena.repositories;

import org.judy.algoarena.models.Problem;
import org.springframework.data.repository.CrudRepository;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
}
