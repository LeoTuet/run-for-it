package me.leotuet.actors;

import me.leotuet.utils.BoundingActor;
import me.leotuet.worlds.GameWorld;

public class Gate extends BoundingActor {

	public Gate() {
		super(GameWorld.BLOCK_SIZE);
		this.getImage().setTransparency(0);
		this.getImage().scale(GameWorld.BLOCK_SIZE, GameWorld.BLOCK_SIZE);
	}

}
