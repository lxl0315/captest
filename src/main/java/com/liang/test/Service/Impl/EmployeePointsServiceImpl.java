package com.liang.test.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.test.DTO.EmployeePointsDTO;
import com.liang.test.DTO.LearningRecordDTO;
import com.liang.test.Entity.EmployeePoints;
import com.liang.test.Entity.LearningRecord;
import com.liang.test.Entity.PointRule;
import com.liang.test.Mapper.EmployeePointsMapper;
import com.liang.test.Mapper.LearningRecordMapper;
import com.liang.test.Mapper.PointRuleMapper;
import com.liang.test.Service.EmployeePointsService;
import com.liang.test.VO.EmployeePointsQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeePointsServiceImpl extends ServiceImpl<EmployeePointsMapper, EmployeePoints> implements EmployeePointsService {
    @Autowired
    private LearningRecordMapper learningRecordMapper;
    @Autowired
    private PointRuleMapper pointRuleMapper;
    @Override
    public EmployeePointsQueryVO queryEmployeePoints(String employeeId) {
        EmployeePoints employeePoints=this.getById(employeeId);
        if (employeePoints==null){
            throw new RuntimeException("该员工积分信息不存在");
        }
        Long learnRecordCount=learningRecordMapper.selectCount(new LambdaQueryWrapper<LearningRecord>()
                .eq(LearningRecord::getEmployeeId,employeeId));
        List<LearningRecord> recordList=learningRecordMapper.selectList(new LambdaQueryWrapper<LearningRecord>()
                .eq(LearningRecord::getEmployeeId,employeeId));
        Map<Integer,BigDecimal> pointMap=new HashMap<>();
        for (LearningRecord record:recordList){
            Integer pointRuleId=record.getPointRuleId();
            BigDecimal Points=pointMap.getOrDefault(pointRuleId,BigDecimal.ZERO);
            pointMap.put(pointRuleId,Points.add(record.getAwardedPoints()));
        }
        List<EmployeePointsDTO> learnPointList=new ArrayList<>();
        for (Map.Entry<Integer,BigDecimal> entry:pointMap.entrySet()){
            Integer pointRuleId=entry.getKey();
            BigDecimal totalPoints=entry.getValue();
            PointRule pointRule=pointRuleMapper.selectById(pointRuleId);
            EmployeePointsDTO dto=new EmployeePointsDTO();
            dto.setPointRuleId(pointRuleId);
            dto.setTypeName(pointRule.getTypeName());
            dto.setTotalPoints(totalPoints);
            learnPointList.add(dto);
        }
        EmployeePointsQueryVO vo=new EmployeePointsQueryVO();
        vo.setEmployeeId(employeeId);
        vo.setTotalPoints(employeePoints.getTotalPoints());
        vo.setLearnRecordCount(learnRecordCount.intValue());
        vo.setTypePoints(learnPointList);
        return vo;
    }
}



