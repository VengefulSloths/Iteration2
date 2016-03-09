package com.vengeful.sloths.Views.AreaView;

import com.vengeful.sloths.Views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class AreaView extends View {

    public AreaView() {

    }


    public AreaView(int viewWidth, int viewHeight) {

        super(viewWidth, viewHeight);
        this.setPreferredSize(new Dimension(this.getViewWidth(), this.getViewHeight()));

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        generateImageBackground("resources/desert.jpg", g);

    }
}
