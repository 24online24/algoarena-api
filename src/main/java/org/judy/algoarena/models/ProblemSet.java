package org.judy.algoarena.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "problemSetName", nullable = false, unique = true)
    private String problemSetName;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "problemSets")
    private List<Problem> problems;

    public ProblemSet(String problemSetName, User creator, String description, List<Problem> problems) {
        this.problemSetName = problemSetName;
        this.creator = creator;
        this.description = description;
        this.problems = problems;
    }
}
