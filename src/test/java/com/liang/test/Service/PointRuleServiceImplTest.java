package com.liang.test.Service;

import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.Entity.PointRule;
import com.liang.test.mapper.PointRuleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PointRuleServiceImplTest {
    @Autowired
    private PointRuleService pointRuleService;
    @Autowired
    private PointRuleMapper pointRuleMapper;
    @Test
    public void testCreatePointRule(){
        PointRuleDTO dto=new PointRuleDTO();
        dto.setTypeName("MORAL_TRAINING");
        dto.setBasePoint(10);
        dto.setMultiplier(new BigDecimal(1.0));
        dto.setDescription("道德培训");
        dto.setIsActive(1);
        pointRuleService.createPointRule(dto);

        PointRule pointRule=pointRuleMapper.selectById(dto.getRuleId());
        assertNotNull(pointRule);
    }
    @Test
    public void testQueryPointRule(){
        PointRuleDTO dto=new PointRuleDTO();
        dto.setTypeName("LANGUAGE");
        var result=pointRuleService.queryPointRule(dto);

        assertNotNull(result);
        assertNotNull(result.getRecords());
    }
    @Test
    public void testUpdatePointRule(){
        PointRule beforePointRule=pointRuleMapper.selectById(1);
        assertNotNull(beforePointRule);

        PointRuleDTO dto=new PointRuleDTO();
        dto.setRuleId(1);
        dto.setBasePoint(20);
        dto.setMultiplier(new BigDecimal(1.2));
        pointRuleService.updatePointRule(dto);

        PointRule updatedPointRule=pointRuleMapper.selectById(1);
        assertNotNull(updatedPointRule);
        assertEquals(20, updatedPointRule.getBasePoint());
        assertEquals(new BigDecimal("1.2"),updatedPointRule.getMultiplier());
    }
    @Test
    public void testDeletePointRule(){
        pointRuleService.deletePointRuleByID(6);

        PointRule deletePointRule=pointRuleMapper.selectById(6);
        assertNull(deletePointRule);
    }

}
