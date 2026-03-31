package com.liang.test.VO;

import com.liang.test.DTO.EmployeePointsDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class EmployeePointsQueryVO {
    private String employeeId;
    private BigDecimal totalPoints;
    private Integer learnRecordCount;
    private List<EmployeePointsDTO> typePoints;
}
