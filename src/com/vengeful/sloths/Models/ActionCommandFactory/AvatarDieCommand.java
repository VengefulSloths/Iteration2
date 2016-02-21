package com.vengeful.sloths.Models.ActionCommandFactory;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;

/**
 * Created by John on 2/7/2016.
 */
public class AvatarDieCommand extends DieCommand {

    public AvatarDieCommand(Map map, Coord coord, Entity entity){
        super(map, coord, entity);

    }

    @Override
    public void execute() {
        AvatarActionCommandFactory cf = new AvatarActionCommandFactory(map);
        /*THIS COMMAND NEEDS TO BE UPDATED TO ACCOUNT FOR MAP AREAS NOW
        I CHANGED THE LINE BELOW TO GET RESPAWN.GETCOORD SO THAT IT COMPILES FOR THE MOMENT
         */
        Coord location = map.getRespawnPoint().getCoord();
//        map.getTile(coord).removeEntity();
//        map.getTile(location).addEntity(entity);
        cf.createMovementCommand(coord, location, Direction.N,entity,0);

        //TODO: all of this over again
//        entity.getEntityStats().setCurrentHealth(entity.getEntityStats().getLife());
//        entity.getEntityStats().setCurrentMana(entity.getEntityStats().getMana());
//        entity.getEntityStats().alertObservers();
        //System.out.Println("should be moved n shit");

    }
}
