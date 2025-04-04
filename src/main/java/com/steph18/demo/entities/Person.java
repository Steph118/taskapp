package com.steph18.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@NoArgsConstructor
@Getter
@Setter
public class Person extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firstname;
    private String email;

    @OneToOne
    private User User;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Task> tasks = new ArrayList<>();

}
