package com.liang.test.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.Entity.PointRule;
import com.liang.test.Service.PointRuleService;
import com.liang.test.common.Result;
import com.liang.test.common.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Pointrule")
public class PointRuleController {
    @Autowired
    private PointRuleService pointRuleService;

    @PostMapping
    public Result<String> createPointRule(@RequestBody PointRuleDTO dto){
        pointRuleService.createPointRule(dto);
        return ResultUtil.success("新增成功");
    }

    @GetMapping
    public Result<Page<PointRule>> queryPointRule(PointRuleDTO dto){
        return ResultUtil.success(pointRuleService.queryPointRule(dto));
    }

    @PutMapping
    public Result<String> updatePointRule(@RequestBody PointRuleDTO dto){
        pointRuleService.updatePointRule(dto);
        return ResultUtil.success("修改成功");
    }

    @DeleteMapping
    public Result<String> deletePointRuleByID(@PathVariable Integer ruleId){
        pointRuleService.deletePointRuleByID(ruleId);
        return ResultUtil.success("删除成功");
    }
}
