package com.liang.test.Entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PointRule {
    private Integer ruleId;
    private String typeName;
    private Integer basePoint;
    private BigDecimal multiplier;
    private String description;
    private Integer isActive;
    @TableLogic
    private Boolean deleted;
}
