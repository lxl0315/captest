package com.liang.test.DTO;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PointRuleDTO extends PageDTO{
    private Integer ruleId;
    private String typeName;
    private Integer basePoint;
    private BigDecimal multiplier;
    private String description;
    private Integer isActive;
}
