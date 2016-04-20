package tv.vego.dp.model;

import javax.persistence.Id;

/**
 * 系统字典对象
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/3 13:48
 */
public class SystemDict extends BaseSerializer
{
    @Id
    private String dictType;

    @Id
    private String dictCode;

    private String dictName;

    private Integer displayOrder;

    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
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
