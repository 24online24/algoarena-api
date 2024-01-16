package org.judy.algoarena.models;

import jakarta.persistence.*;

@Entity
public class SubmissionTestRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;

    @Column(name = "status", nullable = false)
    private SubmissionStatus status;
}
