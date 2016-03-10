package com.vengeful.sloths.Views.HUDView;

import com.vengeful.sloths.Views.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class HUDView extends View {

    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;

    private double leftPanelProportion = 0.25;
    private double rightPanelProportion = 0.20;
    private double centerPanelProportion = 1.0 - leftPanelProportion - rightPanelProportion;

    public HUDView() {

    }

    public HUDView(int viewWidth, int viewHeight) {

        super(viewWidth,viewHeight);
        this.setPreferredSize(new Dimension(this.getViewWidth(), this.getViewHeight()));
        initHUDPanel(this.getViewWidth(), this.getViewHeight());
        //initHUDPanel(this.getWidth(), this.getHeight());
        this.setBackground(new Color(0f,0f,0f,0f));

/*
        this.setPreferredSize(new Dimension(viewWidth, viewWidth));
        initHUDPanel(viewWidth, viewHeight);
        */
        this.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));

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
        this.leftPanel.setPreferredSize(new Dimension(leftPanelWidth, viewHeight));
        JLabel nameLabel = generateTitleLabel("Smasher");
        this.leftPanel.add(nameLabel);
        JLabel levelLabel = new JLabel("Level: ");
        Font font = new Font(levelLabel.getFont().getName(), Font.BOLD, 14);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(font);
        this.leftPanel.add(generateCharacterImageLabel("resources/sneakImage.jpg", leftPanelWidth, viewHeight)); //EDIT
        this.leftPanel.add(levelLabel);
        //this.leftPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
    }


    public void initCenterPanel(int viewWidth, int viewHeight) {

        int centerSubPanelWidth = (int) centerPanelProportion * viewWidth;

        this.centerPanel = new JPanel();
        this.centerPanel.setPreferredSize(new Dimension(centerSubPanelWidth, viewHeight));
        //this.centerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.centerPanel.setBackground(new Color(0f,0f,0f,0f));

        this.centerPanel.setLayout(new GridLayout(4,1,0,0));

        JPanel titlePanel = new JPanel();
        //titlePanel.add(generateTitleLabel("Character status"));
        titlePanel.add(generateTitleLabel("Vengeful Sloths")); //EDIT
        titlePanel.setBackground(new Color(0f,0f,0f,0f));
        //titlePanel.setPreferredSize(new Dimension(centerSubPanelWidth, (int)(0.05*subPanelHeight)));
        this.centerPanel.add(titlePanel);

        JPanel healthPanel = new JPanel();
        healthPanel.setBackground(new Color(0f,0f,0f,0f));
        JLabel healthLabel = new JLabel("     Health: ");
        Font font = new Font(healthLabel.getFont().getName(), Font.BOLD, 12);
        healthLabel.setForeground(Color.WHITE);
        healthLabel.setFont(font);
        //healthPanel.setPreferredSize(new Dimension((int) (centerSubPanelWidth), (int) (0.15*subPanelHeight)));
        JProgressBar healthBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        //healthBar.setBorder(new LineBorder(Color.black));
        healthBar.setValue(100);
        healthBar.setBackground(Color.DARK_GRAY);
        healthBar.setStringPainted(true);
        healthBar.setForeground(Color.GREEN);
       // healthBar.setPreferredSize(new Dimension((int) (0.6*centerSubPanelWidth), (int) (0.12*viewHeight)));
        healthPanel.add(healthLabel);
        healthPanel.add(healthBar);
        this.centerPanel.add(healthPanel);

        JPanel mannaPanel = new JPanel();
        JLabel mannaLabel = new JLabel("      Mana: ");
        mannaLabel.setForeground(Color.WHITE);
        mannaLabel.setFont(font);

        mannaPanel.setBackground(new Color(0f,0f,0f,0f));

        JProgressBar mannaBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        mannaBar.setValue(100);
        mannaBar.setBackground(Color.DARK_GRAY);
        mannaBar.setStringPainted(true);
        mannaBar.setForeground(Color.BLUE);
        //mannaBar.setPreferredSize(new Dimension((int) (0.6*centerSubPanelWidth), (int) (0.12*viewHeight)));
        mannaPanel.add(mannaLabel);
        mannaPanel.add(mannaBar);
        this.centerPanel.add(mannaPanel);

        JPanel xpPanel = new JPanel();
        JLabel xpLabel = new JLabel("XP Points:");
        xpLabel.setForeground(Color.WHITE);
        xpLabel.setFont(font);
        xpPanel.setBackground(new Color(0f,0f,0f,0f));
        JProgressBar xpBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        //xpBar.setValue(0);
        xpBar.setBackground(Color.DARK_GRAY);
        xpBar.setStringPainted(true);
        xpBar.setForeground(Color.ORANGE);
        //xpBar.setPreferredSize(new Dimension((int) (0.6*centerSubPanelWidth), (int) (0.12*viewHeight)));
        xpPanel.add(xpLabel);
        xpPanel.add(xpBar);
        this.centerPanel.add(xpPanel);

    }

    public void initRightPanel(int viewWidth, int viewHeight) {

        int rightPanelWidth = (int) (rightPanelProportion*viewWidth);

        int livesRemaining = 3;
        this.rightPanel = new JPanel();
        this.rightPanel.setPreferredSize(new Dimension(rightPanelWidth, viewHeight));
        //this.rightPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
        this.rightPanel.setBackground(new Color(0f,0f,0f,0f));
        //this.rightPanel.setLayout(new GridLayout(4,1,0,0));
        //this.rightPanel.add(generateTitleLabel("Lives remaining"));
        JPanel livesPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        titlePanel.add(generateTitleLabel("Lives"));
        titlePanel.setBackground(new Color(0f,0f,0f,0f));
        titlePanel.setPreferredSize(new Dimension(rightPanelWidth,(int)(0.20*viewHeight))); //adjust the height if want to push the hearts down further
        this.rightPanel.add(titlePanel);
        livesPanel.setLayout(new GridLayout(3,1,0,10));
        livesPanel.setBackground(new Color(0f,0f,0f,0f));
        //livesPanel.setPreferredSize(new Dimension(rightPanelWidth,(int)(0.8*subPanelHeight))); //used if want hearts aligned horizontally
        //livesPanel.setLayout(new GridLayout(3,1)); //
        //if (livesRemaining>0) {


        for (int i = 0; i < livesRemaining; i++) {
            JLabel picLabel = generateLivesImageLabel("resources/lives.png", rightPanelWidth, viewHeight);
            livesPanel.add(picLabel);
            //this.rightPanel.add(generateLivesImageLabel(livesImageFileName)); //used if want hearts aligned horizontally
        }

        //}
        this.rightPanel.add(livesPanel);
    }


    public JLabel generateCharacterImageLabel(String imageFileName, int viewWidth, int viewHeight) {
        ImageIcon characterImageIcon = new ImageIcon(imageFileName);
        Image characterImage = characterImageIcon.getImage();
        characterImage=characterImage.getScaledInstance((int)(0.90*viewWidth),(int)(.50*viewHeight), Image. SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(characterImage));
        picLabel.setBorder(new LineBorder(Color.BLACK,3));
        return picLabel;
    }

    public JLabel generateLivesImageLabel(String imageFileName, int viewWidth, int viewHeight) {
        ImageIcon livesImageIcon = new ImageIcon(imageFileName);
        Image livesImage = livesImageIcon.getImage();
        livesImage=livesImage.getScaledInstance((int)(0.40*viewWidth),(int)(0.15*viewHeight), Image. SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(livesImage));
        return picLabel;
    }


    public JLabel generateTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title);
        Font font = new Font(titleLabel.getFont().getName(), Font.BOLD, 18);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(font);
        //titleLabel.setBorder(new BevelBorder(BevelBorder.RAISED,Color.GRAY, Color.BLACK));
        return titleLabel;
    }


}
