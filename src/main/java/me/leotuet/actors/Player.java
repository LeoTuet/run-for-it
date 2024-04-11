package me.leotuet.actors;

import greenfoot.Greenfoot;
import me.leotuet.utils.BoundingActor;
import me.leotuet.utils.Direction;

public class Player extends BoundingActor {

	public static final int PLAYER_SIZE = 128;
	public static final int DEFAULT_MOVEMENT_SPEED = 10;
	public static final int MAX_MOVEMENT_SPEED = 25;
	public static final int JUMP_VELOCITY = 20;
	public static final int ACCELERATION_TICKS_NEEDED = 5;

	private Direction freezeDirection = Direction.NONE;
	private int movementSpeed = 20;
	private int gravityVelocity = 0;
	private boolean isJumping = false;
	private boolean isRunningRight = false;
	private boolean isRunningLeft = false;

	private int accelerationTickCount = 0;

	public Player() {
		super(PLAYER_SIZE);
		this.getImage().scale(PLAYER_SIZE, PLAYER_SIZE);
	}

	public void act() {
		move();
		gravity();
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

	public void setFreezeDirection(Direction direction) {
		this.freezeDirection = direction;
	}

	public Direction getFreezeDirection() {
		return this.freezeDirection;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public boolean isAllowedToMove(Direction direction) {
		return !this.isTouchingBlock(direction, movementSpeed);
	}

	private boolean isTouchingBlock(Direction direction, int tolerance) {
		return this.isIntersecting(direction, tolerance, Block.class);
	}

	private void move() {
		if (freezeDirection == Direction.ALL) {
			return;
		}

		if (isMovingUp()) {
			if (!isJumping) {
				isJumping = true;
				gravityVelocity = -JUMP_VELOCITY;
			}
		}

		if (isMovingRight() && isAllowedToMove(Direction.RIGHT)) {
			if (isMovingLeft()) {
				resetMovementSpeed();
			} else {
				accelerate();
				isRunningRight = true;
				isRunningLeft = false;
			}

			if (freezeDirection != Direction.RIGHT) {
				this.setLocation(getX() + movementSpeed, getY());
			}

		} else if (!isRunningLeft && isRunningRight) {
			resetMovementSpeed();
		}

		if (isMovingLeft() && isAllowedToMove(Direction.LEFT)) {
			if (isMovingRight()) {
				resetMovementSpeed();
			} else {
				accelerate();
				isRunningRight = false;
				isRunningLeft = true;
			}

			if (freezeDirection != Direction.LEFT) {
				this.setLocation(getX() - movementSpeed, getY());
			}

		} else if (!isRunningRight && isRunningLeft) {
			resetMovementSpeed();
		}

	}

	private void gravity() {
		if (freezeDirection == Direction.ALL) {
			return;
		}

		if (this.isTouchingBlock(Direction.BELOW, gravityVelocity) && gravityVelocity >= 0) {
			gravityVelocity = 0;
			isJumping = false;
		} else {
			if (!this.isTouchingBlock(Direction.ABOVE, gravityVelocity) || gravityVelocity >= 0) {
				this.setLocation(getX(), getY() + gravityVelocity);
			}
			gravityVelocity += 1;
		}
	}

	private void accelerate() {
		if (movementSpeed <= MAX_MOVEMENT_SPEED && accelerationTickCount == ACCELERATION_TICKS_NEEDED) {
			movementSpeed += 1;
			accelerationTickCount = 0;
		} else if (movementSpeed != MAX_MOVEMENT_SPEED) {
			accelerationTickCount++;
		}
	}

	private void resetMovementSpeed() {
		movementSpeed = DEFAULT_MOVEMENT_SPEED;
		isRunningRight = false;
		isRunningLeft = false;
	}
}
