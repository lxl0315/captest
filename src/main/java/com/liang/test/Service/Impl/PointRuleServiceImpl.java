package com.liang.test.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.Entity.LearningRecord;
import com.liang.test.Entity.PointRule;
import com.liang.test.mapper.LearningRecordMapper;
import com.liang.test.mapper.PointRuleMapper;
import com.liang.test.Service.PointRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class PointRuleServiceImpl extends ServiceImpl<PointRuleMapper, PointRule> implements PointRuleService {

    @Autowired
    private LearningRecordMapper learningRecordMapper;

    @Override
    public void createPointRule(PointRuleDTO dto) {
        boolean exists=this.exists(new LambdaQueryWrapper<PointRule>()
                .eq(PointRule::getTypeName,dto.getTypeName()));
        if (exists) {
            throw new RuntimeException("学习类型名称已存在");
        }
        PointRule pointRule=new PointRule();
        pointRule.setTypeName(dto.getTypeName());
        pointRule.setBasePoint(dto.getBasePoint());
        pointRule.setMultiplier(dto.getMultiplier());
        pointRule.setDescription(dto.getDescription());
        pointRule.setIsActive(dto.getIsActive());
        this.save(pointRule);

    }

    @Override
    public Page<PointRule> queryPointRule(PointRuleDTO dto) {
        LambdaQueryWrapper<PointRule> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.hasText(dto.getTypeName())){
            wrapper.eq(PointRule::getTypeName,dto.getTypeName());
        }
        if (StringUtils.hasText(dto.getDescription())){
            wrapper.like(PointRule::getDescription,dto.getDescription());
        }
        Page<PointRule> page=new Page<>(dto.getPageNum(),dto.getPageSize());

        return this.page(page,wrapper);
    }

    @Override
    public void updatePointRule(PointRuleDTO dto) {
        if (dto.getRuleId()==null) {
            throw new RuntimeException("规则ID不能为空");
        }
        PointRule pointRule=this.getById(dto.getRuleId());
        if (pointRule==null){
            throw new RuntimeException("该规则不存在或被删除");
        }
        pointRule.setBasePoint(dto.getBasePoint());
        pointRule.setMultiplier(dto.getMultiplier());
        pointRule.setDescription(dto.getDescription());
        pointRule.setIsActive(dto.getIsActive());
        this.updateById(pointRule);

    }

    @Override
    public void deletePointRuleByID(Integer ruleId) {
        if (ruleId==null) {
            throw new RuntimeException("规则ID不能为空");
        }
        PointRule pointRule=this.getById(ruleId);
        if (pointRule==null){
            throw new RuntimeException("该规则不存在或被删除");
        }
        Long record=learningRecordMapper.selectCount(new LambdaQueryWrapper<LearningRecord>()
                .eq(LearningRecord::getPointRuleId,ruleId));
        if (record!=null&&record>0){
            throw new RuntimeException("该规则已有关联的学习记录不可删除");
        }
        this.removeById(ruleId);
    }
}
