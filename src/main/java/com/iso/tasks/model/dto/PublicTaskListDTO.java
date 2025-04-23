package com.iso.tasks.model.dto;

import com.iso.tasks.model.TasksList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicTaskListDTO {
    private Long id;
    private String title;

    public static PublicTaskListDTO from(TasksList tasksList) {
        return PublicTaskListDTO
                .builder()
                .id(tasksList.getId())
                .title(tasksList.getTitle())
                .build();
    }
}
