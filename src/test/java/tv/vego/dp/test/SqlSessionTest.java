package tv.vego.dp.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import tv.vego.dp.sql.SqlMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试编程获取SqlSession
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/16 17:36
 */
public class SqlSessionTest
{
    @Test
    public void execSQLTest() throws Exception
    {
        String sql = "<script>select * from t_play_result_vr<where><if test=\"play_region != null and play_region != ''\">play_region = #{play_region}</if><if test=\"vedio_id != null and vedio_id != ''\">and vedio_id = #{vedio_id}</if></where></script>";

        Map<String, Object> params = new HashMap<>();
        params.put("play_region", "中国");

        SqlSession sqlSession = getSqlSession();
        SqlMapper sqlMapper = new SqlMapper(sqlSession);
        System.out.println(sqlMapper.selectList(sql, params, Map.class));
//        System.out.println(sqlMapper.selectList("<script>" +
//                "select * from country " +
//                "   <where>" +
//                "       <if test=\"id != null\">" +
//                "           id &lt; #{id}" +
//                "       </if>" +
//                "   </where>" +
//                "</script>", null, Country.class));
    }

    private DataSource getDataSource() throws SQLException
    {
        DruidDataSource ds = new DruidDataSource();

        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.1.201:3306/stat?useUnicode=true&characterEncoding=utf-8");
        ds.setUsername("root");
        ds.setPassword("gochinatv123");

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

    private SqlSessionFactory getSqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

        sessionFactoryBean.setDataSource(getDataSource());

        //TODO 分页插件设置

        return sessionFactoryBean.getObject();
    }

    private SqlSession getSqlSession() throws Exception
    {
        //TODO 每次调用新建
        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(getSqlSessionFactory());

        return sessionTemplate;
    }
}
