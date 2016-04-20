package tv.vego.dp.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.Serializable;

/**
 * 基础序列化对象，覆写toString方法为返回JSON字符串
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 10:44
 */
public abstract class BaseSerializer implements Serializable
{
    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
