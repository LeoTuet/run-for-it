package me.leotuet.actors;

import greenfoot.Greenfoot;
import me.leotuet.utils.BoundingActor;
import me.leotuet.utils.Direction;

public class Bean extends BoundingActor {

	public static final int PLAYER_SIZE = 128;
	private int movementSpeed = 4;
	private Direction preventMovement = Direction.NONE;

	public Bean() {
		super(PLAYER_SIZE);
		this.getImage().scale(PLAYER_SIZE, PLAYER_SIZE);
	}

	public void act() {
		move();
	}

	public void move() {
		if (isMovingUp()) {
			if (!this.isTouchingBlock(Direction.ABOVE) && preventMovement != Direction.ABOVE) {
				this.setLocation(getX(), getY() - movementSpeed * 2);
			}
		}

		if (isMovingDown()) {
			if (!this.isTouchingBlock(Direction.BELOW)) {
				this.setLocation(getX(), getY() + movementSpeed);
			}
		}

		if (isMovingRight()) {
			if (!this.isTouchingBlock(Direction.RIGHT) && preventMovement != Direction.RIGHT) {
				this.setLocation(getX() + movementSpeed, getY());
			}
		}

		if (isMovingLeft()) {
			if (!this.isTouchingBlock(Direction.LEFT) && preventMovement != Direction.LEFT) {
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

	public boolean isMovingDown() {
		return Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s");
	}

	public void preventMove(Direction direction) {
		this.preventMovement = direction;
	}

	public static int getCenter() {
		return PLAYER_SIZE / 2;
	}

	public boolean isTouchingBlock(Direction direction) {
		if (this.isTouching(Block.class)) {
			var block = (Block) this.getOneIntersectingObject(Block.class);
			return this.isIntersecting(direction, block, movementSpeed);
		}
		return false;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}
}
