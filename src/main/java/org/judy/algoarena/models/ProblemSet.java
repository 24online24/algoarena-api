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

    public ProblemSet() {
    }

    public ProblemSet(Long id, String problemSetName, User creator, String description, List<Problem> problems) {
        this.id = id;
        this.problemSetName = problemSetName;
        this.creator = creator;
        this.description = description;
        this.problems = problems;
    }

    public ProblemSet(String problemSetName, User creator, String description, List<Problem> problems) {
        this.problemSetName = problemSetName;
        this.creator = creator;
        this.description = description;
        this.problems = problems;
    }

    public Long getId() {
        return id;
    }

    public String getProblemSetName() {
        return problemSetName;
    }

    public void setProblemSetName(String problemSetName) {
        this.problemSetName = problemSetName;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
