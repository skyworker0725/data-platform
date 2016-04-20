package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 组合报表定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 9:40
 */
public class CombReportDefine extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long combId;

    private String combName;

    private Long catalogId;

    private String layoutInfo;

    private String combDesc;

    public Long getCombId()
    {
        return combId;
    }

    public void setCombId(Long combId)
    {
        this.combId = combId;
    }

    public String getCombName()
    {
        return combName;
    }

    public void setCombName(String combName)
    {
        this.combName = combName;
    }

    public Long getCatalogId()
    {
        return catalogId;
    }

    public void setCatalogId(Long catalogId)
    {
        this.catalogId = catalogId;
    }

    public String getLayoutInfo()
    {
        return layoutInfo;
    }

    public void setLayoutInfo(String layoutInfo)
    {
        this.layoutInfo = layoutInfo;
    }

    public String getCombDesc()
    {
        return combDesc;
    }

    public void setCombDesc(String combDesc)
    {
        this.combDesc = combDesc;
    }
}
