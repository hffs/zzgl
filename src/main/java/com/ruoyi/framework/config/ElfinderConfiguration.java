package com.ruoyi.framework.config;

import cn.kong.elfinder.param.Constraint;
import cn.kong.elfinder.param.Node;
import cn.kong.elfinder.param.Thumbnail;
import com.ruoyi.common.utils.TenantUtil;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ElfinderConfiguration {

    private Thumbnail thumbnail = new Thumbnail();

    private List<Node> volumes = new ArrayList<>();

    private Long maxUploadSize = -1L;//默认不限制

    public Thumbnail getThumbnail() {
        thumbnail.setWidth(new BigInteger("80"));
        return thumbnail;
    }

    public List<Node> getVolumes() {
        System.out.println("wolai le ");
        Node node = new Node();
        node.setSource("fileSystem");
        Random random = new Random();
        if(TenantUtil.getTenant()!=null){

            System.out.println(TenantUtil.getTenant().getTenantName());
            node.setAlias(TenantUtil.getTenant().getTenantName());
            node.setPath("/D:/CR/" + TenantUtil.getTenant().getTenantId());
        }else {
            node.setAlias("文件目录");
            node.setPath("/D:/CR/"+random.nextInt(10));
        }
        node.set_default(true);
        // node.setLocale();
        Constraint constraint = new Constraint();
        constraint.setLocked(false);
        constraint.setReadable(true);
        constraint.setWritable(true);
        node.setConstraint(constraint);
        volumes.add(node);
        return volumes;
    }


    public Long getMaxUploadSize() {
        return maxUploadSize;
    }

}
