package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {

    private final String message;
    private final String detailedMessage;
    private final LocalDateTime dateTime;

}
