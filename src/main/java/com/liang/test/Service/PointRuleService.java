package com.liang.test.Service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.Entity.PointRule;

public interface PointRuleService  {

    void createPointRule(PointRuleDTO dto);
    Page<PointRule> queryPointRule(PointRuleDTO dto);

    void updatePointRule(PointRuleDTO dto);

    void deletePointRuleByID(Integer ruleId);
}
