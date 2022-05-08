package Main;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StarGame extends JFrame {

    public static void main(String[] args) {
        // 创建开关按钮
        JToggleButton toggleBtn = new JToggleButton();

        // 首先设置不绘制按钮边框
        toggleBtn.setBorderPainted(false);

        // 设置 选中(开) 和 未选中(关) 时显示的图片
        toggleBtn.setSelectedIcon(new ImageIcon("Button/on.png"));

        toggleBtn.setIcon(new ImageIcon("Button/off.png"));

        // 添加 toggleBtn 的状态被改变的监听
        toggleBtn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // 获取事件源（即开关按钮本身）
                JToggleButton toggleBtn = (JToggleButton) e.getSource();
            }
        });

        final JFrame jf = new JFrame("贪吃蛇");
        //设置界面大小
        jf.setSize(400, 400);

        jf.setLocationRelativeTo(null);
        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn = new JButton("开始游戏");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击按钮, 显示新的一个窗口
                showNewWindow(jf, toggleBtn);
            }
        });

        JPanel panel = new JPanel();
        //设置流动布局
        panel.setLayout(new FlowLayout(1,15,50));

        //添加文本
        JLabel Jl = new JLabel("标准模式");
        Jl.setSize(100,25);
        Jl.setFont(new Font("宋体",Font.BOLD,16));

        JLabel Jl2 = new JLabel("无边界模式");
        Jl2.setSize(100,25);
        Jl2.setFont(new Font("宋体",Font.BOLD,16));

        panel.add(Jl);
        panel.add(toggleBtn);
        panel.add(Jl2);
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    public static void showNewWindow(JFrame relativeWindow,JToggleButton toggleBtn ) {
        // 创建一个新窗口
        JFrame newJFrame = new JFrame("202012900538");

        newJFrame.setSize(915, 775);

        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setLocationRelativeTo(relativeWindow);

        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 窗口设置为不可改变大小
        newJFrame.setResizable(false);

        GamePanel gamePanel = new GamePanel();

        gamePanel.num = toggleBtn.isSelected();

        newJFrame.add(gamePanel);

        newJFrame.setContentPane(gamePanel);

        newJFrame.setVisible(true);
    }
}
