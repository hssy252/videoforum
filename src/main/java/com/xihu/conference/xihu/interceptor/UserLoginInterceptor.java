package com.xihu.conference.xihu.interceptor;

import com.xihu.conference.xihu.exception.JwtFailException;
import com.xihu.conference.xihu.properties.JwtProperties;
import com.xihu.conference.xihu.utils.JwtUtil;
import com.xihu.conference.xihu.utils.ThreadLocalUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //令牌验证
        String token = request.getHeader(jwtProperties.getUserTokenName());
        //验证Token
        try {
            String realToken = stringRedisTemplate.opsForValue().get(token);
            if(realToken==null){
                throw new JwtFailException("登录令牌不存在");
            }
            Map<String ,Object> claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);//验证失败会抛出异常
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
