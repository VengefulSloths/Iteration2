package Menu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 2/16/2016.
 */
public class ScrollableMenuList {

    private List<ScrollableMenuItem> list;
    private int currentIndex = 0;

    public ScrollableMenuList(){
        list = new LinkedList<>();
    }


    //Public api
    public void select(ScrollableMenuItemCommand command){
        list.get(currentIndex).select(command);
    }

    public void addItem(ScrollableMenuItem item){
        list.add(item);
    }

    public void removeItem(ScrollableMenuItem item){
        list.remove(item);
    }

    public void addItemToIndex(ScrollableMenuItem item, int index){
        list.add(index, item);
    }

    public void removeItemFromIndex(int index){
        list.remove(index);
    }

    public Iterator<ScrollableMenuItem> getIterator(){
        return list.iterator(); //idk about this
    }
    public int getCurrentIndex(){
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if (currentIndex < 0) return;
        if (currentIndex >= list.size()) return;
        
        this.currentIndex = currentIndex;
    }
}
