package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 报表列定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 16:00
 */
public class ReportColumnDefine extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colId;

    private Long reportId;

    private String fieldLabel;

    private String fieldName;

    private String linkAddr;

    private Integer displayOrder;

    private String fieldType;

    private String formatPattern;

    private Integer fieldWidth;

    public Long getColId()
    {
        return colId;
    }

    public void setColId(Long colId)
    {
        this.colId = colId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public String getFieldLabel()
    {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel)
    {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String getLinkAddr()
    {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr)
    {
        this.linkAddr = linkAddr;
    }

    public Integer getDisplayOrder()
    {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder)
    {
        this.displayOrder = displayOrder;
    }

    public String getFieldType()
    {
        return fieldType;
    }

    public void setFieldType(String fieldType)
    {
        this.fieldType = fieldType;
    }

    public String getFormatPattern()
    {
        return formatPattern;
    }

    public void setFormatPattern(String formatPattern)
    {
        this.formatPattern = formatPattern;
    }

    public Integer getFieldWidth()
    {
        return fieldWidth;
    }

    public void setFieldWidth(Integer fieldWidth)
    {
        this.fieldWidth = fieldWidth;
    }
}
