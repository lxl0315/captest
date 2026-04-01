package com.liang.test.Service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.test.Entity.Reward;
import com.liang.test.mapper.RewardMapper;
import com.liang.test.Service.RewardService;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements RewardService {
    @Override
    public void createReward(Reward reward) {
        this.save(reward);
    }

    @Override
    public Page<Reward> queryReward(Reward reward) {
        return this.page(new Page<>(reward.getPageNum(), reward.getPageSize()));
    }

    @Override
    public void updateReward(Reward reward) {
        if (reward.getRewardId()==null){
            throw new RuntimeException("该奖励不存在");
        }
        this.updateById(reward);
    }

    @Override
    public void deleteReward(Integer rewardId) {
        this.removeById(rewardId);
    }
}
