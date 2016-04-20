package tv.vego.dp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/26 14:09
 */
public class JSONTest
{
    @Test
    public void testJSONPase()
    {
        String jsonStr = "{color:\"red\"}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        jsonObject.put("defaultValue", 0);
        jsonObject.put("elementId", "stat_day");

        System.out.println(jsonObject.toJSONString());
    }
}
