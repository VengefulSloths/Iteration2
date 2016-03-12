package com.vengeful.sloths.Views.HUDView;

import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Observers.StatsObserver;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class HUDView extends View implements StatsObserver {

    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;

    private CustomProgressBar healthBar;
    private CustomProgressBar manaBar;
    private CustomProgressBar xpBar;
    private JLabel levelLabel;
    private LivesView livesView;

    private double leftPanelProportion = 0.25;
    private double rightPanelProportion = 0.20;
    private double centerPanelProportion = 1.0 - leftPanelProportion - rightPanelProportion;

    public HUDView() {

    }

    public HUDView(int viewWidth, int viewHeight) {

        super(viewWidth,viewHeight);
        Avatar.getInstance().getStats().registerObserver(this);
        this.setPreferredSize(new Dimension(this.getViewWidth(), this.getViewHeight()));
        initHUDPanel(this.getViewWidth(), this.getViewHeight());
        //initHUDPanel(this.getWidth(), this.getHeight());
        this.setBackground(new Color(0f,0f,0f,0f));

/*
        this.setPreferredSize(new Dimension(viewWidth, viewWidth));
        initHUDPanel(viewWidth, viewHeight);
        */
        //this.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        alertStatChanged(Avatar.getInstance().getStats());
    }


    public void initHUDPanel(int viewWidth, int viewHeight) {

        this.setLayout(new BorderLayout());
        initLeftPanel(viewWidth, viewHeight);
        initCenterPanel(viewWidth, viewHeight);
        initRightPanel(viewWidth, viewHeight);
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
        this.add(this.rightPanel, BorderLayout.EAST);
    }

    public void initLeftPanel(int viewWidth, int viewHeight) {


        int leftPanelWidth = (int) (leftPanelProportion*viewWidth);

        this.leftPanel = new JPanel();
        this.leftPanel.setBackground(new Color(0f,0f,0f,0f));
        this.leftPanel.setPreferredSize(new Dimension(120, viewHeight));
        JLabel nameLabel = generateTitleLabel("Smasher");
        this.leftPanel.add(nameLabel);
        this.levelLabel = new JLabel("Level: ");
        Font font = new Font(levelLabel.getFont().getName(), Font.BOLD, 14);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(font);
        this.leftPanel.add(generateCharacterImageLabel("resources/smasher_icon_64.png", leftPanelWidth, viewHeight)); //EDIT
        this.leftPanel.add(levelLabel);
        //this.leftPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
    }


    public void initCenterPanel(int viewWidth, int viewHeight) {

        int centerSubPanelWidth = (int) centerPanelProportion * viewWidth;

        this.centerPanel = new JPanel();
        this.centerPanel.setPreferredSize(new Dimension(100, viewHeight));
        //this.centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.centerPanel.setBackground(new Color(0f,0f,0f,0f));

        this.centerPanel.setLayout(new GridLayout(4,1,0,0));

        JPanel titlePanel = new JPanel();
        titlePanel.add(generateTitleLabel("Vengeful Sloths")); //EDIT
        titlePanel.setBackground(new Color(0f,0f,0f,0f));
        this.centerPanel.add(titlePanel);

        JPanel healthPanel = new JPanel();
        healthPanel.setBackground(new Color(0f,0f,0f,0f));
        JLabel healthLabel = new JLabel("      Health: ");
        Font font = new Font(healthLabel.getFont().getName(), Font.BOLD, 12);
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setFont(font);
        //healthPanel.setPreferredSize(new Dimension((int) (centerSubPanelWidth), (int) (0.15*subPanelHeight)));
        this.healthBar = new CustomProgressBar(100, 20);
        healthBar.setMaxProgress(100);
        healthBar.setCurrentProgress(50);
        healthBar.setBorderWidth(5);
        healthBar.setDepletedColor(Color.LIGHT_GRAY);
       // healthBar.setPreferredSize(new Dimension((int) (0.6*centerSubPanelWidth), (int) (0.12*viewHeight)));
        healthPanel.add(healthLabel);
        healthPanel.add(healthBar);
        this.centerPanel.add(healthPanel);

        JPanel manaPanel = new JPanel();
        JLabel manaLabel = new JLabel("       Mana: ");
        manaLabel.setForeground(Color.WHITE);
        manaLabel.setFont(font);

        manaPanel.setBackground(new Color(0f,0f,0f,0f));

        this.manaBar = new CustomProgressBar(100, 20);
        manaBar.setMaxProgress(100);
        manaBar.setCurrentProgress(50);
        manaBar.setBorderWidth(5);
        manaBar.setCurrentColor(Color.CYAN);
        manaBar.setDepletedColor(Color.LIGHT_GRAY);
        //mannaBar.setPreferredSize(new Dimension((int) (0.6*centerSubPanelWidth), (int) (0.12*viewHeight)));
        manaPanel.add(manaLabel);
        manaPanel.add(manaBar);
        this.centerPanel.add(manaPanel);

        JPanel xpPanel = new JPanel();
        JLabel xpLabel = new JLabel("XP Points:");
        xpLabel.setForeground(Color.WHITE);
        xpLabel.setFont(font);
        xpPanel.setBackground(new Color(0f,0f,0f,0f));
        this.xpBar = new CustomProgressBar(100, 20);
        xpBar.setMaxProgress(100);
        xpBar.setCurrentProgress(50);
        xpBar.setBorderWidth(5);
        xpBar.setCurrentColor(Color.ORANGE);
        xpBar.setDepletedColor(Color.LIGHT_GRAY);
        xpPanel.add(xpLabel);
        xpPanel.add(xpBar);
        this.centerPanel.add(xpPanel);

    }

    public void initRightPanel(int viewWidth, int viewHeight) {

        int rightPanelWidth = (int) (rightPanelProportion*viewWidth);

       // int livesRemaining = 3;
        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(64, viewHeight));
        //this.rightPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.rightPanel.setBackground(new Color(0f,0f,0f,0f));
        //this.rightPanel.setLayout(new GridLayout(4,1,0,0));
        //this.rightPanel.add(generateTitleLabel("Lives remaining"));
        //JPanel livesPanel = new JPanel();
//        JPanel titlePanel = new JPanel();
//        titlePanel.add(generateTitleLabel("Lives"));
//        titlePanel.setBackground(new Color(0f,0f,0f,0f));
//        titlePanel.setPreferredSize(new Dimension(rightPanelWidth,(int)(0.18*viewHeight))); //adjust the height if want to push the hearts down further
//        this.rightPanel.add(titlePanel);
        //livesPanel.setLayout(new GridLayout(3,1,0,10));
        //livesPanel.setBackground(new Color(0f,0f,0f,0f));

        //livesPanel.setPreferredSize(new Dimension(rightPanelWidth,(int)(0.8*subPanelHeight))); //used if want hearts aligned horizontally
        //livesPanel.setLayout(new GridLayout(3,1)); //
        //if (livesRemaining>0) {


//        for (int i = 0; i < livesRemaining; i++) {
//            JLabel picLabel = generateLivesImageLabel("resources/lives.png", rightPanelWidth, viewHeight);
//            livesPanel.add(picLabel);
//            //this.rightPanel.add(generateLivesImageLabel(livesImageFileName)); //used if want hearts aligned horizontally
//        }
        this.livesView = new LivesView();
        //ivesPanel.add(this.livesView);
        //}
        this.rightPanel.add(livesView);
        //this.rightPanel.setBackground(new Color(0f,0f,0f,0f));
    }


    public static JLabel generateCharacterImageLabel(String imageFileName, int viewWidth, int viewHeight) {
        ImageIcon characterImageIcon = new ImageIcon(imageFileName);
        Image characterImage = characterImageIcon.getImage();
        characterImage=characterImage.getScaledInstance((int)(0.90*viewWidth),(int)(.50*viewHeight), Image. SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(characterImage));
        picLabel.setBorder(new LineBorder(Color.BLACK,3));
        return picLabel;
    }

    public static JLabel generateLivesImageLabel(String imageFileName, int viewWidth, int viewHeight) {
        ImageIcon livesImageIcon = new ImageIcon(imageFileName);
        Image livesImage = livesImageIcon.getImage();
        livesImage=livesImage.getScaledInstance((int)(0.40*viewWidth),(int)(0.15*viewHeight), Image. SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(livesImage));
        return picLabel;
    }


    public static JLabel generateTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 18);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(font);
        //titleLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.GRAY, Color.BLACK));
        return titleLabel;
    }

    @Override
    public void alertStatChanged(Stats stats) {
        //update health
        healthBar.setCurrentProgress(stats.getCurrentHealth());
        healthBar.setMaxProgress(stats.getMaxHealth());
        //update mana
        manaBar.setCurrentProgress(stats.getCurrentMana());
        manaBar.setMaxProgress(stats.getMaxMana());
        //update xp
        xpBar.setCurrentProgress(stats.getCurrentExperience());
        xpBar.setMaxProgress(stats.getMaxExperience());

        //set level
        levelLabel.setText("Level: " + stats.getLevel());

        //set lives
        livesView.setNumLives(stats.getLives());


    }
}
