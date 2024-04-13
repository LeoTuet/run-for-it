package me.leotuet.actors;

import me.leotuet.utils.BoundingActor;
import me.leotuet.worlds.GameWorld;

public class PowerUp extends BoundingActor {

	public PowerUp() {
		super(GameWorld.BLOCK_SIZE, GameWorld.BLOCK_SIZE);
	}

}
