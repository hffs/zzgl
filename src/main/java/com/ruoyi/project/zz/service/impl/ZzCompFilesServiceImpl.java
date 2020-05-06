package com.ruoyi.project.zz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.zz.domain.ZzCompFiles;
import com.ruoyi.project.zz.mapper.ZzCompFilesMapper;
import com.ruoyi.project.zz.service.IZzCompFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业资质附件Service业务层处理
 *
 * @author wfq
 * @date 2019-12-30
 */
@Service
public class ZzCompFilesServiceImpl extends ServiceImpl<ZzCompFilesMapper, ZzCompFiles> implements IZzCompFilesService
{
    @Autowired
    private  ZzCompFilesMapper filesMapper;

    @Override
    public List<ZzCompFiles> selectFilesList(ZzCompFiles zzCompFiles) {
        return filesMapper.selectFilesList(zzCompFiles);
    }

    @Override
    public List<ZzCompFiles> selectChildrenFileById(Long id) {
        return filesMapper.selectChildrenFileById(id);
    }

    @Override
    public int hasChildByFileId(Long id) {
        return filesMapper.hasChildByFileId(id);
    }

    @Override
    public int insertFile(ZzCompFiles zzCompFiles) {
        ZzCompFiles zzCompFiles2 = new ZzCompFiles();
        zzCompFiles2.setId(zzCompFiles.getParentId());
        List<ZzCompFiles> list = filesMapper.selectFilesList(zzCompFiles2);
        if(list.size() == 1){
            System.out.println(list.get(0).getFileName());
            zzCompFiles.setAncestors(list.get(0).getAncestors()+","+zzCompFiles.getParentId());
        }else{
            System.out.println(zzCompFiles.getFileName());
            zzCompFiles.setAncestors(zzCompFiles.getParentId()+"");
        }
        if(zzCompFiles.getFileType()!=null){


        if(zzCompFiles.getFileType().equals("文件夹")){
            StringBuilder sb = new StringBuilder();
            if(zzCompFiles.getAncestors().equals("0")){
                sb.append("/").append(zzCompFiles.getFileName()).append("/");
                zzCompFiles.setFilePath(sb.toString());
            }else{
                String ss[] =zzCompFiles.getAncestors().split(",");
                sb.append("/");
                for (int i = 1; i <ss.length ; i++) {
                    ZzCompFiles zz = new ZzCompFiles();
                    zz.setId(Long.parseLong(ss[i]));
                    List<ZzCompFiles> list2 = filesMapper.selectFilesList(zz);
                    sb.append(list2.get(0).getFileName()).append("/");

                }
                sb.append(zzCompFiles.getFileName()).append("/");
                System.out.println(RuoYiConfig.getAvatarPath()+sb.toString());
                File dest = new File(RuoYiConfig.getAvatarPath()+sb.toString());
                if(!dest.exists()&&!dest.isDirectory()) {
                    dest.mkdirs();
                }

            }
            zzCompFiles.setFilePath(RuoYiConfig.getAvatarPath()+sb.toString());
        }}

        return filesMapper.insertFile(zzCompFiles);
    }

    @Override
    public int updateFile(ZzCompFiles zzCompFiles) {
        ZzCompFiles newZzCompFiles = filesMapper.selectFileById(zzCompFiles.getParentId());
        ZzCompFiles oldZzCompFiles = filesMapper.selectFileById(zzCompFiles.getId());
        if(StringUtils.isNotNull(newZzCompFiles) && StringUtils.isNotNull(oldZzCompFiles)){
            String newAncestors = newZzCompFiles.getAncestors() + "," + newZzCompFiles.getId();
            String oldAncestors = oldZzCompFiles.getAncestors();
            zzCompFiles.setAncestors(newAncestors);
            updateDeptChildren(zzCompFiles.getId(), newAncestors, oldAncestors);
        }
        return filesMapper.updateFile(zzCompFiles);
    }

    /**
     * 根据projectId 删除文件
     * @param projectId
     */
    @Override
    public void deleteByProjectId(Long projectId) {
        filesMapper.deleteByProjectId(projectId);
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
        List<ZzCompFiles> children = filesMapper.selectChildrenFileById(deptId);
        for (ZzCompFiles child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            filesMapper.updateFileChildren(children);
        }
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param files 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildFileTreeSelect(List<ZzCompFiles> files)
    {
        List<ZzCompFiles> deptTrees = buildDeptTree(files);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param files 部门列表
     * @return 树结构列表
     */
    @Override
    public List<ZzCompFiles> buildDeptTree(List<ZzCompFiles> files)
    {
        List<ZzCompFiles> returnList = new ArrayList<ZzCompFiles>();
        for (Iterator<ZzCompFiles> iterator = files.iterator(); iterator.hasNext();)
        {
            ZzCompFiles t = (ZzCompFiles) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0 || t.getParentId() == 100)
            {
                recursionFn(files, t);
                returnList.add(t);
//                break;
            }
        }
        if (returnList.isEmpty())
        {
            returnList = files;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<ZzCompFiles> list, ZzCompFiles t)
    {
        // 得到子节点列表
        List<ZzCompFiles> childList = getChildList(list, t);
        t.setChildren(childList);
        for (ZzCompFiles tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<ZzCompFiles> it = childList.iterator();
                while (it.hasNext())
                {
                    ZzCompFiles n = (ZzCompFiles) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<ZzCompFiles> getChildList(List<ZzCompFiles> list, ZzCompFiles t)
    {
        List<ZzCompFiles> tlist = new ArrayList<ZzCompFiles>();
        Iterator<ZzCompFiles> it = list.iterator();
        while (it.hasNext())
        {
            ZzCompFiles n = (ZzCompFiles) it.next();
            if (n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ZzCompFiles> list, ZzCompFiles t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    @Override
    public Long[] findInSetAncestors(Long id) {
        return filesMapper.findInSetAncestors(id);
    }

    @Override
    public ZzCompFiles selectFileById(Long id) {
        return filesMapper.selectFileById(id);
    }
}
