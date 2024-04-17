package me.leotuet.actors;

import me.leotuet.utils.Entity;
import me.leotuet.worlds.GameWorld;

public class Enemy extends Entity {

	private static final int MOVEMENT_SPEED = 2;
	private static final int BLOCK_MOVEMENT = 3;
	private int tickCount = 0;

	public Enemy() {
		super(64, 64, MOVEMENT_SPEED, MOVEMENT_SPEED, 0);
		this.getImage().mirrorHorizontally();
	}

	@Override
	public boolean isMovingLeft() {
		if (tickCount <= GameWorld.BLOCK_SIZE * BLOCK_MOVEMENT) {
			tickCount++;
			return true;
		}
		return false;
	}

	@Override
	public boolean isMovingRight() {
		if (tickCount > GameWorld.BLOCK_SIZE * BLOCK_MOVEMENT) {

			if (tickCount == GameWorld.BLOCK_SIZE * BLOCK_MOVEMENT * 3) {
				tickCount = -GameWorld.BLOCK_SIZE * BLOCK_MOVEMENT;
				return false;
			}

			tickCount++;
			return true;
		}

		return false;
	}

	@Override
	public boolean isMovingUp() {
		return false;
	}

}
