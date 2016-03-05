package com.vengeful.sloths.Controllers.Target;

/**
 * Created by zach on 2/25/16.
 */
public interface TargetVisitor {
    void visitAvatarTarget(AvatarTarget avatar);
    void visitPiggyTarget(PiggyTarget piggy);
    void visitAggressiveNPCTarget(AggressiveNPCTarget aNPC);
    void visitNonAggressiveNPCTarget(NonAgressiveNPCTarget nonANPC);
    void visitMapItemTarget(MapItemTarget mapItemTarget);
}
