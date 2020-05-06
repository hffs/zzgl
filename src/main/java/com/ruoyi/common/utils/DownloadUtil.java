package com.ruoyi.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;
import com.ruoyi.project.onlineReview.domain.InvitationEvaluation;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 在线咨询导出工具类
 */
public class DownloadUtil {

    /**
     * 导出工具类
     * @param fileName
     * @param downloadEnum
     * @param datas
     * @throws IOException
     */
    public static void export(String fileName, DownloadEnum downloadEnum, List<InvitationEvaluation> datas) throws IOException {
        // 转换下载名称
        fileName = fileName + "-" + DateUtil.currentSeconds() + ".docx";
        // 转换模板路径
        String filePath = downloadEnum.getPath();
        // 转换数据格式
        Map<String, InvitationEvaluation> mapData = datas.stream().collect(Collectors.toMap(InvitationEvaluation::getEidStr, a -> a,(k1,k2)->k1));
        // 调用导出
        initExport(fileName, filePath, mapData);
    }

    private static void initExport(String fileName, String filePath, Map<String, InvitationEvaluation> params) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        Word07Writer writer = new Word07Writer(FileUtil.file(filePath));



        //替换表格里面的变量
        replaceInTable(writer.getDoc(), params);
        // 这里结束修改
        writer.flush(response.getOutputStream(), true);
    }

    /**
     * 替换段落里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInPara(XWPFDocument doc, Map<String, InvitationEvaluation> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params);
        }
    }

    /**
     * 格式化答案
     *
     * @param groupValue
     * @return
     */
    private static String formatAnswer(String groupValue) {
        if (groupValue == null){
            groupValue = "";
        }
        int[] code = {0x2610, 0x25a0};
        String uncheck = new String(code, 0, 1);
        String checked = new String(code, 1, 1);
        String formatStr = "1满足要求\n2改进项\n3一般不符合\n4严重不符合";
        formatStr = formatStr.replace(groupValue.toUpperCase(), checked)
                .replace("1", uncheck)
                .replace("2", uncheck)
                .replace("3", uncheck)
                .replace("4", uncheck);
        return formatStr;
    }

    /**
     * 替换段落里面的变量
     *
     * @param params 参数
     */
    private static void replaceInPara(XWPFParagraph paragraph, Map<String, InvitationEvaluation> params) {
        String paragraphText = paragraph.getParagraphText();
        Matcher matcher = matcher(paragraphText);
        while (matcher.find()) {
            String groupName = matcher.group();
            String groupKey = groupName.substring(2, groupName.length() - 1);
            StringBuilder builder = new StringBuilder();
            // 获取数据
            InvitationEvaluation evaluation = params.get(groupKey);
            if (evaluation != null){
                builder.append(evaluation.getEvaluationResult());
                builder.append("\n");
                builder.append(" “评估发现”为：");
                builder.append("\n");
                builder.append(formatAnswer(evaluation.getStatus()));
            }
            paragraphText = paragraphText.replace(groupName, builder.toString());
        }
        matcher.reset();
        if (matcher.find()) {
            // 只保留第一个Run
            for (int i = (paragraph.getRuns().size() - 1); i > 0; i--) {
                paragraph.removeRun(i);
            }
            String[] array = paragraphText.split("\n");
            XWPFRun firstRun = paragraph.getRuns().get(0);
            firstRun.setText(array[0], 0);
            int index = 1;
            for (int i = 1; i < array.length; i++) {
                //取数据
                String text = array[i];
                if (StringUtils.isEmpty(text)){
                    continue;
                }
                //换行
                XWPFRun returnRun = paragraph.insertNewRun(index++);
                returnRun.addBreak();
                XWPFRun newRun = paragraph.insertNewRun(index++);
                newRun.setFontFamily(firstRun.getFontFamily());
                newRun.setFontSize(firstRun.getFontSize());
                newRun.setText(text);
            }

        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc    要替换的文档
     * @param params 参数
     */
    private static void replaceInTable(XWPFDocument doc, Map<String, InvitationEvaluation> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        replaceInPara(para, params);
                    }
                }
            }
        }
    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private static Matcher matcher(String str) {
        return Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE).matcher(str);
    }

    public static void main(String[] args) {

        int[] code = {0x2610, 0x25a0};
        String uncheck = new String(code, 0, 1);
        String checked = new String(code, 1, 1);
        System.out.println(uncheck);
        System.out.println(checked);
    }
}
