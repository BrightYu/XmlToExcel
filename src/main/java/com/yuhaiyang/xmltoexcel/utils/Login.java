package com.yuhaiyang.xmltoexcel.utils;

/**
 * 功能：学生成绩管理系统
 * 步骤1、登录界面的静态实现
 * author：ywq
 */

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
    //定义组件
    JPanel jp1, jp2, jp3;//面板
    JLabel jlb1, jlb2;//标签
    JButton jb1, jb2;//按钮
    JTextField jtf;//文本
    JPasswordField jpf;//密码


    //构造函数
    public Login() {
        //创建面板
        jp1 = new JPanel();
        jp1.setBackground(Color.BLUE);
        jp2 = new JPanel();
        jp3 = new JPanel();
        //创建标签
        jlb1 = new JLabel("用户名");
        jlb2 = new JLabel("密    码");
        //创建按钮
        jb1 = new JButton("登录");
        jb2 = new JButton("重置");
        //创建文本框
        jtf = new JTextField(10);
        //创建密码框
        jpf = new JPasswordField(10);

        //设置布局管理
        this.setLayout(new GridLayout(3, 1));//网格式布局

        //加入各个组件
        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);

        jp3.add(jb1);
        jp3.add(jb2);

        //加入到JFrame
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        //设置窗体
        this.setTitle("用户登录");//窗体标签
        this.setSize(300, 150);//窗体大小
        this.setLocationRelativeTo(null);//在屏幕中间显示(居中显示)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出关闭JFrame
        this.setVisible(true);//显示窗体

        //锁定窗体
        this.setResizable(false);
    }
}
