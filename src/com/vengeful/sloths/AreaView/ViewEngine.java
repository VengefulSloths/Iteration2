package com.vengeful.sloths.AreaView;
import javax.swing.*;

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
        setTitle("A game");
        setResizable(false);
        pack();
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
            if (delta < 50) {
                try {
                    Thread.sleep((50 - delta));
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
        this.target = jpanel;
        initUI();
    }
    public void killOldView(){
        remove(this.target);
    }



}

