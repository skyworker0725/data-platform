package tv.vego.dp.service;

import tv.vego.dp.model.ReportDefine;

import java.util.List;

/**
 * 报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 11:23
 */
public interface ReportDefineService extends IService<ReportDefine>
{
    List<ReportDefine> selectByReportDefine(ReportDefine reportDefine, Integer page, Integer rows);
}
