package mhct.task.manager.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import mhct.task.manager.util.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    private TaskStatus status;

    private LocalDateTime dueDate;
}
