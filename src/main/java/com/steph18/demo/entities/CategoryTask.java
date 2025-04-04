package com.steph18.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category_tasks")
@NoArgsConstructor
@Getter
@Setter
public class CategoryTask extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String description;


    @OneToMany(mappedBy = "category", cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Task> tasks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryTask that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryTask{" +
                "id=" + id +
                ", libelle='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
