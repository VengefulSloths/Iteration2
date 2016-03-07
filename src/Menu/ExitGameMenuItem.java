package Menu;

import com.vengeful.sloths.AreaView.ViewEngine;
import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;

/**
 * Created by zach on 3/7/16.
 */
public class ExitGameMenuItem extends MainMenuItem {

    public ExitGameMenuItem(){
        super("Exit Game");
    }
    @Override
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.exitGame();
    }

    private void exitGame() {
        System.out.println("exiting game!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.exit(0);
    }
}

