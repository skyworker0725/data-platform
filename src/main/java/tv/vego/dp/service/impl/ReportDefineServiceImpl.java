package tv.vego.dp.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import tv.vego.dp.model.ReportDefine;
import tv.vego.dp.service.ReportDefineService;

import java.util.List;

/**
 * 报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 11:25
 */
@Service("reportDefineService")
public class ReportDefineServiceImpl extends BaseService<ReportDefine> implements ReportDefineService
{
    @Override
    public List<ReportDefine> selectByReportDefine(ReportDefine reportDefine, Integer page, Integer rows)
    {
        Example example = new Example(ReportDefine.class);

        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(reportDefine.getReportName())) {
            criteria.andLike("reportName", "%" + reportDefine.getReportName() + "%");
        }
        if (null != reportDefine.getReportId()) {
            criteria.andEqualTo("reportId", reportDefine.getReportId());
        }

        //分页查询
        if (null != page && null != rows) {
            PageHelper.startPage(page, rows);
        }

        return selectByExample(example);
    }
}
