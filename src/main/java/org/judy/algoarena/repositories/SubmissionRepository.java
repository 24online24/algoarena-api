package org.judy.algoarena.repositories;

import org.judy.algoarena.models.Submission;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
}
