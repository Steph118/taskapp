package com.steph18.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Entity
@Table(name = "persons")
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public class Person extends BaseEntity {
    public static String TAG = "P";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String name;
    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String firstname;
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private User User;
}
