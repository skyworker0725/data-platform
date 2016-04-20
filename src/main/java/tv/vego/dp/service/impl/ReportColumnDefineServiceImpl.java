package tv.vego.dp.service.impl;

import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tv.vego.dp.model.ReportColumnDefine;
import tv.vego.dp.service.ReportColumnDefineService;

import java.util.List;

/**
 * 报表列定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 16:21
 */
@Service(value = "reportColumnDefineService")
public class ReportColumnDefineServiceImpl extends BaseService<ReportColumnDefine> implements ReportColumnDefineService
{
    @Override
    public List<ReportColumnDefine> selectByReportColumnDefine(ReportColumnDefine reportColumnDefine)
    {
        Example example = new Example(ReportColumnDefine.class);

        Example.Criteria criteria = example.createCriteria();
        if (null != reportColumnDefine.getColId())
        {
            criteria.andEqualTo("colId", reportColumnDefine.getColId());
        }
        if (null != reportColumnDefine.getReportId())
        {
            criteria.andEqualTo("reportId", reportColumnDefine.getReportId());
        }
        example.setOrderByClause("displayOrder");

        return selectByExample(example);
    }
}
