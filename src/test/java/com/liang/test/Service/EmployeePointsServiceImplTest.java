package com.liang.test.Service;

import com.liang.test.VO.EmployeePointsQueryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class EmployeePointsServiceImplTest {
    @Autowired
    private EmployeePointsService employeePointsService;
    @Test
    public void testQueryEmployeePoints(){
        EmployeePointsQueryVO vo=employeePointsService.queryEmployeePoints("E1002");
        assertNotNull(vo);
        assertEquals("E1002",vo.getEmployeeId());
        assertNotNull(vo.getTotalPoints());
        assertNotNull(vo.getTypePoints());
    }
}
