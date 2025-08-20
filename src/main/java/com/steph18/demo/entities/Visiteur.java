package com.steph18.demo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@SuperBuilder
@DiscriminatorValue("V")
public class Visiteur extends Person {
    public static String TAG = "V";
    @OneToMany(mappedBy = "author")
    private List<Comment> posts = new ArrayList<>();
}
