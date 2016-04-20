package tv.vego.dp.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import tv.vego.dp.model.DataSourceMeta;
import tv.vego.dp.service.DataSourceMetaService;

import java.util.List;

/**
 * 数据源定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 14:16
 */
@Service(value = "dataSourceMetaService")
public class DataSourceMetaServiceImpl  extends BaseService<DataSourceMeta> implements DataSourceMetaService
{
    @Override
    public List<DataSourceMeta> selectByDataSourceMeta(DataSourceMeta dataSourceMeta, Integer page, Integer rows)
    {
        Example example = new Example(DataSourceMeta.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(dataSourceMeta.getDsName())) {
            criteria.andLike("dsName", "%" + dataSourceMeta.getDsName() + "%");
        }
        if (null != dataSourceMeta.getDsId()) {
            criteria.andEqualTo("dsId", dataSourceMeta.getDsId());
        }

        //分页查询
        if (null != page && null != rows) {
            PageHelper.startPage(page, rows);
        }

        return selectByExample(example);
    }
}
