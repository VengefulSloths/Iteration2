package com.vengeful.sloths.AreaView;
import com.vengeful.sloths.Utility.Config;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 2/20/2016.
 */
public class ViewEngine extends JFrame implements Runnable{

    private JPanel target;

    private static ViewEngine ourInstance = new ViewEngine();
    public static ViewEngine getInstance(){return ourInstance;}

    private ViewEngine(){}

//    private ViewEngine(JPanel jpanel) {
//        this.target = jpanel;
//        initUI();
//    }

    private void initUI() {
        add(target);
        setTitle("Divergent Skies");
        setResizable(false);
        pack();
        //this.setPreferredSize(new Dimension(1200, 1000));
        //this.setSize(1200, 1000);
        this.setPreferredSize(new Dimension(Config.instance().getWindowWidth(), Config.instance().getWindowHeight())); //edit
        this.setSize(Config.instance().getWindowWidth(), Config.instance().getWindowHeight()); //edit
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void run() {
        int count = 0;
        while(true) {
            long lastTime = System.currentTimeMillis();

            ViewTime.getInstance().tick();
            this.repaint();

            long delta = System.currentTimeMillis() - lastTime;
            if (delta < 30) {
                try {
                    Thread.sleep((30 - delta));
                } catch (Exception e) {
                    //dont care
                }

            } else {
                //System.out.Println("View tick took too long");
            }
            ////System.out.Println("View Tick");
        }
    }
    public void start() {
        new Thread(this).start();
    }



    public void registerView(JPanel jpanel){
        if(target != null) {
            killOldView();
        }
        this.target = jpanel;
        initUI();
    }
    public void killOldView(){
        if (target != null)
            remove(this.target);
    }



}

