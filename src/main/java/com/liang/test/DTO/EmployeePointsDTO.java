package com.liang.test.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeePointsDTO {
    private Integer pointRuleId;
    private String typeName;
    private BigDecimal totalPoints;
}
