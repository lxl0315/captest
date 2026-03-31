package com.liang.test.Controller;

import com.liang.test.DTO.ExchangeDTO;
import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.Service.ExchangeRecordService;
import com.liang.test.VO.ExchangeRecordQueryVO;
import com.liang.test.common.Result;
import com.liang.test.common.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ExchangeRecord")
public class ExchangeRecordController {
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @PostMapping
    public Result<String> exchange(@RequestBody ExchangeDTO dto){
        exchangeRecordService.exchange(dto);
        return ResultUtil.success("兑换成功");
    }
    @GetMapping
    public Result<List<ExchangeRecordQueryVO>> queryExchangeRecord(@RequestBody String employeeId){
        return ResultUtil.success(exchangeRecordService.queryExchangeRecord(employeeId));
    }
}
