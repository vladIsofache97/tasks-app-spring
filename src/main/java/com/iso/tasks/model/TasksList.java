package com.iso.tasks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksList {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(name = "user_id")
    private long userId;
}
