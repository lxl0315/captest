package com.liang.test.Service;

import com.liang.test.DTO.ExchangeDTO;
import com.liang.test.VO.ExchangeRecordQueryVO;

import java.util.List;

public interface ExchangeRecordService {
    void exchange(ExchangeDTO dto);

    List<ExchangeRecordQueryVO> queryExchangeRecord(String employeeId);
}
