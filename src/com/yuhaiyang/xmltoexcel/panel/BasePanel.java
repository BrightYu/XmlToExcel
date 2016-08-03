package com.yuhaiyang.xmltoexcel.panel;

import com.yuhaiyang.xmltoexcel.ui.MyDialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Xml转Excel的界面
 */
public abstract class BasePanel extends JPanel implements ActionListener {
    protected JFrame mJframe;
    private JFileChooser mFileChooser;

    private JTextField mPathText;
    private JButton mSelectButton;

    private JButton mConvertButton;

    public BasePanel(JFrame frame) {
        super(new GridLayout(5, 1));
        mJframe = frame;
        mFileChooser = new JFileChooser();

        add(new Panel());
        Panel selectContent = new Panel();
        add(selectContent);
        add(new Panel());
        Panel ensureContent = new Panel();
        add(ensureContent);

        mSelectButton = new JButton("选择文件");
        mSelectButton.addActionListener(this);
        selectContent.add(mSelectButton);

        mPathText = new JTextField(20);
        selectContent.add(mPathText, BorderLayout.CENTER);


        mConvertButton = new JButton("转换");
        mConvertButton.addActionListener(this);
        ensureContent.add(mConvertButton, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mSelectButton) {
            int intRetVal = mFileChooser.showOpenDialog(this);
            if (intRetVal == JFileChooser.APPROVE_OPTION) {
                mPathText.setText(mFileChooser.getSelectedFile().getPath());
            }
        } else if (source == mConvertButton) {
            String path = mPathText.getText();
            if (path == null || path.length() == 0) {
                MyDialog dialog = new MyDialog(mJframe, "请选择文件", "请选择文件");
                dialog.setVisible(true);
            } else {
                doConvert(path);
            }
        }
    }

    /**
     * 进行转换
     *
     * @param path 文件路径
     */
    protected abstract void doConvert(String path);
}
