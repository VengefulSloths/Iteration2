package com.vengeful.sloths.Views.ViewManager;

import com.vengeful.sloths.Controllers.InputController.KeyBindingMenu.InputChangeMenu;
import com.vengeful.sloths.Menu.InGameMenu.InGameScrollableMenu;
import com.vengeful.sloths.Utility.Config;
//import com.vengeful.sloths.Views.AreaView.AreaView;
import com.vengeful.sloths.Views.AbilitiesSkillsView.AbilitiesSkillView;
import com.vengeful.sloths.Views.CharacterView.CharacterView;
import com.vengeful.sloths.Views.HUDView.HUDView;
import com.vengeful.sloths.AreaView.AreaView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;

/**
 * Created by echristiansen on 2/21/2016.
 */

public class ViewManager extends JPanel {

    DefaultViewComposite dvc;
    private CharacterView characterView;
    private AbilitiesSkillView abilitiesSkillView;
    private AreaView areaview;
    private HUDView hudView;
    private InGameScrollableMenu menuView;
    private InputChangeMenu keyBindView;
    JPanel sidePanel;

    protected static final int AREA_VIEW_WIDTH =(int) Config.instance().getAreaViewWidth();
    protected static final int AREA_VIEW_HEIGHT = (int) Config.instance().getAreaViewHeight();
    protected static final int HUD_VIEW_WIDTH = (int) Config.instance().getHUDViewWidth(); //relative to the
    protected static final int HUD_VIEW_HEIGHT = (int) Config.instance().getHUDViewHeight(); //relative to the

    public CharacterView getCharacterView() {
        return characterView;
    }

    public AbilitiesSkillView getAbilitiesSkillView() {
        return abilitiesSkillView;
    }

    public void setAbilitiesSkillView(AbilitiesSkillView abilitiesSkillView) {
        this.abilitiesSkillView = abilitiesSkillView;
    }

    public void setCharacterView(CharacterView characterView) {
        this.characterView = characterView;
    }
    public AreaView getAreaview() {
        return areaview;
    }
    public void setAreaview(AreaView areaview) {
        this.areaview = areaview;
    }
    public HUDView getHudView() {
        return hudView;
    }
    public void setHudView(HUDView hudView) {
        this.hudView = hudView;
    }

    public ViewManager(DefaultViewComposite dvc, CharacterView characterView) {
        this.dvc = dvc;
        this.characterView = characterView;
        menuView = new InGameScrollableMenu(80);
        initializeViewManager();
    }

    //public ViewManager(AreaView av, HUDView hv) {

    public ViewManager(AreaView av, HUDView hv, CharacterView cv)  {
        this.areaview = av;
        this.hudView = hv;
        this.characterView = cv;
        menuView = new InGameScrollableMenu(80);
        this.keyBindView = new InputChangeMenu(40);
        initializeViewManager();
    }

    public void initializeViewManager() {

        //EDIT: make a factory for the views

        /*show the areaview/gameplay behind it since the only real component in it is the hudview at the top*/

        this.setLayout(new BorderLayout());
        //EDIT: make a factory for the views
        //this.areaview = new AreaView(this.AREA_VIEW_WIDTH, this.AREA_VIEW_HEIGHT);
        this.hudView = new HUDView(this.HUD_VIEW_WIDTH, this.HUD_VIEW_HEIGHT);

        this.sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBackground(new Color(0f,0f,0f,0f));

        this.setPreferredSize(new Dimension(AREA_VIEW_WIDTH, AREA_VIEW_HEIGHT));

        this.areaview.setLayout(new BorderLayout());
        this.sidePanel.setLayout(new BorderLayout());

        //setComponentZOrder(areaview, 0);
        //setComponentZOrder(characterView, 1);
        sidePanel.add(hudView, BorderLayout.NORTH);
        areaview.add(sidePanel, BorderLayout.WEST); /* we add the sidePanel to the areaview because it needs to be transparent to
        //show the areaview/gameplay behind it since the only real component in it is the hudview at the top*/
        //this.add(areaview);
        //areaview.add(menuView, BorderLayout.EAST);
        this.add(areaview, BorderLayout.CENTER);
        this.hudView.setBackground(new Color(0f,0f,0f,0.3f));
        this.areaview.setBackground(Color.blue);

    }

    public void openKeyBindView(){
        addView(keyBindView);
    }
    public void closeKeyBindView(){
        remove(keyBindView);
        this.revalidate();
        this.repaint();
    }
    public void openMenuView(){
        addView(menuView);
    }

    public void closeMenuView(){
        remove(menuView);
        this.revalidate();
        this.repaint();
    }

    public void openCharacterView() {
        addView(characterView);
    }

    public void openAbilitiesSkillsView() {
        addView(abilitiesSkillView);
    }

    public void closeAllViews() {
        //TODO: close everything but HUD
    }
    public void closeCharacterView() {
        this.remove(characterView);
        this.revalidate();
        this.repaint();
    }

    public void closeAbilitiesSkillsView() {
        this.remove(abilitiesSkillView);
        this.revalidate();
        this.repaint();
    }

    public void removeView(JComponent component) {
        this.remove(component);
        this.revalidate();
        this.repaint();
    }

    public void addView(JComponent component) {
        this.add(component,0);
        //this.add(component, BorderLayout.CENTER,0);
        //this.add(component, BorderLayout.CENTER);
        //this.areaview.add(component, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }


    public void resizeComponent(JComponent component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
    }

    public InGameScrollableMenu getMenuView() {
        return menuView;
    }

    public InputChangeMenu getKeyBindView() {
        return keyBindView;
    }

    public void setKeyBindView(InputChangeMenu keyBindView) {
        this.keyBindView = keyBindView;
    }

    public void setMenuView(InGameScrollableMenu menuView) {
        this.menuView = menuView;
    }
}