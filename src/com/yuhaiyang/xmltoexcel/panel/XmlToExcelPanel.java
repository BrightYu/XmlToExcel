package com.yuhaiyang.xmltoexcel.panel;

import com.yuhaiyang.xmltoexcel.model.Cistern;
import com.yuhaiyang.xmltoexcel.ui.MyDialog;
import com.yuhaiyang.xmltoexcel.ui.ResultDialog;
import com.yuhaiyang.xmltoexcel.utils.ExcelUtils;
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
            ResultDialog dialog = new ResultDialog(mJframe, reuslt);
            dialog.setVisible(true);
        } catch (Exception exception) {
            System.out.println("exception = " + exception.toString());
            MyDialog dialog = new MyDialog(mJframe, "转换报错", exception.toString());
            dialog.setVisible(true);
        }
    }
}
