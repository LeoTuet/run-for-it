package me.leotuet.actors;

import me.leotuet.utils.BoundingActor;

public class Block extends BoundingActor {

	public static final int BLOCK_SIZE = 64;
	public static final int HALF_BLOCK_SIZE = BLOCK_SIZE / 2;

	public Block() {
		super(BLOCK_SIZE);
		this.getImage().scale(BLOCK_SIZE, BLOCK_SIZE);
	}

}
