package Menu;

import java.awt.*;

/**
 * Created by John on 2/16/2016.
 */
public interface ScrollableMenuItem {
    public abstract void select(ScrollableMenuItemCommand command); //just need to call command.execute()
    public abstract void paintComponent(Graphics2D g2d, int xPos, int yPos, int width, int height);
}
