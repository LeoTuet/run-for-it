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

	public Location getTopLeft() {
		return new Location(this.getX() - this.getHalfSizeX(), this.getY() - this.getHalfSizeY());
	}

	public Location getTopRight() {
		return new Location(this.getX() + this.getHalfSizeX(), this.getY() - this.getHalfSizeY());
	}

	public Location getBottomLeft() {
		return new Location(this.getX() - this.getHalfSizeX(), this.getY() + this.getHalfSizeY());
	}

	public Location getBottomRight() {
		return new Location(this.getX() + this.getHalfSizeX(), this.getY() + this.getHalfSizeY());
	}

	public Location getLocation() {
		return new Location(this.getX(), this.getY());
	}

	public <T extends BoundingActor> boolean isIntersecting(Direction direction, T collisionActor, int tolerance) {
		switch (direction) {
			case ABOVE:
			case BELOW:
			case LEFT:
			case RIGHT:
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
