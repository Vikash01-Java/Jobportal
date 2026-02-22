package com.jobportal.dto;

import lombok.*;

@Data
public class ApplicationDto {
    private Long id;
    private String status;
    private Long userId;
    private Long jobId;
}
