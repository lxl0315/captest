package com.liang.test.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.test.DTO.LearningRecordDTO;
import com.liang.test.DTO.LearningRecordQueryDTO;
import com.liang.test.Entity.LearningRecord;
import com.liang.test.Service.LearningRecordService;
import com.liang.test.VO.LearningRecordQueryVO;
import com.liang.test.common.Result;
import com.liang.test.common.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/LearningRecord")
public class LearningRecordController {
    @Autowired
    private LearningRecordService learningRecordService;

    @PostMapping
    public Result<String> createLearningRecord(@RequestBody LearningRecordDTO dto){
       learningRecordService.createLearningRecord(dto);
       return ResultUtil.success("创建成功");
    }

    @GetMapping
    public Result<Page<LearningRecordQueryVO>> queryLearnRecord(LearningRecordQueryDTO dto){
        return ResultUtil.success(learningRecordService.queryLearnRecord(dto));
    }

    @DeleteMapping
    public Result<String> deleteLearnRecordById(@PathVariable Integer recordId){
        learningRecordService.deleteLearnRecordById(recordId);
        return ResultUtil.success("删除成功");
    }

}
