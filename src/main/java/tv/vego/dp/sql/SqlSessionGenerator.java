package tv.vego.dp.sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tv.vego.dp.model.DataSourceMeta;
import tv.vego.dp.util.coder.MD5Util;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * SqlSessionFactor单例持有
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/16 18:51
 */
@Component(value = "sqlSessionGenerator")
public class SqlSessionGenerator
{
    @Autowired
    private Properties pageHelperProperties;

    //SqlSessionFactory单例持有
    private Map<String, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();

    /**
     * 根据数据库元信息获取SqlSession
     *
     * @param dsMeta 数据库元信息
     *
     * @return SqlSession
     */
    public SqlSession getSqlSession(DataSourceMeta dsMeta) throws Exception
    {
        String ssfKey = getSSFKey(dsMeta);

        SqlSessionFactory sqlSessionFactory;
        synchronized (this)
        {
            sqlSessionFactory = sqlSessionFactoryMap.get(ssfKey);
            if (null == sqlSessionFactory)
            {
                sqlSessionFactory = buildSqlSessionFactory(dsMeta);

                sqlSessionFactoryMap.put(ssfKey, sqlSessionFactory);
            }
        }

        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private DataSource getDataSource(DataSourceMeta dsMeta) throws SQLException
    {
        DruidDataSource ds = new DruidDataSource();

        ds.setDriverClassName(dsMeta.getDriverClass());
        ds.setUrl(dsMeta.getDsUrl());
        ds.setUsername(dsMeta.getDsUsername());
        ds.setPassword(dsMeta.getDsPasswd());

        ds.setFilters("stat");

        ds.setMaxActive(20);
        ds.setInitialSize(1);
        ds.setMaxWait(60000);
        ds.setMinIdle(1);

        ds.setTimeBetweenEvictionRunsMillis(60000);
        ds.setMinEvictableIdleTimeMillis(300000);

        ds.setValidationQuery("SELECT 'x'");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);

        ds.init();

        return ds;
    }

    private SqlSessionFactory buildSqlSessionFactory(DataSourceMeta dsMeta) throws Exception
    {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        sessionFactoryBean.setDataSource(getDataSource(dsMeta));

        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(pageHelperProperties);

        sessionFactoryBean.setPlugins(new PageHelper[]{pageHelper});

        return sessionFactoryBean.getObject();
    }

    private String getSSFKey(DataSourceMeta dsMeta) throws Exception
    {
        return MD5Util.encode(dsMeta.getDriverClass() + dsMeta.getDsUsername() + dsMeta.getDsPasswd() + dsMeta.getDsUrl());
    }
}
