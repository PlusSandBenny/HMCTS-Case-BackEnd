package mhct.task.manager.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mhct.task.manager.util.TaskStatus;

/**
 * Entity class representing a Task in the task management system.
 * Maps to the "tasks" table in the database.
 * 
 * This class handles the persistence, validation, and lifecycle management
 * of individual tasks created by users.
 * 
 * @author Your Name
 * @version 1.0
 */

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title/summary of the task.
     * Must be descriptive enough to identify the task quickly.
     * 
     * Validation rules:
     * - Cannot be null, empty, or contain only whitespace
     * - Must contain between 3 and 100 characters
     */
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    /**
     * Detailed description of the task.
     * Optional field that can provide additional context or instructions.
     * 
     * Validation rules:
     * - Maximum length of 500 characters
     */
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    /**
     * Current state of the task in its lifecycle.
     * Stored as a String in the database (e.g., "PENDING", "IN_PROGRESS", "COMPLETED").
     * Defaults to PENDING when a new task is created.
     * Cannot be null - every task must have a status.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
