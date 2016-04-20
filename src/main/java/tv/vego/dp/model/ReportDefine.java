package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 10:41
 */
public class ReportDefine extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long dsId;

    private String reportName;

    private String reportSql;

    private Integer needPaging;

    private Integer pageSize;

    private Timestamp createTime;

    private Timestamp modifyTime;

    private String reportDesc;

    private String createPerson;

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getDsId()
    {
        return dsId;
    }

    public void setDsId(Long dsId)
    {
        this.dsId = dsId;
    }

    public String getReportName()
    {
        return reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getReportSql()
    {
        return reportSql;
    }

    public void setReportSql(String reportSql)
    {
        this.reportSql = reportSql;
    }

    public Integer getNeedPaging()
    {
        return needPaging;
    }

    public void setNeedPaging(Integer needPaging)
    {
        this.needPaging = needPaging;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Timestamp getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime)
    {
        this.modifyTime = modifyTime;
    }

    public String getReportDesc()
    {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc)
    {
        this.reportDesc = reportDesc;
    }

    public String getCreatePerson()
    {
        return createPerson;
    }

    public void setCreatePerson(String createPerson)
    {
        this.createPerson = createPerson;
    }
}
