package me.leotuet.utils;

import me.leotuet.actors.Block;

public abstract class Entity extends BoundingActor {

	public static final int ACCELERATION_TICKS_NEEDED = 5;

	private int defaultMovementSpeed;
	private int maxMovementSpeed;
	private int jumpVelocity;
	private int movementSpeed;

	private int gravityVelocity = 0;
	private int accelerationTickCount = 0;

	private Direction freezeDirection = Direction.NONE;
	private boolean isJumping = false;
	private boolean isMovingRight = false;
	private boolean isMovingLeft = false;
	private boolean isInitialMovement = true;

	public Entity(int width, int height, int defaultMovementSpeed, int maxMovementSpeed, int jumpVelocity) {
		super(width, height);
		this.getImage().scale(width, height);
		this.defaultMovementSpeed = defaultMovementSpeed;
		this.movementSpeed = defaultMovementSpeed;
		this.maxMovementSpeed = maxMovementSpeed;
		this.jumpVelocity = jumpVelocity;

	}

	public void act() {
		handleImageDirection();
		move();
		gravity();
	}

	public abstract boolean isMovingLeft();

	public abstract boolean isMovingRight();

	public abstract boolean isMovingUp();

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
				gravityVelocity = -this.jumpVelocity;
			}
		}

		if (isMovingRight() && isAllowedToMove(Direction.RIGHT)) {
			if (isMovingLeft()) {
				resetMovementSpeed();
			} else {
				accelerate();
				isMovingRight = true;
				isMovingLeft = false;
			}

			if (freezeDirection != Direction.RIGHT) {
				this.setLocation(getX() + movementSpeed, getY());
			}

		} else if (!isMovingLeft && isMovingRight) {
			resetMovementSpeed();
		}

		if (isMovingLeft() && isAllowedToMove(Direction.LEFT)) {
			if (isMovingRight()) {
				resetMovementSpeed();
			} else {
				accelerate();
				isMovingRight = false;
				isMovingLeft = true;
			}

			if (freezeDirection != Direction.LEFT) {
				this.setLocation(getX() - movementSpeed, getY());
			}

		} else if (!isMovingRight && isMovingLeft) {
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
		if (movementSpeed <= this.maxMovementSpeed && accelerationTickCount == ACCELERATION_TICKS_NEEDED) {
			movementSpeed += 1;
			System.out.println("Speed: " + movementSpeed);
			accelerationTickCount = 0;
		} else if (movementSpeed != this.maxMovementSpeed) {
			accelerationTickCount++;
		}
	}

	private void resetMovementSpeed() {
		movementSpeed = this.defaultMovementSpeed;
		isMovingRight = false;
		isMovingLeft = false;
	}

	private void handleImageDirection() {
		if ((isMovingLeft() && !isMovingLeft) || (isMovingRight() && !isMovingRight && !isInitialMovement)) {
			this.isInitialMovement = false;
			this.getImage().mirrorHorizontally();
		}
	}
}
