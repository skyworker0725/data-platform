package tv.vego.dp.controller;

import tv.vego.dp.util.ParamConstant;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共控制器
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 14:40
 */
public abstract class BaseController
{
    @SuppressWarnings("unchecked")
    protected Map<String, Object> getQueryParams(HttpServletRequest request)
    {
        Map<String, Object> requestParams = request.getParameterMap();
        Map<String, Object> params = new HashMap<>(requestParams.size());
        for (String paramKey : requestParams.keySet())
        {
            params.put(paramKey, request.getParameter(paramKey));
            params.put(paramKey + ParamConstant.PARAM_ARRAY_SUFFIX, requestParams.get(paramKey));
        }

        return params;
    }
}
