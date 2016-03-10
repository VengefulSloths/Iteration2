package com.vengeful.sloths.Views.ViewManager;

import com.vengeful.sloths.AreaView.AreaView;
import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Views.HUDView.HUDView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2/27/2016.
 */
public class DefaultViewComposite extends ViewComposite {

    AreaView areaview;
    HUDView hudView;
    JPanel sidePanel;

    protected static final int AREA_VIEW_WIDTH =(int) Config.instance().getAreaViewWidth();
    protected static final int AREA_VIEW_HEIGHT = (int) Config.instance().getAreaViewHeight();
    protected static final int HUD_VIEW_WIDTH = (int) Config.instance().getHUDViewWidth(); //relative to the
    protected static final int HUD_VIEW_HEIGHT = (int) Config.instance().getHUDViewHeight(); //relative to the


    public DefaultViewComposite() {
        initializeViewComposite();
    }


    public DefaultViewComposite(AreaView av, HUDView hv)  {
        this.areaview = av;
        this.hudView = hv;
        initializeViewComposite();
    }

    public void initializeViewComposite() {

        //EDIT: make a factory for the views
        /*show the areaview/gameplay behind it since the only real component in it is the hudview at the top*/

        this.setLayout(new BorderLayout());
        //EDIT: make a factory for the views
        //this.areaview = new AreaView(this.AREA_VIEW_WIDTH, this.AREA_VIEW_HEIGHT);
        this.hudView = new HUDView(this.HUD_VIEW_WIDTH, this.HUD_VIEW_HEIGHT);

        this.sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBackground(new Color(0f,0f,0f,0f));

        this.setPreferredSize(new Dimension(AREA_VIEW_WIDTH, AREA_VIEW_HEIGHT));

        this.areaview.setLayout(new BorderLayout());
        this.sidePanel.setLayout(new BorderLayout());

        sidePanel.add(hudView, BorderLayout.NORTH);
        areaview.add(sidePanel, BorderLayout.WEST); /* we add the sidePanel to the areaview because it needs to be transparent to
        show the areaview/gameplay behind it since the only real component in it is the hudview at the top*/
        this.add(areaview);
        this.hudView.setBackground(new Color(0f,0f,0f,0.1f));
        this.areaview.setBackground(Color.blue);

    }

    public void removeView(JComponent component) {
        this.remove(component);
    }

    public void addView(JComponent component) {
        this.add(component);
    }

    public void resizeComponent(JComponent component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
    }


}
