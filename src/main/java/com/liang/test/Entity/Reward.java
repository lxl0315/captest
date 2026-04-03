package com.liang.test.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.liang.test.DTO.PageDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Reward {
    @TableId(value = "reward_id")
    private Integer rewardId;
    private String rewardName;
    private BigDecimal requiredPoints;
    private Integer rewardQuantity;
    private String description;
}
