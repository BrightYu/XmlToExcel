package com.yuhaiyang.xmltoexcel.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainUI extends JFrame {
    private static final String TITLE = "Android翻译转换工具";

    public MainUI() {
        super(TITLE);
    }

    public void initUI() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.setSize(380, 213);//窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
        this.setVisible(true);//显示窗体

        //锁定窗体
        this.setResizable(false);

        layoutUI();
        this.setVisible(true);
    }

    private void layoutUI() {

        //对象实例化
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
        //容器
        Container container = this.getLayeredPane();


        JPanel xmlToExcelPanel = new XmlToExcelPanel(this);
        tab.add(xmlToExcelPanel, "String转Excel");

        JPanel p2 = new ExcelToXmlPanel(this);
        tab.add(p2, "Excel转String");

        container.setLayout(new BorderLayout());
        container.add(tab, BorderLayout.CENTER);

    }
}
