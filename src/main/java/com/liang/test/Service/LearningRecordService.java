package com.liang.test.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.LearningRecordDTO;
import com.liang.test.DTO.LearningRecordQueryDTO;
import com.liang.test.VO.LearningRecordQueryVO;

public interface LearningRecordService {
    void createLearningRecord(LearningRecordDTO dto);

    Page<LearningRecordQueryVO> queryLearnRecord(LearningRecordQueryDTO dto);

    void deleteLearnRecordById(Integer recordId);
}
