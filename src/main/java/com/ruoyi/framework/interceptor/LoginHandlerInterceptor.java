package com.ruoyi.framework.interceptor;

import com.ruoyi.common.utils.TenantUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 * 
 * @author ruoyi
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截逻辑
        TenantUtil.initTenantNo(request.getParameter("authCode"));
        return true;
    }
}
