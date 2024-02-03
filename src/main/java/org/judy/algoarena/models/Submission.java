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
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    private String code;

    @Column(name = "language_id", nullable = false)
    private int language_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubmissionStatus status;

    public Submission(User author, Problem problem, String code, int language_id, SubmissionStatus status) {
        this.author = author;
        this.problem = problem;
        this.code = code;
        this.language_id = language_id;
        this.status = status;
    }
}
