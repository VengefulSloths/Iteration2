package com.vengeful.sloths.Models.RangedEffects;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 3/8/16.
 */
public class RadialEffectGenerator extends RangedEffectGenerator{
    ArrayList<ImmovableHitBox> listOfHitbox; //contains all hitbox created
    private int expandingDistanceLeft;
    private int expandingTime;
    private int totalExpandingDistance;
    private Coord initialLocation;
    private int initialDmg;
    private int initialAccuracy;
    private String hitboxName;

    private DefaultCanGenerateVisitor canGenerateVisitor;

    public RadialEffectGenerator(String name, Coord location, int expandingDistance, int expandingTime, int initialDmg, int initialAccuracy, DefaultCanGenerateVisitor canGenerateVisitor){
        this.hitboxName = name;
        this.initialLocation = location;
        this.totalExpandingDistance = expandingDistance;
        this.expandingDistanceLeft = totalExpandingDistance;
        this.expandingTime = expandingTime;
        this.initialDmg = initialDmg;
        this.initialAccuracy = initialAccuracy;
        listOfHitbox = new ArrayList<>();
        this.canGenerateVisitor = canGenerateVisitor;
    }

    @Override
    public void createRangedEffect() {

        if(expandingDistanceLeft > 0){
            expandingDistanceLeft--;
            System.out.println("***************** 360: GENERATE ON RING: " + (totalExpandingDistance-expandingDistanceLeft) + " *********************");
            generateHitBox(HexMath.ring(initialLocation, totalExpandingDistance-expandingDistanceLeft));
            TimeModel.getInstance().registerAlertable(() -> {
                createRangedEffect();
            }, expandingTime);
            System.out.println("***************** 360: GENERATE ON RING: " + (totalExpandingDistance-expandingDistanceLeft) + " DONE ***************");

        }else{
            for(ImmovableHitBox hb : this.listOfHitbox){
                hb.alertObserverOnDestroy();
            }
        }
    }


    private void generateHitBox(Iterator<Coord> expandingLocation){
        int damage = calculateAtt(initialDmg, totalExpandingDistance-expandingDistanceLeft);
        int accuracy = calculateAccuracy(initialAccuracy, totalExpandingDistance-expandingDistanceLeft);

        boolean doesTileExist;

        //Iterator<Coord> expandingLocation = HexMath.ring(initialLocation, totalExpandingDistance-expandingDistanceLeft);

        int tileCounter = 0; //TODO: test , remove

        while(expandingLocation.hasNext()){
            Coord loc = expandingLocation.next();

            //TODO: remove, remove this doesTileExist thing after map padding added!!
            doesTileExist = HexMath.isValidTile(loc, Map.getInstance().getActiveMapArea().getMaxR(), Map.getInstance().getActiveMapArea().getMaxS());
            if(doesTileExist){
//                Map.getInstance().getActiveMapArea().getTile(loc).accept(canGenerateVisitor);
//                if(canGenerateVisitor.canGenerate()){
                    ImmovableHitBox newHB = new ImmovableHitBox(hitboxName, loc, damage, accuracy);
                    newHB.accept(TemporaryVOCreationVisitor.getInstance());
                    listOfHitbox.add(newHB);
                    newHB.takeDamage();
                    tileCounter++;
//                }else {
//                    System.out.println("HITBOX: generation rejected at location: " + loc.getR() + ", " + loc.getS());
//                } //TODO: visitor throws: null tile if fire on edge. Bring this back once added more padding to map
            }else{
                System.out.println("HITBOX: generation rejected at location: " + loc.getR() + ", " + loc.getS());
            }
        }
        System.out.println("Created " + tileCounter + " tiles");
    }


    @Override
    public void tickAlert() {


    }
}
