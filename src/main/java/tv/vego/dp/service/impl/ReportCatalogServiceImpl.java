package tv.vego.dp.service.impl;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tv.vego.dp.model.ReportCatalog;
import tv.vego.dp.service.ReportCatalogService;

import java.util.List;

/**
 * 报表目录
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/26 15:10
 */
@Service(value = "reportCatalogService")
public class ReportCatalogServiceImpl extends BaseService<ReportCatalog> implements ReportCatalogService
{
    @Override
    public List<ReportCatalog> getReportCatalogByParentId(long parentCatalogId)
    {
        Condition cond = new Condition(ReportCatalog.class);
        Condition.Criteria criteria = cond.createCriteria();
        criteria.andEqualTo("parentCatalogId", parentCatalogId);
        cond.setOrderByClause("displayOrder");

        return selectByExample(cond);
    }
}
