package com.yuhaiyang.xmltoexcel.ui;

import com.yuhaiyang.xmltoexcel.utils.StringUtils;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ResultDialog extends JDialog implements ActionListener {
    private String mPath;

    public ResultDialog(JFrame frame, String path) {
        super(frame, "转换成功", true);
        mPath = path;
        init();
    }


    private void init() {
        setSize(300, 180);
        setLocationRelativeTo(null);
        Container content = getContentPane();
        content.setLayout(null);
        JLabel label = new JLabel(StringUtils.plusString("文件路径：", mPath));
        label.setBounds(0, 0, 300, 60);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(label);

        JButton button = new JButton("打开");
        button.setBounds(120, 80, 60, 30);
        button.addActionListener(this);
        content.add(button);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File file = new File(mPath);
            Desktop.getDesktop().open(file.getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}

