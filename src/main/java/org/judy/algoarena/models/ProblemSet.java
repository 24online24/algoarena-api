package org.judy.algoarena.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProblemSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
