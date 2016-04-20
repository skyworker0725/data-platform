package tv.vego.dp.service;

import tv.vego.dp.model.DataSourceMeta;

import java.util.List;

/**
 * 数据源定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 14:14
 */
public interface DataSourceMetaService extends IService<DataSourceMeta>
{
    List<DataSourceMeta> selectByDataSourceMeta(DataSourceMeta dataSourceMeta, Integer page, Integer rows);
}
