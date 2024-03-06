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

	public Location getBounding(BoundingOptions boundingOptions) {
		switch (boundingOptions) {
			case TOP:
				return new Location(this.getX(), this.getY() - this.sizeY / 2);
			case BOTTOM:
				return new Location(this.getX(), this.getY() + this.sizeY / 2);
			case LEFT:
				return new Location(this.getX() - this.sizeX / 2, this.getY());
			default:
				return new Location(this.getX() + this.sizeX / 2, this.getY());
		}
	}

	public Location getOppositeBounding(BoundingOptions boundingOptions) {
		switch (boundingOptions) {
			case TOP:
				boundingOptions = BoundingOptions.BOTTOM;
				break;
			case BOTTOM:
				boundingOptions = BoundingOptions.TOP;
				break;
			case LEFT:
				boundingOptions = BoundingOptions.RIGHT;
				break;
			case RIGHT:
				boundingOptions = BoundingOptions.LEFT;
				break;

		}

		return this.getBounding(boundingOptions);
	}

	// public boolean isIntersecting(BoundingBox boundingBox, Location location);
	public boolean isIntersecting(BoundingOptions boundingBox, Location collisionLocation, int tolerance) {
		var ownLocation = getBounding(boundingBox);
		var isIntersectingY = ownLocation.y - tolerance <= collisionLocation.y
				&& collisionLocation.y <= ownLocation.y + tolerance;
		var isIntersectingX = ownLocation.x - tolerance <= collisionLocation.x
				&& collisionLocation.x <= ownLocation.x + tolerance;

		switch (boundingBox) {
			case TOP, BOTTOM:
				return isIntersectingY;
			case LEFT, RIGHT:
				return isIntersectingX;
			default:
				return false;
		}

	}

	public Location getLocation() {
		return new Location(this.getX(), this.getY());
	}

}
