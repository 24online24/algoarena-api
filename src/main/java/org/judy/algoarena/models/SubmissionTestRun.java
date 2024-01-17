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

    public SubmissionTestRun() {
    }

    public SubmissionTestRun(Long id, Submission submission, TestCase testCase, SubmissionStatus status) {
        this.id = id;
        this.submission = submission;
        this.testCase = testCase;
        this.status = status;
    }

    public SubmissionTestRun(Submission submission, TestCase testCase, SubmissionStatus status) {
        this.submission = submission;
        this.testCase = testCase;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
}
