package com.sudoleg.portfoliomanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer userId;

    private String username;

    private String name;

    private String surname;

    private String email;

}
