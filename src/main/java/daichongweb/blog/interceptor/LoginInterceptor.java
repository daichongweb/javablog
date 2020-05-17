package daichongweb.blog.interceptor;


import daichongweb.blog.exception.BusinessException;
import daichongweb.blog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("在操作之后");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("在操作之前");
        String token = request.getHeader("token");
        if (!StringUtils.isNotBlank(token)) {
            throw new BusinessException(400, "TOKEN不存在");
        }
        userService.isLogin(token, "login");
        return true;
    }
}
