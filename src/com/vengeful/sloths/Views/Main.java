import com.vengeful.sloths.Utility.Config;
import com.vengeful.sloths.Views.*;
import com.vengeful.sloths.Views.AreaView.AreaView;
import com.vengeful.sloths.Views.CharacterView.CharacterView;
import com.vengeful.sloths.Views.EquipmentView.EquipmentView;
import com.vengeful.sloths.Views.HUDView.HUDView;
import com.vengeful.sloths.Views.InventoryView.InventoryView;
import com.vengeful.sloths.Views.StatsView.StatsView;
import com.vengeful.sloths.Views.ViewManager.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by echristiansen on 2/21/2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        /* characterviewmanager holds the views for the character's stats, inventory, and equipment */
        //CharacterView cvm = new CharacterView();

        /* vm is the ViewManager for the AreaView and HUDView
        * It is what is displaying most of the time */
        ViewManager vm = new ViewManager();
        /* MainView is a JPanel meant to contain all of the other views. It is simply a container so that the view
        system is extensible in that it can simply be added to whatever JFrame is actually being used in the game
         */
        MainView mv = new MainView();

        JFrame testFrame = new JFrame();
        testFrame.add(mv); //add the mainview panel to the frame
        testFrame.pack(); //this is necessary so that the JFrame resizes accoridng to the components it contains
        testFrame.setLocationRelativeTo(null); //center the frame on the screen
        testFrame.setVisible(true); //make the frame visible

        /* testing add and remove of the viewmanager and characterviewmanager displays */
        mv.add(vm); //add the viewManager to the mainview frame
        Thread.sleep(2000);
        //mv.remove(vm); //remove the viewmanager from the mainview frame
        //mv.add(cvm); //add the characterviewmanager to the mainview frame
        //mv.add(cvm,0);
        mv.revalidate();
        mv.repaint();

        /*
        Thread.sleep(3000);
        mv.remove(cvm); //remove the characterviewmanager from the mainview frame
        //mv.add(vm); //add the viewmanager to the mainview frame
        mv.revalidate();
        mv.repaint();
*/
    }

}