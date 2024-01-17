package org.judy.algoarena.models;

import jakarta.persistence.*;

@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "input", columnDefinition = "TEXT", nullable = false)
    private String input;

    @Column(name = "output", columnDefinition = "TEXT", nullable = false)
    private String output;

    @Column(name = "is_example", nullable = false)
    private Boolean isExample;

    public TestCase() {
    }

    public TestCase(Long id, Problem problem, String input, String output, Boolean isExample) {
        this.id = id;
        this.problem = problem;
        this.input = input;
        this.output = output;
        this.isExample = isExample;
    }

    public TestCase(Problem problem, String input, String output, Boolean isExample) {
        this.problem = problem;
        this.input = input;
        this.output = output;
        this.isExample = isExample;
    }

    public Long getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getExample() {
        return isExample;
    }

    public void setExample(Boolean example) {
        isExample = example;
    }
}
