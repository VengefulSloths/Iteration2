package com.vengeful.sloths.GameLaunching;

import com.vengeful.sloths.AreaView.CameraView;
import com.vengeful.sloths.AreaView.CameraViewManager;
import com.vengeful.sloths.AreaView.PlainsCameraView;
import com.vengeful.sloths.Models.Entity.Avatar;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Map.MapArea;
import com.vengeful.sloths.Models.Map.Tile;
import com.vengeful.sloths.Models.SaveLoad.Loader;
import com.vengeful.sloths.Utility.Coord;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Ian on 3/7/2016.
 */
public class LaunchSavedGame implements LaunchGameHelper {
    private LevelFactory levelFactory;
    private CameraViewManager cameras;

    public LaunchSavedGame() {
        this.levelFactory = new LevelFactory();
        levelFactory.init("test");
//        this.populateMap();
    }
    @Override
    public void populateMap() {
        //load saved data
        try {
            Loader l = new Loader("save.xml");
            MapArea[] mapAreas = Map.getInstance().getMapAreas();
            l.loadAreas(mapAreas);
//            Avatar.getInstance().getLocation()
            CameraView camera2 = new PlainsCameraView();
            CameraView camera1 = new PlainsCameraView();
            camera2.init(mapAreas[1]);
            camera1.init(mapAreas[0]);
            cameras = new CameraViewManager();
            cameras.addCameraView(mapAreas[1], camera2);
            cameras.addCameraView(mapAreas[0], camera1);

        } catch (ParserConfigurationException e) {
            System.out.println("error with parser when creating loader");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception when trying to load");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("SAX parser error when trying to load");
            e.printStackTrace();
        }

    }
    @Override
    public Map createMap() {
        return levelFactory.getMap();
    }
    @Override
    public Coord spawnPoint() {
        return levelFactory.getSpawnPoint();
    }
    @Override
    public CameraViewManager createCameras() {
        return this.getCameras();
    }
    @Override
    public Avatar createAvatar(String avatarOccupation) {
        return null;
    }

    public CameraViewManager getCameras() {
        return cameras;
    }
}
