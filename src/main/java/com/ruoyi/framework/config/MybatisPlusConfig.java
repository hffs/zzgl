package com.ruoyi.framework.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.ruoyi.common.utils.TenantUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
public class MybatisPlusConfig {

    private static final String SYSTEM_TENANT_ID = "tenant_id";

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();

        // 创建租户SQL解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();

        // 设置租户处理器
        tenantSqlParser.setTenantHandler(new TenantHandler() {

            @Override
            public Expression getTenantId(boolean where) {
                //获取租户id
                Long tenantId = TenantUtil.getTenant().getTenantId();
                return new LongValue(tenantId);
            }

            @Override
            public String getTenantIdColumn() {
                // 对应数据库租户ID的列名
                return SYSTEM_TENANT_ID;
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 是否需要需要过滤某一张表
                List<String> tableNames = new ArrayList<String>();
                tableNames.add("tables");//mysql系统表
                tableNames.add("gen_table");//代码生成
                tableNames.add("gen_table_column");//代码生成

                tableNames.add("sys_tenant");//租户表
                tableNames.add("sys_tenant_role");//租户角色表
                tableNames.add("sys_tenant_menu");//租户菜单表
                tableNames.add("sys_menu");//菜单表
                tableNames.add("sys_dict_data");//字典表
                tableNames.add("sys_dict_type");//字典表
                tableNames.add("sys_logininfor");//登录日志
                tableNames.add("sys_oper_log");//操作日志
                tableNames.add("sys_notice");//通知公告
                tableNames.add("zztp_atlas");//通知公告
                tableNames.add("zztp_details");//通知公告
                tableNames.add("zztp_subclass");//通知公告
                tableNames.add("public_gjzc");//国家政策
                tableNames.add("dev_test");//国家政策
                tableNames.add("invitation_histry");//历史记录
                tableNames.add("evaluation_table");//评审表元素
                tableNames.add("invitation_evaluation");//评审表内容
                tableNames.add("qualification_template");//在线咨询模板表
                tableNames.add("cascade_consultation");//在线咨询级联入口表
                return tableNames.contains(tableName);
            }
        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 过滤自定义Mapper.Method
                List<String> filterMethods = new ArrayList<>();
                // 用户
                filterMethods.add("com.ruoyi.project.system.mapper.SysUserMapper.saveTenantUser");
                filterMethods.add("com.ruoyi.project.system.mapper.SysUserMapper.resetTenantPwd");
                // 租户菜单表
                filterMethods.add("com.ruoyi.project.system.mapper.SysTenantMenuMapper.queryByRoleId");
                filterMethods.add("com.ruoyi.project.system.mapper.SysTenantMenuMapper.deleteByRoleId");
                filterMethods.add("com.ruoyi.project.system.mapper.SysTenantMenuMapper.batchSaveByRoleId");
                filterMethods.add("com.ruoyi.project.system.mapper.SysTenantMenuMapper.deleteByMenuId");
                // 角色菜单表
                filterMethods.add("com.ruoyi.project.system.mapper.SysRoleMenuMapper.deleteByMenuId");

                //历史记录表
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationHistryMapper.addHistry");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationHistryMapper.addHistryByFileId");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationHistryMapper.listByInvitationId");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationHistryMapper.listByFileId");

                //文件目录表
//                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationCatalogMapper.selectInvitationCatalogListBytenantId");
               //文件附件表
//                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationFileMapper.selectInvitationFileList");
//                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationFileMapper.selectInvitationFileList_COUNT");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationFileMapper.editStatus");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationFileMapper.editStatus");
//                filterMethods.add("com.ruoyi.project.onlineReview.mapper.CascadeConsultationMapper.getById");

                //评审邀约方法机构方法忽略

                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationAssessmentMapper.selectByJgInvitationAssessmentList");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationAssessmentMapper.selectByJgInvitationAssessmentList_COUNT");
                filterMethods.add("com.ruoyi.project.onlineReview.mapper.InvitationAssessmentMapper.selectInvitationAssessmentById");
                filterMethods.add("com.ruoyi.project.onlineApplication.mapper.ConsultationManagementMapper.selectByTeacherConsultationManagementList_COUNT");
                filterMethods.add("com.ruoyi.project.onlineApplication.mapper.ConsultationManagementMapper.selectByTeacherConsultationManagementList");
                filterMethods.add("com.ruoyi.project.onlineApplication.mapper.ConsultationManagementMapper.updateConsultationManagement");

                //
                return filterMethods.contains(ms.getId());
            }
        });


        return paginationInterceptor;
    }
}
