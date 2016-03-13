package com.vengeful.sloths.Views.TradeView;

import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Views.InventoryView.GridInventoryView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by harrison on 3/13/16.
 */
public class TradeView extends JPanel {
    private GridAvatarInvViewTrading avatarInvView;
    private GridEntityInvViewTrading entityInvView;
    private View containerPanel;
    private int bargainSkill;
    protected static final int CHARACTER_VIEW_WIDTH =(int) Config.instance().getCharacterViewWidth();
    protected static final int CHARACTER_VIEW_HEIGHT = (int) Config.instance().getCharacterViewHeight();
    public static int getCharacterViewWidth() {
        return CHARACTER_VIEW_WIDTH;
    }
    public static int getCharacterViewHeight() {
        return CHARACTER_VIEW_HEIGHT;
    }

    public TradeView(GridAvatarInvViewTrading avatarInvView, GridEntityInvViewTrading entityInvView, int bargainSkill) {
        this.avatarInvView = avatarInvView;
        this.entityInvView = entityInvView;
        this.bargainSkill = bargainSkill;
        initDefaultUI();
    }

    public void initDefaultUI() {

        JPanel pushPanel = new JPanel(); //this JPanel is used to center the CharacterView in the ViewManager. It "pushes" down the CharacterView in the ViewManager
        pushPanel.setPreferredSize(new Dimension(Config.instance().getAreaViewWidth(), Config.instance().getAreaViewHeight()/6));
        pushPanel.setBackground(new Color(0f,0f,0f,0f)); //pushPanel needs to be invisible


        //this.containerPanel = new JPanel();
        this.setBackground(new Color(0f,0f,0f,0f));
        //this.containerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.setLayout(new FlowLayout());
        /* begin old layout code */
        //this.containerPanel.setPreferredSize(new Dimension(this.getCharacterViewWidth(),this.getCharacterViewHeight()));
        //this.containerPanel.setLayout(new GridLayout(1,3));
        //this.containerPanel.add(this.inventoryView);
        //this.containerPanel.add(this.equipmentView);
        //this.containerPanel.add(this.statsView);
        this.containerPanel = new View();
        this.containerPanel.setBackgroundImageFileName(("resources/skyCharacterView.png"));
        this.containerPanel.setPreferredSize(new Dimension(this.getCharacterViewWidth(),this.getCharacterViewHeight()));
        this.containerPanel.setLayout(new GridLayout(1,2));
        this.containerPanel.add(this.avatarInvView);
        this.containerPanel.add(this.entityInvView);
        //this.containerPanel.setBackground(new Color(1f,1f,1f,0.8f));
        /* end old layout code */
        /* begin new layout edit */
        //this.inventoryView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*leftPanelProportion)), this.getCharacterViewHeight()));
        //this.equipmentView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*centerPanelProportion)), this.getCharacterViewHeight()));
        //this.statsView.setPreferredSize(new Dimension(((int)(this.getCharacterViewWidth()*rightPanelProportion)), this.getCharacterViewHeight()));
        //this.containerPanel.setLayout(new BorderLayout());
        //this.containerPanel.add(this.inventoryView, BorderLayout.WEST);
        //this.containerPanel.add(this.equipmentView,BorderLayout.CENTER);
        //this.containerPanel.add(this.statsView, BorderLayout.EAST);
        /* end new layout edit */
        this.add(pushPanel);
        this.add(containerPanel);
        //this.setBorder(new LineBorder(Color.WHITE));
        this.containerPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(15.0f)));
    }

    public InventoryView getAvatarInvView() {
        return avatarInvView;
    }

    public void setAvatarInvView(GridAvatarInvViewTrading avatarInvView) {
        this.avatarInvView = avatarInvView;
    }

    public GridEntityInvViewTrading getEntityInvView() {
        return entityInvView;
    }

    public void setEntityInvView(GridEntityInvViewTrading entityInvView) {
        this.entityInvView = entityInvView;
    }

    public View getContainerPanel() {
        return containerPanel;
    }

    public void setContainerPanel(View containerPanel) {
        this.containerPanel = containerPanel;
    }
}
