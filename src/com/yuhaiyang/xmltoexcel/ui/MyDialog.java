package com.yuhaiyang.xmltoexcel.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MyDialog extends JDialog {
    public MyDialog(JFrame frame, String errorMessage) {
        this(frame, "", errorMessage);
    }

    public MyDialog(JFrame frame, String title, String errorMessage) {
        super(frame, title, true);
        init(errorMessage);
    }

    private void init(String errorMessage) {
        setSize(300, 180);
        setLocationRelativeTo(null);
        Container content = getContentPane();
        content.setLayout(null);
        JLabel label = new JLabel(errorMessage);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, 300, 60);
        content.add(label);

        JButton button = new JButton("确定");
        button.setBounds(120, 80, 60, 30);
        content.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}

