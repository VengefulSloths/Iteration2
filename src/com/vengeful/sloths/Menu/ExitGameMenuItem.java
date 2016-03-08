package com.vengeful.sloths.Menu;

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

