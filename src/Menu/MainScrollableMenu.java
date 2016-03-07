package Menu;

/**
 * Created by John on 3/7/2016.
 */
public class MainScrollableMenu extends ScrollableMenu {

    public MainScrollableMenu(int height){
        super(height);
        //create list here for main menu
        ScrollableMenuList list = new ScrollableMenuList();
        list.addItem(new MainMenuItem("New Game"));
        list.addItem(new MainMenuItem("Load Game"));
        list.addItem(new MainMenuItem("Exit"));
        this.setList(list);
    }
}
