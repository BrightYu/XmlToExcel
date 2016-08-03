package com.yuhaiyang.xmltoexcel.panel;

import com.yuhaiyang.xmltoexcel.model.Cistern;
import com.yuhaiyang.xmltoexcel.ui.MyDialog;
import com.yuhaiyang.xmltoexcel.utils.ExcelUtils;
import com.yuhaiyang.xmltoexcel.utils.StringUtils;
import com.yuhaiyang.xmltoexcel.utils.XmlUtils;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;

/**
 * Xml转Excel的界面
 */
public class XmlToExcelPanel extends BasePanel {

    public XmlToExcelPanel(JFrame frame) {
        super(frame);
    }

    @Override
    protected void doConvert(String path) {
        try {
            File file = new File(path);
            String parentPath = file.getParent();

            List<Cistern> datas = XmlUtils.parse(path);
            String reuslt = ExcelUtils.create(datas, parentPath);
            MyDialog dialog = new MyDialog(mJframe, "文件已经转换完毕", StringUtils.plusString("文件路径：", reuslt));
            dialog.setVisible(true);
        } catch (Exception exception) {
            MyDialog dialog = new MyDialog(mJframe, "转换报错", exception.toString());
            dialog.setVisible(true);
        }
    }
}
