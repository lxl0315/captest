package com.liang.test.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExchangeRecord {
    private Integer exchangeId;
    private String employeeId;
    private Integer rewardId;
    private BigDecimal consumePoints;
    private LocalDateTime exchangeTime;
}
