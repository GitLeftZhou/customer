package com.zhou.customer.mapper;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    Report findReportById(Integer id);

    List<Report> findReport(Param param);

    public List<Report> listReport();

    public int insert(Report Report);

    public int generateReport(Param param);

    public int delete(int id);

}
