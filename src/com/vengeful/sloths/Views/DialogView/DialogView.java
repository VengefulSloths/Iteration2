package com.vengeful.sloths.Views.DialogView;

import com.vengeful.sloths.AreaView.DynamicImages.DynamicImage;
import com.vengeful.sloths.AreaView.DynamicImages.DynamicImageFactory;
import com.vengeful.sloths.Models.DialogueTrade.DialogObserver;
import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Utility.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class DialogView extends JPanel implements DialogObserver{

    private DynamicImage dialogBox;
    private String[] endDialog;
    private String currentDialog = "";
    private int index = 0;
    private Font font = new Font("verdana", 0, 16);

    public DialogView() {
        this.dialogBox = DynamicImageFactory.getInstance().loadDynamicImage("resources/chat/chat.xml");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(dialogBox.getImage(), dialogBox.getXOffset() + Config.getAreaViewWidth()/2, dialogBox.getYOffset() + Config.getAreaViewHeight()-200, this);

        //Draw the dialog
        g.setColor(new Color(0x2e2b25));
        g.setFont(font);
        ArrayList<String> lines = StringUtils.wrap(currentDialog, g.getFontMetrics(font), 650);
        for (int i = 0; i<lines.size(); i++) {
            g.drawString(lines.get(i),
                    Config.getAreaViewWidth()/2-325,
                    Config.getAreaViewHeight()-300 + i*22);
        }

        if (index < endDialog.length) {
            currentDialog += endDialog[index++] + " ";
        }


    }

    @Override
    public void alertDialogChange(String dialog) {
        this.currentDialog = "";
        this.endDialog = dialog.split(" ");
        this.index = 0;
    }

}
