package com.jobportal.dto;

import lombok.*;

@Data
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private Double salary;
}
