package com.liang.test.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.test.Entity.EmployeePoints;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeePointsMapper extends BaseMapper<EmployeePoints> {
}
