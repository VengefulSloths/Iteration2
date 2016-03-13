package com.vengeful.sloths.Views.SkillsView;

import com.vengeful.sloths.Models.Skills.Skill;
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
    private String name;
    private String value;
    private Skill skill;

    private TextLabelFactory labelFactory = new TextLabelFactory();
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() { return this.name; }

    public Skill getSkill() { return this.skill; }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public SkillsViewObject(Skill skill){
        this.skill = skill;
        this.name = skill.getName();
        this.value = String.valueOf(this.skill.getLevel());
    }

    public void paintComponent(Graphics2D g2d) {
        super.paintComponent(g2d);

    }

    public void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        String skillString = this.getName() + ": " + this.getValue();
//        int stringWidth = g.getFontMetrics().stringWidth(skillString);
        g.drawString(skillString, 30, y-5);
    }


}
