package com.xihu.conference.xihu.interceptor;

import com.xihu.conference.xihu.utils.JWTUtils;
import com.xihu.conference.xihu.utils.ThreadLocalUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证Token
        try {
            String realToken = stringRedisTemplate.opsForValue().get(token);
            if(realToken==null){
                throw new RuntimeException();
            }
            Map<String ,Object> claims = JWTUtils.parseToken(token);//验证失败会抛出异常
            ThreadLocalUtils.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //设置 401 状态码
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal里的数据
        ThreadLocalUtils.remove();
    }
}
