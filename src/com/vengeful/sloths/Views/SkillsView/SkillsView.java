package com.vengeful.sloths.Views.SkillsView;

import com.vengeful.sloths.Models.ObserverManager;
import com.vengeful.sloths.Models.Observers.*;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Views.AbilitiesView.AbilityViewObject;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zach on 3/12/16.
 */
public class SkillsView extends View implements SkillManagerObserver {
    // Handle selection
    private int viewIndex = 0;
    private int numRows;
    private int numCols;

    private JPanel titlePanel = new JPanel();
    private JPanel skillsPanel = new JPanel();
    private SkillManager skillManager;
    private ArrayList<SkillsViewObject> skillsList;
    private int skillPoints;
    public JPanel getTitlePanel() {
        return titlePanel;
    }
    public JPanel getSkillsPanel() {
        return skillsPanel;
    }

    public int getNumCols() {
        return numCols;
    }
    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
    public int getNumRows() {
        return numRows;
    }
    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getItemListSize() {
        return this.skillsList.size();
    }

    public SkillsViewObject getFromItemList(int index) {
        try {
            return this.skillsList.get(index);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void decrementViewIndex(int val) {
        SkillsViewObject item = this.getFromItemList(this.viewIndex);


        if (item != null)
            this.setDeselected(this.getFromItemList(this.viewIndex));
        if (this.viewIndex - val <= 0) {
            this.viewIndex = 0;
        } else
            this.viewIndex -= val;


        item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.viewIndex));
    }

    public void incrementViewIndex(int val) {
        SkillsViewObject item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setDeselected(this.getFromItemList(this.viewIndex));


        if (this.viewIndex + val >= this.skillsList.size()) {
            this.viewIndex = this.skillsList.size() - 1;
        } else
            this.viewIndex += val;

        item = this.getFromItemList(this.viewIndex);
        if (item != null)
            this.setSelected(this.getFromItemList(this.viewIndex));

    }

    public int selectNorthItem() {
        decrementViewIndex(1);

        return viewIndex;
    }

    public int selectSouthItem() {
        incrementViewIndex(1);

        return viewIndex;
    }

    public void resetView(boolean isActiveView) {
        if (this.skillsList.size() > 0) {

            SkillsViewObject item = this.getFromItemList(this.viewIndex);

            if (item != null)
                this.setDeselected(this.getFromItemList(this.viewIndex));

            this.viewIndex = 0;

            item = this.getFromItemList(this.viewIndex);
            if (item != null && isActiveView)
                this.setSelected(this.getFromItemList(this.viewIndex));
        }
    }


    public void setSelected(SkillsViewObject item){
        //give a border
        item.setSelected(true);
    }

    public void setDeselected(SkillsViewObject item){
        //give a border
        item.setSelected(false);
    }

    public void setSkillManager(SkillManager skillManager) {
        this.skillManager = skillManager;
        SkillsViewObjectFactory factory = new SkillsViewObjectFactory();
        this.skillsList = factory.generateSkillsViewObjects(skillManager);
    }

    public SkillsView(SkillManager skillManager) {
        this.skillManager = skillManager;
        ProxyObserver pio = new ProxySkillManagerObserver(this, skillManager);
        ObserverManager.getInstance().addProxyObserver(pio);

        SkillsViewObjectFactory factory = new SkillsViewObjectFactory();
        this.skillsList = factory.generateSkillsViewObjects(skillManager);

        initDefaultUI();
    }

    public SkillsView() {
        initDefaultUI();
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void initDefaultUI() {

        JLabel title = generateTitleLabel("Skills", 22, Color.WHITE);
        //titlePanel = new JPanel();
        //skillsPanel = new JPanel();

        //this.setBackgroundImageFileName("resources/skyInventory2.png");
        this.setBackground(new Color(0f,0f,0f,0.3f));
        this.titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.skillsPanel.setBackground(new Color(0f,0f,0f,0f));

        GridLayout grid = new GridLayout(15,1);
        this.setNumCols(1);
        this.setNumRows(10);
        grid.setVgap(0);

        this.setLayout(new BorderLayout());
        this.titlePanel.setLayout(new BorderLayout());
        this.skillsPanel.setLayout(grid);
        //this.skillsPanel.setLayout(new FlowLayout());
        this.titlePanel.setPreferredSize(new Dimension(this.getWidth(), 50));

        //this.titlePanel.setBorder(new LineBorder(Color.BLACK));
        //this.setBorder(new LineBorder(Color.WHITE));
        this.titlePanel.add(title, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(skillsPanel, BorderLayout.CENTER);
        //populateView(this.statsList);

    }

    public void addSkill(SkillsViewObject skill) {
        this.skillsPanel.add(skill);
    }

    public void populateView(ArrayList<SkillsViewObject> skillsList) {
        for(SkillsViewObject s: skillsList)
            this.skillsPanel.add(s);
        this.skillsPanel.revalidate();
        this.skillsPanel.repaint();
    }

    public void emptyView() {
        for(SkillsViewObject s: skillsList)
            this.skillsPanel.remove(s);
        this.skillsPanel.revalidate();
        this.skillsPanel.repaint();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = this.getWidth()/2;
        int y, titleYOffset;
        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);

        int stringHeight = g.getFontMetrics().getHeight();

        titleYOffset = this.getTitlePanel().getHeight() + stringHeight + this.getSkillsPanel().getWidth()/10;
        y = titleYOffset;

        for (int i=0; i < this.skillsList.size(); i++) {

            if (this.skillsList.get(i).isSelected()) {
                Border b = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN);
                this.skillsList.get(i).paintComponent(g,x,y);
                b.paintBorder(this.skillsList.get(i), g, 0, (y - stringHeight - 4) , this.getWidth(), stringHeight + 10 );
                g.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
                g.setColor(Color.WHITE);
            } else {
                this.skillsList.get(i).paintComponent(g,x,y);
            }

            g.setFont(font);
            g.setColor(Color.WHITE);

            y += calculateYCoordBasedOnIndex(i, y, stringHeight);
        }

        int stringWidth = g.getFontMetrics().stringWidth("Skill Points:" + this.skillManager.getAvailableSkillPoints());
        g.drawString("Skill Points: " + this.skillManager.getAvailableSkillPoints(), x - stringWidth/2, y);
    }

    public int calculateSlotHeight() {
        int slotHeight = (int)((this.getSkillsPanel().getHeight()) * (1.0/((3*getNumRows())+1)));
        return slotHeight;
    }

    public int calculateYCoordBasedOnIndex(int index, int y, int stringHeight) {
        int rowSlotIndex = index;
        int multipleY = 1;
        int slotHeight = calculateSlotHeight();
        int lineLength = slotHeight/2;
        int yCoord = ((slotHeight/2) * multipleY) + getTitlePanel().getHeight();
        return yCoord;
    }

    public void useSkillPoint() {
        if (this.skillManager.getAvailableSkillPoints() <= 0) return;

        this.skillManager.updateSkillLevel(this.getFromItemList(this.viewIndex).getSkill(), 1);
    }

    @Override
    public void alertSkillAdded(Skill skill) {
        SkillsViewObjectFactory factory = new SkillsViewObjectFactory();
        this.skillsList = factory.generateSkillsViewObjects(skillManager);
    }

    @Override
    public void alertSkillLevelUpdated(Skill skill) {

        SkillsViewObjectFactory factory = new SkillsViewObjectFactory();
        this.skillsList = factory.generateSkillsViewObjects(skillManager);
        this.getFromItemList(this.viewIndex).setSelected(true);
    }

    @Override
    public void alertAvailableSkillPointsUpdated(int availableSkillPoints) {
        this.setSkillPoints(availableSkillPoints);
    }
}
