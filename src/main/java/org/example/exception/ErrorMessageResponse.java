package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {

    private String message;
    private String detailedMessage;
    private LocalDateTime dateTime;

}
