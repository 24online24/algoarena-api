package org.judy.algoarena.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @ManyToMany(mappedBy = "categories", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    private List<Problem> problems;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
