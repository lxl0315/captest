package com.liang.test.Service;

import com.liang.test.DTO.ExchangeDTO;
import com.liang.test.Entity.EmployeePoints;
import com.liang.test.Entity.Reward;
import com.liang.test.VO.ExchangeRecordQueryVO;
import com.liang.test.mapper.EmployeePointsMapper;
import com.liang.test.mapper.ExchangeRecordMapper;
import com.liang.test.mapper.RewardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ExchangeServiceImplTest {
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @Autowired
    private EmployeePointsMapper employeePointsMapper;
    @Autowired
    private RewardMapper rewardMapper;
    @Test
    public void testExchange(){
        EmployeePoints before=employeePointsMapper.selectById("E0005");
        assertNotNull(before);
        Reward beforeReward=rewardMapper.selectById(1);
        assertNotNull(beforeReward);
        assertNotNull(beforeReward.getRewardQuantity());

        BigDecimal beforePoint=before.getTotalPoints();
        Integer beforeQuantity=beforeReward.getRewardQuantity();

        ExchangeDTO dto=new ExchangeDTO();
        dto.setEmployeeId("E0005");
        dto.setRewardId(1);
        exchangeRecordService.exchange(dto);

        EmployeePoints after=employeePointsMapper.selectById("E0005");
        assertNotNull(after);
        Reward afterReward=rewardMapper.selectById(1);
        assertNotNull(afterReward);
        //判断员工积分减少正确
        BigDecimal points=beforePoint.subtract(beforeReward.getRequiredPoints());
        assertEquals(0,after.getTotalPoints().compareTo(points));
        //判断奖励数量减少1
        assertEquals(beforeQuantity-1,afterReward.getRewardQuantity());
    }
    @Test
    public void testQueryExchangeRecord(){
        List<ExchangeRecordQueryVO> list=exchangeRecordService.queryExchangeRecord("E1003");
        assertNotNull(list);
    }
}
