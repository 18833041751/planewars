package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameFrame extends Frame {
    private Background background = new Background();

    @Override
    public void paint(Graphics g) {
        background.draw(g);

    }

    /**
     * 使用这个方法初始化窗口
     */
    public void init(){
        //设置好尺寸
       setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

       //设置居中
        setLocationRelativeTo(null);

        enableInputMethods(false);
       //关闭0
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               System.exit(0);
           }
       } );

       new Thread(){
           @Override
           public void run() {

               while (true){
                   repaint();
                   try {
                       Thread.sleep(14);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

           }
       }.start();
        setVisible(true);
    }
    private Image offScreenImage = null;//创建缓冲区
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);//将缓冲区图片绘制到窗口目标
    }
}
