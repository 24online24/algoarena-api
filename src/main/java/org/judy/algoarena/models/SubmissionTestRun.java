package org.judy.algoarena.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionTestRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "test_case_id", nullable = false)
    private TestCase testCase;

    @Column(name = "status", nullable = false)
    private SubmissionStatus status;

    public SubmissionTestRun(Submission submission, TestCase testCase, SubmissionStatus status) {
        this.submission = submission;
        this.testCase = testCase;
        this.status = status;
    }
}
