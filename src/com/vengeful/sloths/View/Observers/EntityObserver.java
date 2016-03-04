package com.vengeful.sloths.View.Observers;

import com.vengeful.sloths.Models.Map.MapItems.MapItem;
import com.vengeful.sloths.Utility.Direction;
import com.vengeful.sloths.Utility.WeaponClass;

public interface EntityObserver extends ModelObserver {
	void alertDirectionChange(Direction d);
	void alertMove(int r, int s, long animationTime);
	void alertAttack(int r, int s, long animationTime);

	@Deprecated
	void alertDrop(int x, int y, MapItem itemToDrop);


	void alertEquipWeapon(String name, WeaponClass weaponClass);
	void alertUnequipWeapon();
	void alertEquipHat(String name);
	void alertUnequipHat();
	void alertLevelUp();
	void alertDeath();
}
