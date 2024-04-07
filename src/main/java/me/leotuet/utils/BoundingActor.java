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

	public <T extends BoundingActor> boolean isIntersecting(Direction direction, int tolerance, Class<T> collisionActor) {
		CrashActor crashActor = spawnCrashActor(direction, tolerance);
		var intersectingActor = crashActor.getIntersecting(collisionActor);
		boolean isIntersecting = intersectingActor != null;
		crashActor.remove();

		if (!isIntersecting) {
			return isIntersecting;
		}

		// Prevents bugging into objects
		if (direction == Direction.ABOVE) {
			this.setLocation(this.getX(), intersectingActor.getBottom().y +
					this.getHalfSizeY());
		} else if (direction == Direction.BELOW) {
			this.setLocation(this.getX(), intersectingActor.getTop().y -
					this.getHalfSizeY());
		}

		return isIntersecting;
	}

	private CrashActor spawnCrashActor(Direction direction, int tolerance) {
		var world = this.getWorld();
		CrashActor crashActor = new CrashActor();
		int toleranceSize = tolerance;

		if (toleranceSize <= 0) {
			toleranceSize = tolerance * -1;
		}

		if (toleranceSize < 5) {
			toleranceSize = 5;
		}

		switch (direction) {
			case ABOVE:
				crashActor.setSize(this.sizeX, toleranceSize);
				world.addObject(crashActor, this.getX(), this.getTop().y + tolerance / 2);
			case BELOW:
				crashActor.setSize(this.sizeX, toleranceSize);
				world.addObject(crashActor, this.getX(), this.getBottom().y - tolerance / 2);
			case LEFT:
				crashActor.setSize(toleranceSize, this.sizeY);
				world.addObject(crashActor, this.getLeft().x + tolerance / 2, this.getY());
			case RIGHT:
				crashActor.setSize(toleranceSize, this.sizeY);
				world.addObject(crashActor, this.getRight().x - tolerance / 2, this.getY());
			default:
				break;
		}

		return crashActor;

	}

	public int getHalfSizeX() {
		return sizeX / 2;
	}

	public int getHalfSizeY() {
		return sizeY / 2;
	}

}
