package org.judy.algoarena.repositories;

import java.util.List;

import org.judy.algoarena.models.Submission;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    List<Submission> findByAuthorId(Long authorId);
}
