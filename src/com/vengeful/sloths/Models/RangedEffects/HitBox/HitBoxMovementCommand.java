package com.vengeful.sloths.Models.RangedEffects.HitBox;


//import apple.laf.JRSUIConstants;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.CanMoveVisitor;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Observers.HitBoxObserver;
import com.vengeful.sloths.Models.RangedEffects.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.Iterator;

/**
 * Created by luluding on 3/8/16.
 */
public class HitBoxMovementCommand{
    private Coord src;
    private Coord dst;
    private Map map;
    private int travelTicks;
    private HitBox subject;
    private RangedEffectGenerator creator;
    private Iterator<HitBoxObserver> observerIterator;
    private DefaultCanGenerateVisitor canGenerateVisitor;


    public HitBoxMovementCommand(Coord src, Direction dir, HitBox hitBox, int travelTicks, RangedEffectGenerator creator, Iterator<HitBoxObserver> observerIterator, DefaultCanGenerateVisitor canGenerateVisitor) {
        this.src = src;
        this.map = Map.getInstance();
        this.travelTicks = travelTicks;
        this.subject = hitBox;
        this.creator = creator;
        this.observerIterator = observerIterator;

        this.dst = HexMath.getNextFacingCoord(src, dir);
        this.canGenerateVisitor = canGenerateVisitor;

    }

    public int execute() {

        //map area padding = 1
        boolean doesTileExist = HexMath.isValidTile(dst, Map.getInstance().getActiveMapArea().getMaxR()-1, Map.getInstance().getActiveMapArea().getMaxS()-1);

        if(!doesTileExist){
            System.out.println("HITBOX: movement rejected " + src.toString() + " to " + dst.toString());
            return 0;
        }

        //TODO: visitor throws: null tile, get this back once added more padding to map area
//        map.getActiveMapArea().getTile(dst).accept(canGenerateVisitor);
//        if(!canGenerateVisitor.canGenerate()){
//            return 0;
//        }

        this.subject.setLocation(this.dst);

        TimeModel.getInstance().registerAlertable(() -> {
            this.creator.tickAlert();
        }, this.travelTicks);

        //alert view
        while (observerIterator.hasNext()) {
            observerIterator.next().alertMove(dst.getR(), dst.getS(), travelTicks * TimeController.MODEL_TICK);
        }

        System.out.println("HITBOX: Going to move from " + src.toString() + " to " + dst.toString());

        return this.travelTicks;
    }
}
