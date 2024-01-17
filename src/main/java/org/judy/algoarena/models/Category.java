package org.judy.algoarena.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryName", nullable = false, unique = true)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Problem> problems;

    public Category() {
    }

    public Category(Long id, String categoryName, List<Problem> problems) {
        this.id = id;
        this.categoryName = categoryName;
        this.problems = problems;
    }

    public Category(String categoryName, List<Problem> problems) {
        this.categoryName = categoryName;
        this.problems = problems;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}
