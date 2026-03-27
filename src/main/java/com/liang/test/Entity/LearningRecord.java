package com.liang.test.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LearningRecord {
    private Integer recordId;
    private String employeeId;
    private String employeeName;
    private Integer pointRuleId;
    private String activityTitle;
    private BigDecimal learningHours;
    private LocalDate completedDate;
    private Integer awardedPoints;

}
