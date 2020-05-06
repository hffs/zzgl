package com.ruoyi.project.onlineReview.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.onlineReview.domain.InvitationCatalog;
import com.ruoyi.project.onlineReview.mapper.InvitationCatalogMapper;
import com.ruoyi.project.onlineReview.service.InvitationCatalogService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationCatalogServiceImpl extends ServiceImpl<InvitationCatalogMapper, InvitationCatalog> implements InvitationCatalogService{

    @Resource
    private InvitationCatalogMapper invitationCatalogMapper;
        /**
         * 查询部门管理数据
         *
         * @param dept 部门信息
         * @return 部门信息集合
         */
        @Override
        public List<InvitationCatalog> selectInvitationCatalogList(InvitationCatalog dept)
        {
            return invitationCatalogMapper.selectInvitationCatalogList(dept);
        }


    @Override
    public List<InvitationCatalog> selectInvitationCatalogListBytenantId(InvitationCatalog invitationCatalog) {
        return invitationCatalogMapper.selectInvitationCatalogListBytenantId(invitationCatalog);
    }

    /**
         * 构建前端所需要树结构
         *
         * @param depts 部门列表
         * @return 树结构列表
         */
        @Override
        public List<InvitationCatalog> buildInvitationCatalogTree(List<InvitationCatalog> depts)
        {
            List<InvitationCatalog> returnList = new ArrayList<InvitationCatalog>();
            for (Iterator<InvitationCatalog> iterator = depts.iterator(); iterator.hasNext();)
            {
                InvitationCatalog t = iterator.next();
                // 根据传入的某个父节点ID,遍历该父节点的所有子节点
                if (t.getParentId() == 0L)
                {
                    recursionFn(depts, t);
                    returnList.add(t);
                } else {
                    break;
                }
            }
            if (returnList.isEmpty())
            {
                returnList = depts;
            }
            return returnList;
        }

        /**
         * 构建前端所需要下拉树结构
         *
         * @param depts 部门列表
         * @return 下拉树结构列表
         */
        @Override
        public List<TreeSelect> buildInvitationCatalogTreeSelect(List<InvitationCatalog> depts)
        {
            List<InvitationCatalog> deptTrees = buildInvitationCatalogTree(depts);
            return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
        }

        /**
         * 是否存在子节点
         *
         * @param deptId 部门ID
         * @return 结果
         */
        @Override
        public boolean hasChildByInvitationCatalogId(Long deptId)
        {
            return invitationCatalogMapper.hasChildBycatalogId(deptId) > 0;
        }

        @Override
        public List<Long> queryChildId(Long deptId) {
            return invitationCatalogMapper.queryChildId(deptId);
        }

        /**
         * 校验部门名称是否唯一
         *
         * @param dept 部门信息
         * @return 结果
         */
        @Override
        public String checkInvitationCatalogNameUnique(InvitationCatalog dept)
        {
            Long deptId = StringUtils.isNull(dept.getCatalogId()) ? -1L : dept.getCatalogId();
            InvitationCatalog info = invitationCatalogMapper.checkcatalogNameUnique(dept.getCatalogName(), dept.getParentId());
            if (StringUtils.isNotNull(info) && info.getCatalogId().longValue() != deptId.longValue())
            {
                return UserConstants.NOT_UNIQUE;
            }
            return UserConstants.UNIQUE;
        }

        /**
         * 新增保存部门信息
         *
         * @param dept 部门信息
         * @return 结果
         */
        @Override
        public boolean insertInvitationCatalog(InvitationCatalog dept)
        {
            if (dept.getParentId() != 0L){
                InvitationCatalog info = super.getById(dept.getParentId());
                // 如果父节点不为正常状态,则不允许新增子节点
//                if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
//                {
//                    throw new CustomException("部门停用，不允许新增");
//                }
                dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
            } else {
                dept.setAncestors(dept.getParentId() + "");
            }
            return super.save(dept);
        }

        /**
         * 修改保存部门信息
         *
         * @param dept 部门信息
         * @return 结果
         */
        @Override
        public int updateInvitationCatalog(InvitationCatalog dept)
        {
            InvitationCatalog newParentDept = super.getById(dept.getParentId());
            InvitationCatalog oldDept = super.getById(dept.getCatalogId());
            if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
            {
                String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getCatalogId();
                String oldAncestors = oldDept.getAncestors();
                dept.setAncestors(newAncestors);
                updateDeptChildren(dept.getCatalogId(), newAncestors, oldAncestors);
            }
            int result = invitationCatalogMapper.updateInvitationCatalog(dept);

            return result;
        }

        /**
         * 修改该部门的父级部门状态
         *
         * @param dept 当前部门
         */
        private void updateParentInvitationCatalogStatus(InvitationCatalog dept)
        {
            dept = getById(dept.getCatalogId());
            invitationCatalogMapper.updateInvitationCatalog(dept);
        }

        /**
         * 修改子元素关系
         *
         * @param deptId 被修改的部门ID
         * @param newAncestors 新的父ID集合
         * @param oldAncestors 旧的父ID集合
         */
        public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
        {
            List<InvitationCatalog> children = invitationCatalogMapper.selectChildrenInvitationCatalogById(deptId);
            for (InvitationCatalog child : children)
            {
                child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
            }
            if (children.size() > 0)
            {
                invitationCatalogMapper.updateInvitationCatalogChildren(children);
            }
        }

        /**
         * 递归列表
         */
        private void recursionFn(List<InvitationCatalog> list, InvitationCatalog t)
        {
            // 得到子节点列表
            List<InvitationCatalog> childList = getChildList(list, t);
            t.setChildren(childList);
            for (InvitationCatalog tChild : childList)
            {
                if (hasChild(list, tChild))
                {
                    // 判断是否有子节点
                    Iterator<InvitationCatalog> it = childList.iterator();
                    while (it.hasNext())
                    {
                        InvitationCatalog n = it.next();
                        recursionFn(list, n);
                    }
                }
            }
        }

        /**
         * 得到子节点列表
         */
        private List<InvitationCatalog> getChildList(List<InvitationCatalog> list, InvitationCatalog t)
        {
            List<InvitationCatalog> tlist = new ArrayList<>();
            Iterator<InvitationCatalog> it = list.iterator();
            while (it.hasNext())
            {
                InvitationCatalog n = it.next();
                if (n.getParentId().longValue() == t.getCatalogId().longValue())
                {
                    tlist.add(n);
                }
            }
            return tlist;
        }

        /**
         * 判断是否有子节点
         */
        private boolean hasChild(List<InvitationCatalog> list, InvitationCatalog t)
        {
            return getChildList(list, t).size() > 0;
        }
}

