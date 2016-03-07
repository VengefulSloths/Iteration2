package Menu;


import javax.swing.*;
import java.awt.*;

public class MenuTester {

    public static void main(String[] args) {
	// write your code here
        JFrame window = new JFrame("Test Window");

        //make test list
        ScrollableMenuList list = new ScrollableMenuList();

        list.addItem(new InventoryItem("cat"));
        list.addItem(new InventoryItem("cat"));
        list.addItem(new InventoryItem("cat"));
        list.addItem(new InventoryItem("catsdfsfdsfdsfd"));
        ScrollableMenu menu = new ScrollableMenu(50, list);
        menu.setPreferredSize(new Dimension(80, 600));
        menu.setBackground(Color.green);
        menu.setBorder(BorderFactory.createBevelBorder(1, Color.ORANGE, Color.ORANGE));
        window.add(menu);
        menu.add(new Button());
        window.setTitle("test");
        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        try {
            Thread.sleep(3000);
        }catch(Exception e){
            //do nothing
        }
        menu.down();
        window.repaint();
        try {
            Thread.sleep(3000);
        }catch(Exception e){
            //do nothing
        }
        menu.down();
        window.repaint();
        try {
            Thread.sleep(3000);
        }catch(Exception e){
            //do nothing
        }
        menu.down();
        window.repaint();
    }
}
