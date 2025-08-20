package com.steph18.demo.entities;

import com.steph18.demo.enums.TaskPriority;
import com.steph18.demo.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "due_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime dueDate;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createDate;

    private TaskPriority priority;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_id")
//    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryTask category;

}
