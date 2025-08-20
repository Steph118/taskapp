package com.steph18.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor // 👈 JPA a besoin d’un constructeur vide
@AllArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @Column(name = "version", nullable = false)
    private int version = 1;
}
