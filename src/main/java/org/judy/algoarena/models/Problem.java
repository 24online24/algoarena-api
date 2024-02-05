package org.judy.algoarena.models;

import java.util.List;

import jakarta.persistence.CascadeType;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "problem_category", joinColumns = @JoinColumn(name = "problem_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @Column(name = "example_input", columnDefinition = "TEXT", nullable = false)
    private String exampleInput;

    @Column(name = "example_output", columnDefinition = "TEXT", nullable = false)
    private String exampleOutput;

    @Column(name = "input", columnDefinition = "TEXT", nullable = false)
    private String input;

    @Column(name = "output", columnDefinition = "TEXT", nullable = false)
    private String output;

    public Problem(
            User author,
            String name,
            String description,
            Difficulty difficulty,
            List<Category> categories,
            String exampleInput,
            String exampleOutput,
            String input,
            String output) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.categories = categories;
        this.exampleInput = exampleInput;
        this.exampleOutput = exampleOutput;
        this.input = input;
        this.output = output;
    }
}
