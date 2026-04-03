package com.liang.test.Service;

import com.liang.test.DTO.RewardQueryDTO;
import com.liang.test.Entity.Reward;
import com.liang.test.mapper.RewardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RewardServiceImplTest {
    @Autowired
    private RewardService rewardService;
    @Autowired
    private RewardMapper rewardMapper;
    @Test
    public void testCreateReward(){
        Reward reward=new Reward();
        reward.setRewardName("演唱会票");
        reward.setRequiredPoints(new BigDecimal("500"));
        reward.setRewardQuantity(5);
        reward.setDescription("国内任意演唱会票");
        rewardService.createReward(reward);

        Reward createReward=rewardMapper.selectById(reward.getRewardId());
        assertNotNull(createReward);
        assertNotNull(createReward.getRewardId());
    }
    @Test
    public void testQueryReward(){
        RewardQueryDTO dto=new RewardQueryDTO();
        dto.setRewardId(1);
        var result=rewardService.queryReward(dto);

        assertNotNull(result);
        assertNotNull(result.getRecords());
    }
    @Test
    public void testUpdateReward(){
        Reward reward=new Reward();
        reward.setRewardId(3);
        reward.setRewardName("电影票");
        reward.setDescription("任意100元以下的电影");
        rewardService.updateReward(reward);

        Reward updateReward=rewardMapper.selectById(3);
        assertNotNull(updateReward);
        assertEquals("电影票",updateReward.getRewardName());
        assertEquals("任意100元以下的电影",updateReward.getDescription());
    }
    @Test
    public void testDeleteReward(){
        Reward reward = new Reward();
        reward.setRewardName("测试删除奖励");
        reward.setRequiredPoints(new BigDecimal("20"));
        reward.setRewardQuantity(5);
        reward.setDescription("要删除的奖励");
        rewardService.createReward(reward);
        assertNotNull(reward.getRewardId());
        rewardService.deleteReward(reward.getRewardId());

        Reward deleteReward=rewardMapper.selectById(reward.getRewardId());
        assertNull(deleteReward);
    }

}
