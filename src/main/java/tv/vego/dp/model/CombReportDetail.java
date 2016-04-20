package tv.vego.dp.model;

import javax.persistence.Id;

/**
 * 组合报表详情
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 9:58
 */
public class CombReportDetail extends BaseSerializer
{
    @Id
    private Long combId;

    @Id
    private Long reportId;

    private Integer displayOrder;

    public Long getCombId()
    {
        return combId;
    }

    public void setCombId(Long combId)
    {
        this.combId = combId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Integer getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder)
    {
        this.displayOrder = displayOrder;
    }
}
