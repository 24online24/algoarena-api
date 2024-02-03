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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Problem> problems;

    public Category(String categoryName, List<Problem> problems) {
        this.categoryName = categoryName;
        this.problems = problems;
    }
}
