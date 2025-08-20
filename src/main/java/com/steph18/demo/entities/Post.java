package com.steph18.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Table(name = "posts")
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private int views = 0;

    @ManyToOne
    private Publisher postedBy;
}
