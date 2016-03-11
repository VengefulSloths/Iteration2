package com.vengeful.sloths.Models.Map.MapItems.InteractiveItem.Quest;

import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Utility.Coord;

/**
 * Created by alexs on 3/10/2016.
 */
public class DoDestroyObstacleQuest extends Quest{

    private Coord target;
    public DoDestroyObstacleQuest(Coord target) {
        this.target = target;
    }

    @Override
    public boolean attempt() {
        if (Map.getInstance().getActiveMapArea().getTile(target).hasObstacle()) {
            Map.getInstance().getActiveMapArea().getTile(target).removeObstacle();
            return true;
        }
        return false;
    }

    @Override
    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitDoDestroyObstacleQuest(this);
    }

    public Coord getTarget() {
        return target;
    }

    public void setTarget(Coord target) {
        this.target = target;
    }
}
