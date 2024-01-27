package com.sudoleg.portfoliomanager;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents API errors.
 * Contains relevant information about errors during REST calls.
 */
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    //holds the operation call status
    private HttpStatus status;

    // holds an array of user-friendly messages about the error
    private List<String> messages;
}
