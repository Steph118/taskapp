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
@Data
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("A")
public class Publisher extends Person {
    public static String TAG = "A";

    @OneToMany(mappedBy = "postedBy")
    private List<Post> posts = new ArrayList<>();
}
