package com.vengeful.sloths.Views.SkillsView;

import com.vengeful.sloths.Views.ViewFactory.TextLabelFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zach on 3/12/16.
 */
public class SkillsViewObject extends JPanel {

    JLabel skillName;
    JLabel skillValue;
    private JPanel statsObjectPanel;
    private String skill;
    private String value;
    private TextLabelFactory labelFactory = new TextLabelFactory();
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getSkill() {
        return skill;
    }
    public void setSkill(String stat) {
        this.skill = skill;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public SkillsViewObject(String skill, int value){
        this.skill = skill;
        this.value = String.valueOf(value);
    }



    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);

    }

    public void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        String skillString = this.getSkill() + ": " + this.getValue();
        int stringWidth = g.getFontMetrics().stringWidth(skillString);
        g.drawString(skillString, x - stringWidth/2, y);

    }


}
