package tv.vego.dp.service;

import tv.vego.dp.model.QueryWidgetDefine;

import java.util.List;

/**
 * 查询控件获取
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/18 16:42
 */
public interface QueryWidgetDefineService extends IService<QueryWidgetDefine>
{
    List<QueryWidgetDefine> selectReportUseWidgets(Long combId);
}
