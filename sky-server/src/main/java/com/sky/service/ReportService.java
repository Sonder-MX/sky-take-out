package com.sky.service;

import java.time.LocalDate;

import com.sky.vo.TurnoverReportVO;

public interface ReportService {

    /**
     * 营业额统计
     * 
     * @param begin
     * @param end
     * @return
     */
    TurnoverReportVO getTurnoverReport(LocalDate begin, LocalDate end);

}
