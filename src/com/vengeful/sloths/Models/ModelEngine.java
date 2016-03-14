package com.vengeful.sloths.Models;

import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;

/**
 * Created by John on 1/30/2016.
 */
public class ModelEngine implements Runnable {
    //private MainController controller;
    private TimeController timeController;
    private boolean paused = false;
    private Thread gameThread = null;

    private static ModelEngine ourInstance = new ModelEngine();
    public static ModelEngine getInstance(){return ourInstance;}

    private ModelEngine(){
        timeController = new TimeController();
    }

    @Override
    public void run() {

        while (!paused) {
            //controller.continuousFunction();
            //System.out.println("tick");
            timeController.tick();
        }
    }

//    public void setController(MainController controller) {
//        this.controller = controller;
//    }

    public void start() {
        if(gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void pauseGame() {
        this.paused = true;
        ViewEngine.getInstance().pauseView();
        this.gameThread = null;
    }
    public void unpauseGame() {
        this.paused = false;
        ViewEngine.getInstance().unpauseView();
        if(gameThread == null){
            this.start();
        }
    }
}
