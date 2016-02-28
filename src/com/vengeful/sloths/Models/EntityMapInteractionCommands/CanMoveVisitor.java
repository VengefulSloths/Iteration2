package com.vengeful.sloths.Models.EntityMapInteractionCommands;

import com.vengeful.sloths.Models.ModelVisitor;

/**
 * Created by alexs on 2/28/2016.
 */
public abstract class CanMoveVisitor implements ModelVisitor{
    private boolean canMove = true;

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
