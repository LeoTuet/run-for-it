package me.leotuet.actors;

import me.leotuet.utils.BoundingActor;
import me.leotuet.worlds.GameWorld;

public class Block extends BoundingActor {

	public Block() {
		super(GameWorld.BLOCK_SIZE);
	}

}
