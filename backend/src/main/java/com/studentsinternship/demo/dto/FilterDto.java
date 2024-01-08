package com.studentsinternship.demo.dto;

import lombok.Data;

@Data
public class FilterDto {
    String position;
    String companyName;
    String industry;
    String location;
    Long salaryLowerBound;
    Long salaryUpperBound;
    Long durationLowerBound;
    Long durationUpperBound;
}
