package tv.vego.dp.service;

import tv.vego.dp.model.ReportCatalog;

import java.util.List;

/**
 * 报表目录
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/26 15:08
 */
public interface ReportCatalogService extends IService<ReportCatalog>
{
    /**
     * 根据父节点ID查询子节点目录信息
     *
     * @param parentCatalogId 父节点ID
     *
     * @return 子节点信息
     */
    List<ReportCatalog> getReportCatalogByParentId(long parentCatalogId);
}
