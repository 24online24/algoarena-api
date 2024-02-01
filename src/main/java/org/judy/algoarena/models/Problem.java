package org.judy.algoarena.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "problem_category", joinColumns = @JoinColumn(name = "problem_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "problem_problemset", joinColumns = @JoinColumn(name = "problem_id"), inverseJoinColumns = @JoinColumn(name = "problemset_id"))
    private List<ProblemSet> problemSets;

    public Problem() {
    }

    public Problem(Long id, User author, String name, String description, Difficulty difficulty,
            List<Category> categories, List<ProblemSet> problemSets) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.categories = categories;
        this.problemSets = problemSets;
    }

    public Problem(User author, String name, String description, Difficulty difficulty, List<Category> categories,
            List<ProblemSet> problemSets) {
        this.author = author;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
