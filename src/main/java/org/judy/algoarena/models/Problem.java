package org.judy.algoarena.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "name_name", nullable = false)
    private String nameName;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(
            name = "problem_category",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "problem_problemset",
            joinColumns = @JoinColumn(name = "problem_id"),
            inverseJoinColumns = @JoinColumn(name = "problemset_id")
    )
    private List<ProblemSet> problemSets;

    public Problem() {
    }

    public Problem(Long id, User author, String nameName, String description, Difficulty difficulty, List<Category> categories, List<ProblemSet> problemSets) {
        this.id = id;
        this.author = author;
        this.nameName = nameName;
        this.description = description;
        this.difficulty = difficulty;
        this.categories = categories;
        this.problemSets = problemSets;
    }

    public Problem(User author, String nameName, String description, Difficulty difficulty, List<Category> categories, List<ProblemSet> problemSets) {
        this.author = author;
        this.nameName = nameName;
        this.description = description;
        this.difficulty = difficulty;
        this.categories = categories;
        this.problemSets = problemSets;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getNameName() {
        return nameName;
    }

    public void setNameName(String nameName) {
        this.nameName = nameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<ProblemSet> getProblemSets() {
        return problemSets;
    }

    public void setProblemSets(List<ProblemSet> problemSets) {
        this.problemSets = problemSets;
    }
}
