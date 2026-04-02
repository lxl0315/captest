package com.liang.test.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.RewardQueryDTO;
import com.liang.test.Entity.Reward;

public interface RewardService {
    void createReward(Reward reward);

    Page<Reward> queryReward(RewardQueryDTO dto);

    void updateReward(Reward reward);

    void deleteReward(Integer rewardId);
}
