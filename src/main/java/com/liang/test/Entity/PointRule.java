package com.liang.test.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("point_rules")
public class PointRule {
    @TableId(value = "rule_id")
    private Integer ruleId;
    private String typeName;
    private Integer basePoint;
    private BigDecimal multiplier;
    private String description;
    private Integer isActive;

}
