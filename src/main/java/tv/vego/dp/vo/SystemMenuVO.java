package tv.vego.dp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 页面菜单VO
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/2/18 15:03
 */
public class SystemMenuVO implements Serializable
{
    private String id;

    private String text;

    private String url;

    private int isLeaf;

    private List<SystemMenuVO> children;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public int getIsLeaf()
    {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf)
    {
        this.isLeaf = isLeaf;
    }

    public List<SystemMenuVO> getChildren()
    {
        return children;
    }

    public void setChildren(List<SystemMenuVO> children)
    {
        this.children = children;
    }
}
