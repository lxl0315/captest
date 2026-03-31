package com.liang.test.Controller;

import com.liang.test.Service.EmployeePointsService;
import com.liang.test.VO.EmployeePointsQueryVO;
import com.liang.test.common.Result;
import com.liang.test.common.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/EmployeePoints")
public class EmployeePointsController {
    @Autowired
    private EmployeePointsService employeePointsService;

    @GetMapping
    public Result<EmployeePointsQueryVO> queryEmployeePoints(@PathVariable String employeeId){
        return ResultUtil.success(employeePointsService.queryEmployeePoints(employeeId));
    }
}
