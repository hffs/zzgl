package com.ruoyi.framework.security.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.enums.UserType;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.TenantUtil;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.domain.SysTenantRole;
import com.ruoyi.project.system.service.ISysTenantRoleService;
import com.ruoyi.project.system.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private ISysTenantService sysTenantService;

    @Resource
    private ISysTenantRoleService sysTenantRoleService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        // 验证验证码
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        // 检验验证码
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaException();
        }
        // 验证租户
        SysTenant tenant = sysTenantService.queryByTenantNo(TenantUtil.getTenant().getTenantNo());
        if (tenant == null){
            throw new CustomException("租户不存在");
        }
        if (UserStatus.DISABLE.getCode().equals(String.valueOf(tenant.getStatus()))){
            throw new CustomException("租户已被禁用");
        }
        // 验证租户角色
        if (!UserType.ADMIN.getCode().equals(tenant.getTenantType())){
            SysTenantRole role = sysTenantRoleService.getById(tenant.getRoleId());
            if (role == null){
                throw new CustomException("租户角色不存在");
            }
            if (UserStatus.DISABLE.getCode().equals(String.valueOf(role.getStatus()))){
                throw new CustomException("租户角色已被禁用");
            }
            // 设置租户子类型
            tenant.setChildrenType(role.getChildrenType());
            tenant.setChildrenData(role.getChildrenData());
        }
        // 保存租户信息
        TenantUtil.setTenant(tenant);
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
