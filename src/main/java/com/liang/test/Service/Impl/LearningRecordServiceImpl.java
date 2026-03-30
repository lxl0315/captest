package com.liang.test.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.test.DTO.LearningRecordDTO;
import com.liang.test.DTO.LearningRecordQueryDTO;
import com.liang.test.Entity.EmployeePoints;
import com.liang.test.Entity.LearningRecord;
import com.liang.test.Entity.PointRule;
import com.liang.test.Mapper.EmployeePointsMapper;
import com.liang.test.Mapper.LearningRecordMapper;
import com.liang.test.Mapper.PointRuleMapper;
import com.liang.test.Service.LearningRecordService;
import com.liang.test.VO.LearningRecordQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class LearningRecordServiceImpl extends ServiceImpl<LearningRecordMapper, LearningRecord> implements LearningRecordService {
    @Autowired
    private PointRuleMapper pointRuleMapper;
    private EmployeePointsMapper employeePointsMapper;
    @Override
    public void createLearningRecord(LearningRecordDTO dto) {
        PointRule pointRule=pointRuleMapper.selectById(dto.getPointRuleId());
        if (pointRule==null||Boolean.TRUE.equals(pointRule.getDeleted())){
            throw new RuntimeException("不存在或被删除");
        }
        if (pointRule.getIsActive()!=1){
            throw new RuntimeException("学习类型停用");
        }
        boolean exists=this.exists(new LambdaQueryWrapper<LearningRecord>()
                .eq(LearningRecord::getEmployeeId,dto.getEmployeeId())
                .eq(LearningRecord::getPointRuleId,dto.getPointRuleId())
                .eq(LearningRecord::getCompletedDate,dto.getCompletedDate()));
        if (exists){
            throw new RuntimeException("同一员工同一天对同一类型的记录不可重复提交");
        }
        BigDecimal awardedPoints=new BigDecimal(pointRule.getBasePoint()).multiply(pointRule.getMultiplier())
                .multiply(dto.getLearningHours());

        LearningRecord learningRecord=new LearningRecord();
        learningRecord.setEmployeeId(dto.getEmployeeId());
        learningRecord.setEmployeeName(dto.getEmployeeName());
        learningRecord.setPointRuleId(dto.getPointRuleId());
        learningRecord.setActivityTitle(dto.getActivityTitle());
        learningRecord.setLearningHours(dto.getLearningHours());
        learningRecord.setCompletedDate(dto.getCompletedDate());
        learningRecord.setAwardedPoints(awardedPoints);
        this.save(learningRecord);
    }

    @Override
    public Page<LearningRecordQueryVO> queryLearnRecord(LearningRecordQueryDTO dto) {
        LambdaQueryWrapper<LearningRecord> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.hasText(dto.getEmployeeId())){
            wrapper.eq(LearningRecord::getEmployeeId,dto.getEmployeeId());
        }
        if (dto.getPointRuleId()!= null){
            wrapper.eq(LearningRecord::getPointRuleId,dto.getPointRuleId());
        }
        if (dto.getStartDate()!= null) {
            wrapper.ge(LearningRecord::getCompletedDate, dto.getStartDate());
        }
        if (dto.getEndDate()!= null) {
            wrapper.le(LearningRecord::getCompletedDate, dto.getEndDate());
        }
        Page<LearningRecord> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        Page<LearningRecord> recordPage = this.page(page, wrapper);
        List<LearningRecordQueryVO> list = new ArrayList<>();
        for(LearningRecord record : recordPage.getRecords()){
            LearningRecordQueryVO vo = new LearningRecordQueryVO();
            vo.setEmployeeId(record.getEmployeeId());
            vo.setEmployeeName(record.getEmployeeName());
            vo.setActivityTitle(record.getActivityTitle());
            vo.setLearningHours(record.getLearningHours());
            vo.setAwardedPoints(record.getAwardedPoints());
            vo.setCompletedDate(record.getCompletedDate());
            PointRule pointRule = pointRuleMapper.selectById(record.getPointRuleId());
            vo.setTypeName(pointRule.getTypeName());
            list.add(vo);
        }
        Page<LearningRecordQueryVO> QueryVO = new Page<>();
        QueryVO.setCurrent(recordPage.getCurrent());
        QueryVO.setSize(recordPage.getSize());
        QueryVO.setTotal(recordPage.getTotal());
        QueryVO.setRecords(list);
        return QueryVO;
    }

    @Override
    public void deleteLearnRecordById(Integer recordId) {
        LearningRecord record=this.getById(recordId);
        if (record==null) {
            throw new RuntimeException("学习记录不存在");
        }
        EmployeePoints employeePoints=employeePointsMapper.selectById(record.getEmployeeId());
        if (employeePoints==null){
            throw new RuntimeException("该员工积分信息不存在");
        }
        BigDecimal newTotalPoints=employeePoints.getTotalPoints().subtract(record.getAwardedPoints());
        employeePoints.setTotalPoints(newTotalPoints);
        employeePointsMapper.updateById(employeePoints);
        this.removeById(recordId);

    }
}
