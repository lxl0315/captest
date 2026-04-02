package com.liang.test.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.PointRuleDTO;
import com.liang.test.DTO.RewardQueryDTO;
import com.liang.test.Entity.PointRule;
import com.liang.test.Entity.Reward;
import com.liang.test.Service.RewardService;
import com.liang.test.common.Result;
import com.liang.test.common.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;
    @PostMapping
    public Result<String> createReward(@RequestBody Reward reward){
        rewardService.createReward(reward);
        return ResultUtil.success("新增成功");
    }

    @GetMapping
    public Result<Page<Reward>> queryReward(RewardQueryDTO dto){
        return ResultUtil.success(rewardService.queryReward(dto));
    }
    @PutMapping
    public Result<String> updateReward(@RequestBody Reward reward){
        rewardService.updateReward(reward);
        return ResultUtil.success("新增成功");
    }
    @DeleteMapping
    public Result<String> deleteReward(@PathVariable Integer rewardId) {
        rewardService.deleteReward(rewardId);
        return ResultUtil.success("删除成功");
    }
}
