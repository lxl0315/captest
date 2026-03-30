package com.liang.test.Entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeePoints {
    private String employeeId;
    private BigDecimal totalPoints;
}
