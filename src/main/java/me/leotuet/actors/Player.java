package me.leotuet.actors;

import greenfoot.Greenfoot;
import me.leotuet.utils.BoundingActor;
import me.leotuet.utils.Direction;

public class Player extends BoundingActor {

	public static final int PLAYER_SIZE = 128;
	private Direction mapMovement = Direction.NONE;
	private int movementSpeed = 10;
	private int gravityVelocity = 0;
	private int jumpVelocity = 20;
	private boolean isJumping = false;

	public Player() {
		super(PLAYER_SIZE);
		this.getImage().scale(PLAYER_SIZE, PLAYER_SIZE);
	}

	public void act() {
		move();
		gravity();
	}

	public void move() {
		if (isMovingUp()) {
			if (!isJumping) {
				isJumping = true;
				gravityVelocity = -jumpVelocity;
			}
		}

		if (isMovingRight()) {
			if (isAllowedToMove(Direction.RIGHT) && mapMovement != Direction.RIGHT) {
				this.setLocation(getX() + movementSpeed, getY());
			}
		}

		if (isMovingLeft()) {
			if (isAllowedToMove(Direction.LEFT) && mapMovement != Direction.LEFT) {
				this.setLocation(getX() - movementSpeed, getY());
			}
		}
	}

	public void gravity() {
		if (this.isTouchingBlock(Direction.BELOW, gravityVelocity) && gravityVelocity >= 0) {
			gravityVelocity = 0;
			isJumping = false;
		} else {
			this.setLocation(getX(), getY() + gravityVelocity);
			gravityVelocity += 1;
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

	public boolean isMovingDown() {
		return Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s");
	}

	public void setMapMovement(Direction direction) {
		this.mapMovement = direction;
	}

	public boolean isTouchingBlock(Direction direction, int tolerance) {
		return this.isIntersecting(direction, tolerance, Block.class);
	}

	public boolean isAllowedToMove(Direction direction) {
		return !this.isTouchingBlock(direction, movementSpeed);
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}
}
