package com.vengeful.sloths.Models.RangedEffects;

import com.vengeful.sloths.AreaView.TemporaryVOCreationVisitor;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.RangedEffects.CanGenerateVisitor.DefaultCanGenerateVisitor;
import com.vengeful.sloths.Models.RangedEffects.HitBox.ImmovableHitBox;
import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.HexMath;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by luluding on 3/8/16.
 */
public class AngularEffectGenerator extends RangedEffectGenerator{

    ArrayList<ImmovableHitBox> listOfHitbox; //contains all hitbox created
    private int expandingDistanceLeft;
    private int expandingTime;
    private int totalDistance;
    private Coord initialLocation;
    private int initialDmg;
    private int initialAccuracy;
    private String hitboxName;
    private Direction facingDirection;

    private DefaultCanGenerateVisitor canGenerateVisitor;

    public AngularEffectGenerator(String name, Coord location, int expandingDistance, int expandingTime, int initialDmg, int initialAccuracy, DefaultCanGenerateVisitor canGenerateVisitor, Direction facingDirection){
        this.hitboxName = name;
        this.initialLocation = location;
        this.totalDistance = expandingDistance;
        this.expandingDistanceLeft = totalDistance;
        this.expandingTime = expandingTime;
        this.initialDmg = initialDmg;
        this.initialAccuracy = initialAccuracy;
        listOfHitbox = new ArrayList<>();
        this.canGenerateVisitor = canGenerateVisitor;
        this.facingDirection = facingDirection;
    }


    @Override
    public void createRangedEffect(){
        if(expandingDistanceLeft > 0){
            expandingDistanceLeft--;
            System.out.println("***************** 60 DEGREE ANGLE EFFECT: " + (totalDistance-expandingDistanceLeft) + " *********************");
            generateHitBox(HexMath.angle(initialLocation, totalDistance-expandingDistanceLeft, facingDirection));
            TimeModel.getInstance().registerAlertable(() -> {
                createRangedEffect();
            }, expandingTime);
            System.out.println("***************** 60 DEGREE ANGLE EFFECT: " + (totalDistance-expandingDistanceLeft) + " DONE ***************");

        }else{
            for(ImmovableHitBox hb : this.listOfHitbox){
                hb.alertObserverOnDestroy();
            }
        }
    }

    private void generateHitBox(Iterator<Coord> location){
        int damage = calculateAtt(initialDmg, totalDistance-expandingDistanceLeft);
        int accuracy = calculateAccuracy(initialAccuracy, totalDistance-expandingDistanceLeft);

        boolean doesTileExist;

        int tileCounter = 0; //TODO: test , remove

        while(location.hasNext()){
            Coord loc = location.next();

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
