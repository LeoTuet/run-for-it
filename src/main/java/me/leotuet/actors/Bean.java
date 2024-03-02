package me.leotuet.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

enum Direction {
	LEFT, RIGHT, NONE, UP, DOWN
}

public class Bean extends Actor {

	private int movementSpeed = 4;
	private Direction preventMovement = Direction.NONE;

	public Bean() {
		this.getImage().scale(64, 64);
	}

	public void act() {
		move();
	}

	public void move() {
		if (isMovingUp()) {
			this.setLocation(getX(), getY() - movementSpeed * 2);
		}

		if (!isTouchingBlock()) {
			this.setLocation(getX(), getY() + movementSpeed);
		}

		if (isMovingRight()) {
			if (preventMovement != Direction.RIGHT) {
				this.setLocation(getX() + movementSpeed, getY());
			}
		}

		if (isMovingLeft()) {
			if (preventMovement != Direction.LEFT) {
				this.setLocation(getX() - movementSpeed, getY());
			}
		}
	}

	public boolean isMovingLeft() {
		return Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a");
	}

	public boolean isMovingRight() {
		return Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d");
	}

	public boolean isMovingUp() {
		return Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("space");
	}

	// public boolean isMovingDown() {
	// 	return Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s");
	// }

	public void preventMove(Direction direction) {
		this.preventMovement = direction;
	}

	public boolean isTouchingBlock() {
		return this.isTouching(Block.class);
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}
}
