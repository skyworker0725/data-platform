package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 查询控件定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/18 16:06
 */
public class QueryWidgetDefine extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long widgetId;

    private String widgetName;

    private String elementType;

    private String elementId;

    private String defaultValue;

    private String widgetDesc;

    private String extAttrs;

    public Long getWidgetId()
    {
        return widgetId;
    }

    public void setWidgetId(Long widgetId)
    {
        this.widgetId = widgetId;
    }

    public String getWidgetName()
    {
        return widgetName;
    }

    public void setWidgetName(String widgetName)
    {
        this.widgetName = widgetName;
    }

    public String getElementType()
    {
        return elementType;
    }

    public void setElementType(String elementType)
    {
        this.elementType = elementType;
    }

    public String getElementId()
    {
        return elementId;
    }

    public void setElementId(String elementId)
    {
        this.elementId = elementId;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public String getWidgetDesc()
    {
        return widgetDesc;
    }

    public void setWidgetDesc(String widgetDesc)
    {
        this.widgetDesc = widgetDesc;
    }

    public String getExtAttrs()
    {
        return extAttrs;
    }

    public void setExtAttrs(String extAttrs)
    {
        this.extAttrs = extAttrs;
    }
}
