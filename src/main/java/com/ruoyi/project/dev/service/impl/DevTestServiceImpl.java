package com.ruoyi.project.dev.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.dev.domain.DevTest;
import com.ruoyi.project.dev.mapper.DevTestMapper;
import com.ruoyi.project.dev.service.IDevTestService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 开发-测试Service业务层处理
 *
 * @author wfq
 * @date 2020-02-24
 */
@Service
public class DevTestServiceImpl extends ServiceImpl<DevTestMapper, DevTest> implements IDevTestService {

}
