package com.liang.test.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.test.DTO.ExchangeDTO;
import com.liang.test.Entity.EmployeePoints;
import com.liang.test.Entity.ExchangeRecord;
import com.liang.test.Entity.Reward;
import com.liang.test.Mapper.EmployeePointsMapper;
import com.liang.test.Mapper.ExchangeRecordMapper;
import com.liang.test.Mapper.RewardMapper;
import com.liang.test.Service.ExchangeRecordService;
import com.liang.test.VO.ExchangeRecordQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRecordServiceImpl extends ServiceImpl<ExchangeRecordMapper, ExchangeRecord> implements ExchangeRecordService {

    @Autowired
    private RewardMapper rewardMapper;
    @Autowired
    private EmployeePointsMapper employeePointsMapper;
    @Override
    public void exchange(ExchangeDTO dto) {
        Reward reward=rewardMapper.selectById(dto.getRewardId());
        if (reward.getRewardQuantity()==null||reward.getRewardQuantity()<=0){
            throw new RuntimeException("奖励数量不足");
        }
        EmployeePoints employeePoints=employeePointsMapper.selectById(dto.getEmployeeId());
        if (employeePoints.getTotalPoints().compareTo(reward.getRequiredPoints())<0){
            throw new RuntimeException("积分不足");
        }
        BigDecimal newTotalPoints=employeePoints.getTotalPoints().subtract(reward.getRequiredPoints());
        employeePoints.setTotalPoints(newTotalPoints);
        employeePointsMapper.updateById(employeePoints);
        reward.setRewardQuantity(reward.getRewardQuantity()-1);
        rewardMapper.updateById(reward);
        ExchangeRecord record=new ExchangeRecord();
        record.setEmployeeId(dto.getEmployeeId());
        record.setRewardId(dto.getRewardId());
        record.setConsumePoints(reward.getRequiredPoints());
        record.setExchangeTime(LocalDateTime.now());
        this.save(record);
    }

    @Override
    public List<ExchangeRecordQueryVO> queryExchangeRecord(String employeeId) {
        LambdaQueryWrapper<ExchangeRecord> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ExchangeRecord::getEmployeeId,employeeId);
        wrapper.orderByDesc(ExchangeRecord::getExchangeTime);
        List<ExchangeRecord> recordList=this.list(wrapper);
        List<ExchangeRecordQueryVO> voList=new ArrayList<>();
        for (ExchangeRecord item:recordList){
            Reward reward=rewardMapper.selectById(item.getRewardId());
            ExchangeRecordQueryVO vo=new ExchangeRecordQueryVO();
            vo.setEmployeeId(item.getEmployeeId());
            vo.setRewardName(reward.getRewardName());
            vo.setConsumePoints(item.getConsumePoints());
            vo.setExchangeTime(item.getExchangeTime());
            voList.add(vo);
        }
        return voList;
    }
}
