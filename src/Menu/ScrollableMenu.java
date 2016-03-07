package Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by John on 2/16/2016.
 */
public abstract class ScrollableMenu extends JPanel{

    protected int itemHeight;
    protected ScrollableMenuList list;
    protected int padding = 300;


    public ScrollableMenu(int height){
        this.itemHeight = height;
    }

    public ScrollableMenu(int height, ScrollableMenuList list){
        this.itemHeight = height;
        this.list = list;
    }

    @Override
    protected void paintComponent(Graphics g){
        //System.out.println("painting");
        super.paintComponent(g);
//        int offset = padding;
//        int index = 0;
//        Graphics2D g2d = (Graphics2D) g;
//        Iterator iter = list.getIterator();
//        while (iter.hasNext()) {
//            if(index == list.getCurrentIndex()){
//                System.out.println(list.getCurrentIndex());
//            ScrollableMenuItem current = (ScrollableMenuItem) iter.next();
//            g2d.setColor(new Color(255, 0, 255, 80));
//            g2d.fillRect(padding/2, offset , this.getWidth() - padding, itemHeight);
//            current.paintComponent(g2d, padding , offset + itemHeight/2, this.getWidth(), itemHeight);
//            }else{
//                ScrollableMenuItem current = (ScrollableMenuItem) iter.next();
//                current.paintComponent(g2d, padding, offset + itemHeight/2, this.getWidth(), itemHeight);
//            }
//            offset += itemHeight;
//            index++;
//        }
        Toolkit.getDefaultToolkit().sync();
    }


    // Public Api
    public void setList(ScrollableMenuList list){
        this.list = list;
    }

    public void setItemHeight(int height){
        this.itemHeight = height;
    }

    public void down(){
        //move visual down
        //move list down
        System.out.println("going down");
        this.list.setCurrentIndex(this.list.getCurrentIndex() + 1);
        System.out.println(this.list.getCurrentIndex());
    }

    public void up(){
        this.list.setCurrentIndex(this.list.getCurrentIndex() - 1);
        //move visual up
        //moce list p
    }
    public void select(ScrollableMenuItemCommand command ){
        list.select(command);
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }
}
