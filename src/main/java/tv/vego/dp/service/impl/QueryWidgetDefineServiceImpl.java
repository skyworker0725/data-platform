package tv.vego.dp.service.impl;

import org.springframework.stereotype.Service;
import tv.vego.dp.mapper.QueryWidgetDefineMapper;
import tv.vego.dp.model.QueryWidgetDefine;
import tv.vego.dp.service.QueryWidgetDefineService;

import java.util.List;

/**
 * 查询控件定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/18 16:43
 */
@Service(value = "queryWidgetDefineService")
public class QueryWidgetDefineServiceImpl extends BaseService<QueryWidgetDefine> implements QueryWidgetDefineService
{
    @Override
    public List<QueryWidgetDefine> selectReportUseWidgets(Long combId)
    {
        return ((QueryWidgetDefineMapper) getMapper()).selectReportUseWidgets(combId);
    }
}
