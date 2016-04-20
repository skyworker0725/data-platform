package tv.vego.dp.mapper;

import tv.vego.dp.model.QueryWidgetDefine;
import tv.vego.dp.util.MyMapper;

import java.util.List;

/**
 * 查询控件定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/18 16:11
 */
public interface QueryWidgetDefineMapper extends MyMapper<QueryWidgetDefine>
{
    List<QueryWidgetDefine> selectReportUseWidgets(Long combId);
}
