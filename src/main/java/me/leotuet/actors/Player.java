package me.leotuet.actors;

import greenfoot.Greenfoot;
import javafx.scene.input.KeyCode;
import me.leotuet.utils.Entity;
import me.leotuet.utils.KeyHelper;

public class Player extends Entity {

	public static final int PLAYER_SIZE = 128;
	public static final int DEFAULT_MOVEMENT_SPEED = 10;
	public static final int MAX_MOVEMENT_SPEED = 20;
	public static final int JUMP_VELOCITY = 20;
	public static final int ACCELERATION_TICKS_NEEDED = 5;
	public static final int POWER_UP_DURATION = 500;

	private int powerUpTicks = 0;
	private boolean superPowerEnabled = false;

	public Player() {
		super(PLAYER_SIZE / 2, PLAYER_SIZE, DEFAULT_MOVEMENT_SPEED, MAX_MOVEMENT_SPEED, JUMP_VELOCITY);
	}

	public void act() {
		super.act();
		handlePowerUp();
		handleSuperPower();
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

	private void handlePowerUp() {
		var powerUp = getOneIntersectingObject(PowerUp.class);

		if (powerUp != null) {
			powerUpTicks = POWER_UP_DURATION;
			superPowerEnabled = true;
			getWorld().removeObject(powerUp);
		}

		if (powerUpTicks > 0) {
			powerUpTicks--;
		} else {
			superPowerEnabled = false;
		}
	}

	private void handleSuperPower() {
		if (superPowerEnabled && KeyHelper.isKeyPressedDown(KeyCode.SHIFT, "shift")) {
			System.out.println("fireball");
		}
	}

}
