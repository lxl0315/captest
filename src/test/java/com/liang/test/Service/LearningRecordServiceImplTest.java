package com.liang.test.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liang.test.DTO.LearningRecordDTO;
import com.liang.test.DTO.LearningRecordQueryDTO;
import com.liang.test.Entity.EmployeePoints;
import com.liang.test.Entity.LearningRecord;
import com.liang.test.mapper.EmployeePointsMapper;
import com.liang.test.mapper.LearningRecordMapper;
import com.liang.test.mapper.PointRuleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LearningRecordServiceImplTest {
    @Autowired
    private LearningRecordService learningRecordService;
    @Autowired
    private LearningRecordMapper learningRecordMapper;
    @Autowired
    private PointRuleMapper pointRuleMapper;
    @Autowired
    private EmployeePointsMapper employeePointsMapper;
    @Test
    public void testCreateLearningRecord(){
        LearningRecordDTO dto=new LearningRecordDTO();
        dto.setEmployeeId("E0005");
        dto.setEmployeeName("小明");
        dto.setPointRuleId(3);
        dto.setActivityTitle("英语培训");
        dto.setLearningHours(new BigDecimal("2.0"));
        dto.setCompletedDate(LocalDate.now());
        learningRecordService.createLearningRecord(dto);

        LearningRecord createRecord=learningRecordMapper.selectById(dto.getEmployeeId());
        assertNotNull(createRecord);
    }
    @Test
    public void testQueryLearnRecord(){
        LearningRecordQueryDTO dto=new LearningRecordQueryDTO();
        dto.setEmployeeId("E1002");
        dto.setPointRuleId(1);
        var queryLearnRecordResult=learningRecordService.queryLearnRecord(dto);

        assertNotNull(queryLearnRecordResult);
        assertNotNull(queryLearnRecordResult.getRecords());
    }
    @Test
    public void testDeleteLearnRecord(){
        LearningRecordDTO dto=new LearningRecordDTO();
        dto.setEmployeeId("E0006");
        dto.setEmployeeName("小李");
        dto.setPointRuleId(3);
        dto.setActivityTitle("英语培训");
        dto.setLearningHours(new BigDecimal("2.0"));
        dto.setCompletedDate(LocalDate.of(2025,12,12));
        learningRecordService.createLearningRecord(dto);

        LearningRecord deleteRecord=learningRecordMapper.selectOne(new LambdaQueryWrapper<LearningRecord>()
                .eq(LearningRecord::getEmployeeId,"E0006")
                .eq(LearningRecord::getPointRuleId,3)
                .eq(LearningRecord::getActivityTitle,"英语培训")
                .eq(LearningRecord::getLearningHours,new BigDecimal("2.0"))
                .eq(LearningRecord::getCompletedDate,LocalDate.of(2025,12,12)));

        assertNotNull(deleteRecord);
        Integer recordId=deleteRecord.getRecordId();
        assertNotNull(recordId);

        EmployeePoints before=employeePointsMapper.selectById("E0006");
        assertNotNull(before);
        BigDecimal beforePoints=before.getTotalPoints();
        assertNotNull(beforePoints);
        BigDecimal recordPoints=deleteRecord.getAwardedPoints();

        //根据id删除
        learningRecordService.deleteLearnRecordById(recordId);

        LearningRecord Record=learningRecordMapper.selectById(recordId);
        assertNull(Record);
        //删除后的积分减少
        EmployeePoints after=employeePointsMapper.selectById("E0006");
        assertNotNull(after);
        BigDecimal points=beforePoints.subtract(recordPoints);
        assertEquals(0,after.getTotalPoints().compareTo(points));

    }

}
