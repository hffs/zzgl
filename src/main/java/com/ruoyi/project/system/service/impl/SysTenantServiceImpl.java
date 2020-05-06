package com.ruoyi.project.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.ConfigConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysTenant;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysTenantMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysTenantService;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统租户Service业务层处理
 *
 * @author wfq
 * @date 2020-02-08
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService
{
    @Resource
    private SysTenantMapper tenantMapper;
    @Resource
    private ISysUserService userService;
    @Resource
    private ISysConfigService configService;

    @Override
    public List<SysTenant> queryList(SysTenant sysTenant) {
        return tenantMapper.queryList(sysTenant);
    }

    @Override
    public SysTenant queryByTenantNo(String tenantNo) {
        if (StringUtils.isEmpty(tenantNo)){
            return null;
        }
        return tenantMapper.queryByTenantNo(tenantNo);
    }

    @Override
    public boolean updateStatus(SysTenant sysTenant) {
        SysTenant tenant = new SysTenant();
        tenant.setStatus(sysTenant.getStatus());
        tenant.setTenantId(sysTenant.getTenantId());
        return tenantMapper.updateStatus(tenant) > 0;
    }

    @Override
    public boolean removeTenant(Long tenantId) {
        return tenantMapper.removeTenant(tenantId) > 0;
    }

    @Override
    public boolean resetPwd(SysTenant sysTenant) {
        String password = SecurityUtils.encryptPassword(sysTenant.getPassword());
        return userService.resetTenantPwd(sysTenant.getTenantId(), password) > 0;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean save(SysTenant sysTenant) {
        if (hasTenantNo(sysTenant)){
            throw new CustomException("租户编码已存在");
        }
        Date now = new Date();
        sysTenant.setDueTime(now);
        sysTenant.setCreateTime(now);
        sysTenant.setUpdateTime(now);
        super.save(sysTenant);
        // 保存用户信息
        SysUser sysUser = makeSysUser(sysTenant);
        return userService.saveTenantUser(sysUser);
    }

    private SysUser makeSysUser(SysTenant sysTenant){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setNickName("租户管理员");
        String password = configService.selectConfigByKey(ConfigConstants.USER_PASSWORD);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        sysUser.setTenantId(sysTenant.getTenantId());
        return sysUser;
    }

    @Override
    public boolean updateTenant(SysTenant sysTenant) {
        if (hasTenantNo(sysTenant)){
            throw new CustomException("租户编码已存在");
        }
        Date now = new Date();
        sysTenant.setUpdateTime(now);
        return tenantMapper.updateTenant(sysTenant) > 0;
    }

    private boolean hasTenantNo(SysTenant tenant){
        return tenantMapper.hasTenantNo(tenant) > 0;
    }

    /**
     * 查询租户正常数
     * @return 正常租户数量
     */

    public Integer sysTenantCharTnormal(){
        return tenantMapper.sysTenantCharTnormal();
    }

    /**
     * 查询 租户停用数量
     * @return 停用租户数量
     */

    public Integer sysTenantDiscontinue(){
        return tenantMapper.sysTenantDiscontinue();
    }

    @Override
    public Integer queryByRoleId(Long roleId) {
        return tenantMapper.queryByRoleId(roleId);
    }
}
