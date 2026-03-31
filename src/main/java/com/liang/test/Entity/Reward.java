package com.liang.test.Entity;

import com.liang.test.DTO.PageDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Reward extends PageDTO {
    private Integer rewardId;
    private String rewardName;
    private BigDecimal requiredPoints;
    private Integer rewardQuantity;
    private String description;
}
