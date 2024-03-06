package me.leotuet.utils;

import greenfoot.Actor;

public class BoundingActor extends Actor {

	private int sizeX;
	private int sizeY;

	public BoundingActor(int size) {
		this.sizeX = size;
		this.sizeY = size;
	}

	public BoundingActor(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public Location getTop() {
		return new Location(this.getX(), this.getY() - this.getHalfSizeY());
	}

	public Location getRight() {
		return new Location(this.getX() + this.getHalfSizeX(), this.getY());
	}

	public Location getLeft() {
		return new Location(this.getX() - this.getHalfSizeX(), this.getY());
	}

	public Location getBottom() {
		return new Location(this.getX() + this.getHalfSizeX(), this.getY() + this.getHalfSizeY());
	}

	public Location getLocation() {
		return new Location(this.getX(), this.getY());
	}

	public <T extends BoundingActor> boolean isIntersecting(Direction direction, T collisionActor, int tolerance) {
		var isBetweenObject = (this.getTop().y <= collisionActor.getTop().y + tolerance
				|| this.getBottom().y >= collisionActor.getBottom().y - tolerance);

		switch (direction) {

			case ABOVE:
				var isAbove = this.getTop().y >= collisionActor.getBottom().y - tolerance
						&& this.getTop().y <= collisionActor.getBottom().y;

				if (isAbove) {
					this.setLocation(this.getX(), collisionActor.getBottom().y + this.getHalfSizeY());
				}

				return isAbove;
			case BELOW:
				var isBelow = this.getBottom().y >= collisionActor.getTop().y - tolerance
						&& this.getBottom().y <= collisionActor.getTop().y;

				if (isBelow) {
					this.setLocation(this.getX(), collisionActor.getTop().y - this.getHalfSizeY());
				}

				return isBelow;
			case LEFT:
				var isLeft = isBetweenObject
						&& this.getLeft().x >= collisionActor.getRight().x - tolerance
						&& this.getLeft().x <= collisionActor.getRight().x;

				if (isLeft) {
					this.setLocation(collisionActor.getRight().x + this.getHalfSizeX(), this.getY());
				}

				return isLeft;
			case RIGHT:
				var isRight = isBetweenObject
						&& this.getRight().x >= collisionActor.getLeft().x - tolerance
						&& this.getRight().x <= collisionActor.getLeft().x;

				if (isRight) {
					this.setLocation(collisionActor.getLeft().x - this.getHalfSizeX(), this.getY());
				}

				return isRight;
			case NONE:
				return false; // This is a special case, as it is not possible to intersect with no direction
		}
		return false;
	}

	private int getHalfSizeX() {
		return sizeX / 2;
	}

	private int getHalfSizeY() {
		return sizeY / 2;
	}

}
