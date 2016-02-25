package com.vengeful.sloths.Models.Entity;

/**
 * Created by zach on 2/22/16.
 */
public interface EntityVisitor {

    void visitAvatar(Avatar avatar);
    void visitPiggy(Piggy piggy);
    void visitAggressiveNPC(AggressiveNPC aNPC);
    void visitNonAggressiveNPC(NonAggressiveNPC nonANPC);

}
