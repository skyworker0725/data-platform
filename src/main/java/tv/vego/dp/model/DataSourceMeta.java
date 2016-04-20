package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 数据库连接元信息
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/16 18:59
 */
public class DataSourceMeta extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dsId;

    private String dsName;

    private String driverClass;

    private String dsUsername;

    private String dsPasswd;

    private String dsUrl;

    public Long getDsId()
    {
        return dsId;
    }

    public void setDsId(Long dsId)
    {
        this.dsId = dsId;
    }

    public String getDsName()
    {
        return dsName;
    }

    public void setDsName(String dsName)
    {
        this.dsName = dsName;
    }

    public String getDriverClass()
    {
        return driverClass;
    }

    public void setDriverClass(String driverClass)
    {
        this.driverClass = driverClass;
    }

    public String getDsUsername()
    {
        return dsUsername;
    }

    public void setDsUsername(String dsUsername)
    {
        this.dsUsername = dsUsername;
    }

    public String getDsPasswd()
    {
        return dsPasswd;
    }

    public void setDsPasswd(String dsPasswd)
    {
        this.dsPasswd = dsPasswd;
    }

    public String getDsUrl()
    {
        return dsUrl;
    }

    public void setDsUrl(String dsUrl)
    {
        this.dsUrl = dsUrl;
    }
}
