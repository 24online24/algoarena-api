package org.judy.algoarena.repositories;

import org.judy.algoarena.models.TestCase;
import org.springframework.data.repository.CrudRepository;

public interface TestCaseRepository extends CrudRepository<TestCase, Long> {
}
