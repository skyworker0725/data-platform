package tv.vego.dp.service;

import tv.vego.dp.model.ReportColumnDefine;

import java.util.List;

/**
 * 报表列定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 16:19
 */
public interface ReportColumnDefineService extends IService<ReportColumnDefine>
{
    List<ReportColumnDefine> selectByReportColumnDefine(ReportColumnDefine reportColumnDefine);
}
