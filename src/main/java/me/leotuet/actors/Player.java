package me.leotuet.actors;

import greenfoot.Greenfoot;
import me.leotuet.utils.Entity;

public class Player extends Entity {

	public static final int PLAYER_SIZE = 128;
	public static final int DEFAULT_MOVEMENT_SPEED = 10;
	public static final int MAX_MOVEMENT_SPEED = 20;
	public static final int JUMP_VELOCITY = 20;
	public static final int ACCELERATION_TICKS_NEEDED = 5;

	public Player() {
		super(PLAYER_SIZE / 2, PLAYER_SIZE, DEFAULT_MOVEMENT_SPEED, MAX_MOVEMENT_SPEED, JUMP_VELOCITY);
	}

	public void act() {
		super.act();
	}

	@Override
	public boolean isMovingLeft() {
		return Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a");
	}

	@Override
	public boolean isMovingRight() {
		return Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d");
	}

	@Override
	public boolean isMovingUp() {
		return Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("space");
	}

}
