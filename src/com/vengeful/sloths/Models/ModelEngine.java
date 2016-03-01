package com.vengeful.sloths.Models;

import com.vengeful.sloths.Models.TimeModel.TimeController;

/**
 * Created by John on 1/30/2016.
 */
public class ModelEngine implements Runnable {
    //private MainController controller;
    private TimeController timeController;

    private static ModelEngine ourInstance = new ModelEngine();
    public static ModelEngine getInstance(){return ourInstance;}

    private ModelEngine(){
        timeController = new TimeController();
    }

    @Override
    public void run() {
        while(true) {
            ////System.out.Println("Model Tick");

            //controller.continuousFunction();
            timeController.tick();

        }
    }

//    public void setController(MainController controller) {
//        this.controller = controller;
//    }

    public void start() {
        new Thread(this).start();
    }
}
