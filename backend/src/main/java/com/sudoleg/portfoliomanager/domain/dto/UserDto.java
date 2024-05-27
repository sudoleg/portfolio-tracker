package com.sudoleg.portfoliomanager.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Schema(description = "Automatically generated user ID of the user", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "", example = "my-username", accessMode = Schema.AccessMode.READ_WRITE)
    private String username;

    @Schema(description = "First name", example = "John", accessMode = Schema.AccessMode.READ_WRITE)
    private String name;

    @Schema(description = "Family name", example = "Doe", accessMode = Schema.AccessMode.READ_WRITE)
    private String surname;

    @Schema(description = "Email of the user", example = "john.doe@email.com", accessMode = Schema.AccessMode.READ_WRITE)
    private String email;
}
