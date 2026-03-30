package com.liang.test.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class LearningRecordQueryDTO extends PageDTO{
    private String employeeId;
    private Integer pointRuleId;
    private LocalDate startDate;
    private LocalDate endDate;

}
