package Main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    public boolean num ;//模式变化
    String mod;//表示当前模式状态
    int length;
    int [] snakeX = new int[600];
    int [] snakeY = new int[500];
    String fx ;//U：up D:down R:right L:left

    //积分系统
    int score;
    int k=1;
    int s ;

    //画面帧的延迟
    int delay = 150;
    Timer timer1 = new Timer(delay,this);
    boolean isStare = false;//游戏是否开始

    //定义一个食物
    int foodX;
    int foodY;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;

    //构造器
    public GamePanel(){
        init();

        //获取键盘的监听事件
        this.setFocusable(true);

        //监听
        this.addKeyListener(this);

        //让时间动起来
        timer1.start();
    }

    //初始化
    public void init(){
        length = 3;
        int i;
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        fx = "R";

        //随机生成食物
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);

        for ( i = 1; i <length-1 ; i++) {
            if(snakeX[i] == foodX && snakeY[i] == foodY){
                foodX = 25 + 25*random.nextInt(34);
                foodY = 75 + 25*random.nextInt(24);
            }
        }
        score = 0;
    }

    //画板：画界面，划蛇
    //Graphics：画笔
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);//清屏

        this.setBackground(Color.WHITE);//背景颜色

        Date.header.paintIcon(this,g,25,11);
        g.fillRect(25,75,850,600);

        //画一条蛇
        if(fx.equals("R")){
            Date.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("L")){
            Date.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("U")){
            Date.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(fx.equals("D")){
            Date.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        for (int i = 1; i <length ; i++) {
            Date.body.paintIcon(this,g,snakeX[i],snakeY[i]);//蛇的长度
        }

        //画食物
        Date.food.paintIcon(this,g,foodX,foodY);

        if(!num){
            mod = "标准模式";
        }else mod = "无边界模式";

        //画游戏数据
        g.setColor(Color.WHITE);
        g.setFont(new Font("宋体",Font.BOLD,18));
        g.drawString("长度：" + length,750,35);
        g.drawString("分数：" + score,750,55 );
        g.drawString("当前模式："+ mod,55,35);
        g.drawString("难度：" + k,55,55);

        //游戏提示
        if(!isStare){
            //画一个文字，String
            g.setColor(Color.WHITE);//设置画笔颜色
            g.setFont(new Font("宋体",Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);
        }

        //难度变化
        if(s % 5 == 0 && s != 0){
            s -=5;
            k+=1;
        }

        //失败提醒
        if(isFail){
            g.setColor(Color.RED);//设置画笔颜色
            g.setFont(new Font("宋体",Font.BOLD,40));//设置字体
            g.drawString("分数为：" + score,200,200);
            g.drawString("游戏结束，按空格重新开始",200,300);
            delay = 150;
            timer1.setDelay(delay);//死亡后重置帧的延迟
        }
    }

    //接收键盘输入：监听
    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        //获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){//如果按下空格
            if(isFail){//失败，重新开始游戏
                delay = 150;
                isFail = false;
                init();//重新初始化游戏
            }else{
                isStare = !isStare;
            }
            repaint();//刷新界面
        }

        //键盘控制走向
        if(keyCode == KeyEvent.VK_LEFT){
            fx = "L";
        }else if(keyCode == KeyEvent.VK_RIGHT){
            fx = "R";
        }else if(keyCode == KeyEvent.VK_UP){
            fx = "U";
        }else if(keyCode == KeyEvent.VK_DOWN){
            fx = "D";
        }
    }

    //定时器，监听时间，帧
    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有结束
        //如果游戏处于开始状态
        if(isStare && !isFail){
            //右移
            for (int i = length-1; i>0 ; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //通过控制方向让头部移动
            if(fx.equals("R"))
            {
                snakeX[0] = snakeX[0] +25;//头部移动
                //边界判断
                if(snakeX[0] > 850){
                    if(!num){
                        isFail = true;
                    }
                    else
                        snakeX[0] = 25;
                    }
            }else if(fx.equals("L"))
            {
                snakeX[0] = snakeX[0] -25;//头部移动
                //边界判断
                if(snakeX[0]<25){
                    if(!num){
                        isFail = true;
                    }
                    else
                        snakeX[0] = 850;
                    }
            }else if(fx.equals("U"))
            {
                snakeY[0] = snakeY[0] -25;//头部移动
                //边界判断
                if(snakeY[0] < 75){
                    if(!num){
                        isFail = true;
                    }
                    else
                        snakeY[0] = 650;
                    }
            }else if(fx.equals("D"))
            {
                snakeY[0] = snakeY[0] + 25;//头部移动
                //边界判断
                if (snakeY[0] > 650) {
                    if(!num){
                        isFail = true;
                    }
                    else
                        snakeY[0] = 75;
                    }
            }

            //如果小蛇的头和食物坐标重合
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                //长度加1
                length++;
                snakeX[length-1] = 999999;
                snakeY[length-1] = 999999;
                score++;
                if(score % 5 == 0 && score !=0){
                    delay -= 50;
                    timer1.setDelay(delay);//改变当前帧的延迟
                }
                s++;

                //重新生成食物
                foodX = 25 + 25*random.nextInt(34);
                foodY = 75 + 25*random.nextInt(24);
                for (int i = 1; i <length-1 ; i++) {
                    if(snakeX[i] == foodX && snakeY[i] == foodY){
                        foodX = 25 + 25*random.nextInt(34);
                        foodY = 75 + 25*random.nextInt(24);
                    }
                }
            }

            //结束判断
            for (int i = 1; i <length ; i++) {
                if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    break;
                }
            }
            //刷新界面
            repaint();
        }
        timer1.start();//让时间动起来
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //接受键盘的输入：监听
    }
    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起：敲击
    }


}

