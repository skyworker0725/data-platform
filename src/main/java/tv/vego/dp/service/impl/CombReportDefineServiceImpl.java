package tv.vego.dp.service.impl;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tv.vego.dp.model.CombReportDefine;
import tv.vego.dp.service.CombReportDefineService;

import java.util.List;

/**
 * 组合报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 11:07
 */
@Service("combReportDefineService")
public class CombReportDefineServiceImpl extends BaseService<CombReportDefine> implements CombReportDefineService
{
    @Override
    public List<CombReportDefine> getCatalogCombReportByCatalogId(long catalogId)
    {
        Condition cond = new Condition(CombReportDefine.class);
        Condition.Criteria criteria = cond.createCriteria();
        criteria.andEqualTo("catalogId", catalogId);
        cond.setOrderByClause("combName");

        return selectByExample(cond);
    }
}
