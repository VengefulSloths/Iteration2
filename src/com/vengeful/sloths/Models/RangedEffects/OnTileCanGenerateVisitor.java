package com.vengeful.sloths.Models.RangedEffects;

import com.vengeful.sloths.Models.Map.Terrains.Water;

/**
 * Created by luluding on 3/10/16.
 */
public class OnTileCanGenerateVisitor extends DefaultCanGenerateVisitor{
    @Override
    public void visitWater(Water water) {
        setCanGenerate(false);
    }
}
