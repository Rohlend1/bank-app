package org.bank.globalutils.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ResponseMessage {
    
    private HttpStatus status;

    private Integer code;

    private String message;
    
    private LocalDateTime time;
}