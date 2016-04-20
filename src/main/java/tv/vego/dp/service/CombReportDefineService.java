package tv.vego.dp.service;

import tv.vego.dp.model.CombReportDefine;

import java.util.List;

/**
 * 组合报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 11:05
 */
public interface CombReportDefineService extends IService<CombReportDefine>
{
    /**
     * 根据目录ID查询目录下报表信息
     *
     * @param catalogId 目录ID
     *
     * @return 目录下报表信息
     */
    List<CombReportDefine> getCatalogCombReportByCatalogId(long catalogId);
}
