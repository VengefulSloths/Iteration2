package com.vengeful.sloths.Models.Ability.Hooks;

import com.vengeful.sloths.Models.Ability.AbilityManager;
import com.vengeful.sloths.Models.Buff.Buff;
import com.vengeful.sloths.Models.Buff.BuffManager;
import com.vengeful.sloths.Models.Entity.Entity;
import com.vengeful.sloths.Models.Observers.EntityObserver;

import java.util.ArrayList;

/**
 * Created by Alex on 3/12/2016.
 */
public class RemoveBuffHook implements Hook {
    private BuffManager buffManager;
    private Buff buff;


    public RemoveBuffHook(Buff buff, BuffManager buffManager) {
        this.buff = buff;
        this.buffManager = buffManager;
    }

    @Override
    public void execute(AbilityManager owner) {
        buffManager.removeBuff(buff);
        owner.removeAbilityHook(this);
    }
}
