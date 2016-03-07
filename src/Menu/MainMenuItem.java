package Menu;

import java.awt.*;

/**
 * Created by John on 2/18/2016.
 */
public class MainMenuItem implements ScrollableMenuItem {

    private String name;

    public MainMenuItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void select(ScrollableMenuItemCommand command) {
        //do something
        command.execute();
    }

    @Override
    public void paintComponent(Graphics2D g2d, int xPos, int yPos, int width, int height) {
        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        g2d.drawString(this.name, width / 2 - metrics.stringWidth(this.name)/2, yPos + height/2 + metrics.getAscent()/2);
    }
}
