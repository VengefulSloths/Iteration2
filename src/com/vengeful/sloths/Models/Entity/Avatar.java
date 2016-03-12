package com.vengeful.sloths.Models.Entity;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.EntityMapInteractionCommands.EntityMapInteractionFactory;
import com.vengeful.sloths.Models.Inventory.Equipped;
import com.vengeful.sloths.Models.Inventory.Inventory;
import com.vengeful.sloths.Models.Map.Map;
import com.vengeful.sloths.Models.ModelVisitor;
import com.vengeful.sloths.Models.InventoryItems.ConsumableItems.ConsumableItems;
import com.vengeful.sloths.Models.Occupation.Smasher;
import com.vengeful.sloths.Models.Occupation.Sneak;
import com.vengeful.sloths.Models.Occupation.Summoner;
import com.vengeful.sloths.Models.Skills.Skill;
import com.vengeful.sloths.Models.Skills.SkillManager;
import com.vengeful.sloths.Models.Stats.StatAddables.HealthManaExperienceAddable;
import com.vengeful.sloths.Models.Stats.Stats;
import com.vengeful.sloths.Utility.Coord;
import com.vengeful.sloths.Utility.HexMath;
import com.vengeful.sloths.Utility.Location;

import com.vengeful.sloths.Models.TimeModel.TimeModel;
import com.vengeful.sloths.Utility.Direction;

import java.util.Iterator;

/**
 * Created by luluding on 2/21/16.
 */
public class Avatar extends Entity{

    private static Avatar avatar = null;
    private int timeToRespawn;
    private int stealthLevel = 5;
    private Pet pet;


    private Avatar(){
        super("Phill", new Stats());
        this.getStats().setLives(3);
    }

    public static Avatar getInstance(){
        if(avatar == null)
            avatar = new Avatar();

        return avatar;
    }

    public void setTimeToRespawn(int time) {
        this.timeToRespawn = time;
    }

    public int getTimeToRespawn() {
        return this.timeToRespawn;
    }

    private boolean isMounted = false;

    public void avatarInit(String occupationString, AbilityManager abilityManager, BuffManager buffManager, SkillManager skillManager){

        this.setInventory(new Inventory());
        this.setAbilityManager(abilityManager);
        this.setBuffManager(buffManager);
        this.setSkillManager(skillManager);
        //this.setStats(stats);
        this.setEquipped(new Equipped(this));

        switch (occupationString) {
            case "Smasher":
                this.setOccupation(new Smasher(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            case "Sneak":
                this.setOccupation(new Sneak(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            case "Summoner":
                this.setOccupation(new Summoner(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
                break;
            default:
                this.setOccupation(new Summoner(this.getStats(), this.getSkillManager(), this.getAbilityManager(), this));
        }

        //TODO: test ability, remove
        Iterator<Skill> iter = skillManager.getSkillsIter();
        Skill bws = null;
        while(iter.hasNext()){
            Skill s = iter.next();
            if(s.getName() == "bind wounds"){
                bws = s;
                break;
            }
        }

        skillManager.updateSkillLevel(bws, 1);
        ///////////////////////////

    }

    public int getStealthLevel() {
        return stealthLevel;
    }



    public void mount() {
        this.getAbilityManager().doMountAbility();
        this.stealthLevel = 0;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }

    @Override
    public int attack(Direction dir) {
        return super.attack(dir);
    }

    @Override
    public void doAbility(int index) {
        super.doAbility(index);
    }

    public boolean isMounted() {
        return isMounted;
    }

    public void avatarInit(String occupationString, Stats stats){
        this.avatarInit(occupationString, new AbilityManager(this), new BuffManager(this), new SkillManager());
    }


    public void talk(){
        //create talk command
    }

    public void consumeItem(ConsumableItems item){
        item.use(this.getStats());
    }



    public void incSkillLevel(Skill skill){
       this.getSkillManager().updateSkillLevel(skill, 1);
    }


    //called by levelUp AE
    public void levelUp() {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, this.getStats().getMaxExperience() - this.getStats().getCurrentExperience()));
        this.getOccupation().levelUp(this.getStats());
        this.getSkillManager().setAvailableSkillPoint(this.getSkillManager().getAvailableSkillPoints() + 2); //hard coded to gain 2 sp every level
    }

    public void gainXP(int xp) {
        this.getStats().add(new HealthManaExperienceAddable(0, 0, 0, 0, xp));
    }

    public void accept(ModelVisitor modelVisitor) {
        modelVisitor.visitAvatar(this);
    }

    @Override
    protected void dropAllItems() {
        // Do nothing
        //@ TODO: Maybe have this dorp a few coins
    }

    @Override
    protected void doRespawn() {
        EntityMapInteractionFactory.getInstance().createRespawnCommand(this, this.getLocation(), timeToRespawn);
    }

    @Override
    public void teleportPet(Location location){
        if(pet != null){
            System.out.println("squaaaaaaa");
            Coord coord = HexMath.getClosestMovableTile(location);
            Map.getInstance().getTile(pet.getLocation()).removeEntity(pet);
            System.out.println("here is the coord that piggy trying to go to : " + coord);
            location.getMapArea().addEntity(pet, coord);
            pet.getControllerManager().setMapArea(location.getMapArea());
        }
    }


    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet removePet() { this.pet = null; return pet; }
}
