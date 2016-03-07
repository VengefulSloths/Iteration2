package Menu;

import com.vengeful.sloths.GameLaunching.LaunchGameTemplate;
import com.vengeful.sloths.GameLaunching.LaunchNewGame;

/**
 * Created by zach on 3/7/16.
 */
public class NewGameMenuItem extends MainMenuItem {

    public NewGameMenuItem(){
        super("New Game");
    }
    @Override
    public void select(ScrollableMenuItemCommand command) {
        //super.select(command);
        this.startGame();
    }

    private void startGame(){
        System.out.println("launch new game !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        LaunchGameTemplate launcher = new LaunchGameTemplate(new LaunchNewGame());
        launcher.launch();
    }
}
