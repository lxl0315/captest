package com.liang.test.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LearningRecordQueryVO {
    private String employeeId;
    private String employeeName;
    private Integer pointRuleId;
    private String typeName;
    private String activityTitle;
    private BigDecimal learningHours;
    private LocalDate completedDate;
    private BigDecimal awardedPoints;
}
