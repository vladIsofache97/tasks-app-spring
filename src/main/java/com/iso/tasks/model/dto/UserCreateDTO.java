package com.iso.tasks.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    // TODO: Add constraints for username and password
    @NotNull
    private String username;

    @NotNull
    private String password;

}
