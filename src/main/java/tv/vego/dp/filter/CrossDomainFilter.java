package tv.vego.dp.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域请求过滤
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/18 11:51
 */
public class CrossDomainFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
//        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod()))
//        {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        //在1800秒内，不需要再发送预检验请求，可以缓存该结果
        response.addHeader("Access-Control-Max-Age", "1800");
//        }

        filterChain.doFilter(request, response);
    }
}
