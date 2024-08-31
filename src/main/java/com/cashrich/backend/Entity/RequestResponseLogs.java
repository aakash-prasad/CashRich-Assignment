package com.cashrich.backend.Entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseLogs {

    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userid;

    @Column(name = "type")
    private String type;

    @Column(name = "response")
    private Object response;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
