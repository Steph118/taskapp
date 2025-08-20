package com.steph18.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    private Visiteur author;

    @ManyToOne
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
