package com.vengeful.sloths.Menu.CharacterCreation;

import com.vengeful.sloths.Utility.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by John on 3/12/2016.
 */
public class CharacterCreationView extends JComponent {

    private BufferedImage bg;

    private Image buttonUp = (new ImageIcon("resources/key_icon_xl.png")).getImage();
    private Image buttonDown = (new ImageIcon("resources/key_pressed_icon_xl.png")).getImage();

    private Image smasherIcon = (new ImageIcon("resources/smasher_icon_64.png")).getImage();
    private Image sneakIcon = (new ImageIcon("resources/sneak_icon.png")).getImage();
    private Image summonerIcon = (new ImageIcon("resources/wizard_icon.png")).getImage();

    private Image arrowRight = (new ImageIcon("resources/arrow_right_icon_xl.png")).getImage();
    private Image arrowLeft = (new ImageIcon("resources/arrow_left_icon_xl.png")).getImage();

    private int currentIndex = 0;
    private int xPadding = 0;
    private int yPadding = 0;

    int drawRightPressed = 0;
    int drawLeftPressed = 0;

    public CharacterCreationView(){
        try {
            bg = ImageIO.read(new File("resources/backgrounds/sky_main_title.png"));
        }catch (Exception e){
            System.out.println("EXCEPTION");
            System.out.println(e);
        }
    }

    public CharacterCreationView(int xPadding, int yPadding){
        this();
        this.xPadding = xPadding;
        this.yPadding = yPadding;
    }

    @Override
    public void paintComponent(Graphics g){
        //System.out.println("blooop");
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bg,0,0, Config.instance().getWindowWidth(), Config.instance().getWindowHeight(), null);

        int horizontal_offset = xPadding;
        int vertical_offset = yPadding;

        g2d.setFont(new Font("Helvetica",1,80));
        g2d.setColor(Color.GRAY);
        g2d.drawString("Character Creation", horizontal_offset - 180, vertical_offset);
        vertical_offset += 90;
        g2d.setFont(new Font("Helvetica",1,50));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Choose Your Class:", horizontal_offset - 50, vertical_offset);
        vertical_offset+= 40;


        //draw arrows
        if(drawLeftPressed > 0){
            g2d.drawImage(buttonDown, horizontal_offset, vertical_offset + 6, this);
            g2d.drawImage(arrowLeft, horizontal_offset + 32, vertical_offset + 30 + 18, this);
            --drawLeftPressed;
        }else {
            g2d.drawImage(buttonUp, horizontal_offset, vertical_offset, this);
            g2d.drawImage(arrowLeft, horizontal_offset + 32, vertical_offset + 30, this);
        }

        horizontal_offset += 128;
        g2d.setColor(Color.BLACK);
        FontMetrics metrics = g2d.getFontMetrics();


        switch (this.currentIndex){
            case 0: //draw smasher
                g2d.drawImage(smasherIcon,horizontal_offset + 32,vertical_offset + 32,this);
                g2d.drawString("Smasher", horizontal_offset + 64 - metrics.stringWidth("Smasher")/2, vertical_offset + 180);
                break;
            case 1: //draw summoner
                g2d.drawImage(summonerIcon,horizontal_offset + 32,vertical_offset + 32,this);
                g2d.drawString("Summoner", horizontal_offset + 64 - metrics.stringWidth("Summoner")/2, vertical_offset + 180);
                break;
            case 2: //draw sneak
                g2d.drawImage(sneakIcon,horizontal_offset + 32,vertical_offset + 32,this);
                g2d.drawString("Sneak", horizontal_offset + 64 - metrics.stringWidth("Sneak")/2, vertical_offset + 180);
                break;
        }
        horizontal_offset += 128;

        if(drawRightPressed > 0){
            g2d.drawImage(buttonDown, horizontal_offset, vertical_offset + 6, this);
            g2d.drawImage(arrowRight, horizontal_offset + 32, vertical_offset + 30 + 18, this);
            --drawRightPressed;
        }else {
            g2d.drawImage(buttonUp, horizontal_offset, vertical_offset, this);
            g2d.drawImage(arrowRight, horizontal_offset + 32, vertical_offset + 30, this);
        }

        vertical_offset += 256;
        g2d.setColor(Color.GRAY);
        g2d.drawString("Hit Enter to Continue!", horizontal_offset - 64 - metrics.stringWidth("Hit Enter to Continue!")/2, vertical_offset);

    }

    public void right(){
        currentIndex = (currentIndex + 1) % 3;
        drawRightPressed = 8;
        System.out.println(currentIndex);
    }

    public void left(){
        currentIndex = Math.abs((currentIndex*-1 - 1) % 3) ;
        System.out.println(currentIndex);
        drawLeftPressed = 8;
    }

    public String getOccupation() {
        switch (this.currentIndex) {
            case 0: //draw smasher
                return "Smasher";
            case 1: //draw summoner
                return "Summoner";
            case 2: //draw sneak
                return "Sneak";
            default:
                return null;
        }
    }
}
