package com.steph18.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category_tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class CategoryTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Le libellé est invalide")
    private String label;
    @NotBlank(message = "La description est invalide")
    private String description;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Task> tasks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryTask that)) return false;
        return Objects.equals(id, that.id);
    }
}
