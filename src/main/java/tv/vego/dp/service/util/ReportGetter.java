package tv.vego.dp.service.util;

import com.github.pagehelper.PageHelper;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tv.vego.dp.model.DataSourceMeta;
import tv.vego.dp.model.PageInfo;
import tv.vego.dp.model.ReportColumnDefine;
import tv.vego.dp.model.ReportDefine;
import tv.vego.dp.service.DataSourceMetaService;
import tv.vego.dp.service.ReportColumnDefineService;
import tv.vego.dp.service.ReportDefineService;
import tv.vego.dp.sql.SqlMapper;
import tv.vego.dp.sql.SqlSessionGenerator;
import tv.vego.dp.vo.ReportDefineVO;

import java.util.LinkedHashMap;

/**
 * 报表数据获取
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/13 16:52
 */
@Component
public class ReportGetter implements InitializingBean
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportGetter.class);

    @Autowired
    private ReportDefineService reportDefineService;

    @Autowired
    private DataSourceMetaService dataSourceMetaService;

    @Autowired
    private ReportColumnDefineService reportColumnDefineService;

    @Autowired
    private SqlSessionGenerator sqlSessionGenerator;

    private static ReportDefineService _reportDefineService;
    private static DataSourceMetaService _dataSourceMetaService;
    private static ReportColumnDefineService _reportColumnDefineService;
    private static SqlSessionGenerator _sqlSessionGenerator;

    public static Object getData(String reportId, Object paramValue, Integer pageNum, Integer pageSize) throws Exception
    {
        LOGGER.debug("tv.vego.dp.service.util.ReportGetter.getData({})", reportId, paramValue);

        //获取报表定义信息
        ReportDefine reportDefine = _reportDefineService.selectByKey(reportId);
        if (null == reportDefine)
        {
            throw new IllegalArgumentException("报表ID为【" + reportId + "】的报表信息不存在。");
        }

        //获取数据源定义信息
        DataSourceMeta dataSourceMeta = _dataSourceMetaService.selectByKey(reportDefine.getDsId());
        if (null == dataSourceMeta)
        {
            throw new IllegalArgumentException("数据源ID为【" + reportDefine.getDsId() + "】的数据源信息不存在。");
        }

        //查询数据
        SqlSession sqlSession = _sqlSessionGenerator.getSqlSession(dataSourceMeta);
        SqlMapper sqlMapper = new SqlMapper(sqlSession);

        if (null != pageNum && null != pageSize)
        {
            PageHelper.startPage(pageNum, pageSize);

            return new PageInfo<>(sqlMapper.selectList(reportDefine.getReportSql(), paramValue, LinkedHashMap.class));
        }
        else
        {
            return sqlMapper.selectList(reportDefine.getReportSql(), paramValue, LinkedHashMap.class);
        }
    }

    public static ReportDefineVO getMeta(String reportId)
    {
        ReportDefineVO reportDefineVO = new ReportDefineVO();

        //获取报表信息
        ReportDefine reportDefine = _reportDefineService.selectByKey(reportId);
        if (null == reportDefine)
        {
            throw new IllegalArgumentException("报表ID为【" + reportId + "】的报表信息不存在。");
        }
        BeanUtils.copyProperties(reportDefine, reportDefineVO);

        //获取报表列信息
        ReportColumnDefine reportColumnDefineParam = new ReportColumnDefine();
        reportColumnDefineParam.setReportId(Long.parseLong(reportId));
        reportDefineVO.setColumns(_reportColumnDefineService.selectByReportColumnDefine(reportColumnDefineParam));

        return reportDefineVO;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        _reportDefineService = this.reportDefineService;
        _dataSourceMetaService = this.dataSourceMetaService;
        _reportColumnDefineService = this.reportColumnDefineService;
        _sqlSessionGenerator = this.sqlSessionGenerator;
    }
}
