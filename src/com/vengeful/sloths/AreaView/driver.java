package com.vengeful.sloths.AreaView;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;

/**
 * Created by alexs on 2/20/2016.
 */
public class driver {
    public static void main(String ... args) {


        DynamicImageFactory.getInstance().loadDynamicImage("resources/terrain/grass.xml");


        System.out.println("Starting driver");
        AreaView av = new AreaView();
        ViewEngine viewEngine = new ViewEngine(av);
        viewEngine.start();
        System.out.println("Finished with driver");
    }
}
