package com.liang.test.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RewardQueryDTO extends PageDTO {
    private Integer rewardId;
    private String rewardName;
    private BigDecimal requiredPoints;
    private Integer rewardQuantity;
    private String description;
}