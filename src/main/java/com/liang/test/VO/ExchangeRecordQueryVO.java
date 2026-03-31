package com.liang.test.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExchangeRecordQueryVO {
    private String employeeId;
    private String rewardName;
    private BigDecimal consumePoints;
    private LocalDateTime exchangeTime;
}
