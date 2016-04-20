package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 报表目录
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 9:49
 */
public class ReportCatalog extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId;

    private String catalogName;

    private String parentCatalogId;

    private Integer displayOrder;

    public Long getCatalogId()
    {
        return catalogId;
    }

    public void setCatalogId(Long catalogId)
    {
        this.catalogId = catalogId;
    }

    public String getCatalogName()
    {
        return catalogName;
    }

    public void setCatalogName(String catalogName)
    {
        this.catalogName = catalogName;
    }

    public String getParentCatalogId()
    {
        return parentCatalogId;
    }

    public void setParentCatalogId(String parentCatalogId)
    {
        this.parentCatalogId = parentCatalogId;
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
