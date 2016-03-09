package com.vengeful.sloths.Models.RangedEffects.HitBox;

import apple.laf.JRSUIConstants;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.CanMoveVisitor;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.RangedEffects.RangedEffectGenerator;
import com.vengeful.sloths.Models.TimeModel.Alertable;
import com.vengeful.sloths.Models.TimeModel.TimeController;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.View.Observers.EntityObserver;
import javafx.scene.control.Alert;

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


    public HitBoxMovementCommand(Coord src, Direction dir, HitBox hitBox, int travelTicks, RangedEffectGenerator creator) {
        this.src = src;
        this.map = Map.getInstance();
        this.travelTicks = travelTicks;
        this.subject = hitBox;
        this.creator = creator;


        this.dst = new Coord(src.getR(), src.getS());
        switch (dir) {
            case N:
                dst.setS(dst.getS() - 1);
                break;
            case S:
                dst.setS(dst.getS() + 1);
                break;
            case NE:
                dst.setR(dst.getR() + 1);
                dst.setS(dst.getS() - 1);
                break;
            case NW:
                dst.setR(dst.getR() - 1);
                break;
            case SE:
                dst.setR(dst.getR() + 1);
                break;
            case SW:
                dst.setR(dst.getR() - 1);
                dst.setS(dst.getS() + 1);
                break;
            default:
                break;
        }

    }

    public int execute() {

        boolean doesTileExist = HexMath.isValidTile(dst, Map.getInstance().getActiveMapArea().getMaxR(), Map.getInstance().getActiveMapArea().getMaxS());

        if(!doesTileExist){
            System.out.println("HITBOX: movement rejected " + src.toString() + " to " + dst.toString());
            return 0;
        }


        this.subject.setLocation(this.dst);

        TimeModel.getInstance().registerAlertable(() -> {
            this.creator.tickAlert();
        }, this.travelTicks);

        System.out.println("HITBOX: Going to move from " + src.toString() + " to " + dst.toString());

        return this.travelTicks;
    }
}
