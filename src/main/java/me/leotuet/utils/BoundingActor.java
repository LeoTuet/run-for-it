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

	public int getTop() {
		return this.getY() - this.getHalfSizeY();
	}

	public int getRight() {
		return this.getX() + this.getHalfSizeX();
	}

	public int getBottom() {
		return this.getY() + this.getHalfSizeY();
	}

	public int getLeft() {
		return this.getX() - this.getHalfSizeX();
	}

	public <T extends BoundingActor> boolean isIntersecting(Direction direction, int tolerance, Class<T> collisionActor) {
		PhantomActor crashActor = spawnCrashActor(direction, tolerance);
		var intersectingActor = crashActor.getIntersecting(collisionActor);
		boolean isIntersecting = intersectingActor != null;
		crashActor.remove();

		if (!isIntersecting) {
			return isIntersecting;
		}

		// Prevents bugging into objects
		if (direction == Direction.ABOVE) {
			this.setLocation(this.getX(), intersectingActor.getBottom() +
					this.getHalfSizeY());
		} else if (direction == Direction.BELOW) {
			this.setLocation(this.getX(), intersectingActor.getTop() -
					this.getHalfSizeY());
		}

		return isIntersecting;
	}

	private PhantomActor spawnCrashActor(Direction direction, int tolerance) {
		var world = this.getWorld();
		PhantomActor crashActor = new PhantomActor();
		int toleranceSize = tolerance;

		if (toleranceSize <= 0) {
			toleranceSize = tolerance * -1;
		}

		if (toleranceSize < 5) {
			toleranceSize = 5;
		}

		switch (direction) {
			case ABOVE:
				crashActor.setSize(this.getHalfSizeX(), toleranceSize);
				world.addObject(crashActor, this.getX(), this.getTop() + tolerance / 2);
			case BELOW:
				crashActor.setSize(this.getHalfSizeX(), toleranceSize);
				world.addObject(crashActor, this.getX(), this.getBottom() - tolerance / 2);
			case LEFT:
				crashActor.setSize(toleranceSize, this.getHalfSizeY());
				world.addObject(crashActor, this.getLeft() + tolerance / 2, this.getY());
			case RIGHT:
				crashActor.setSize(toleranceSize, this.getHalfSizeY());
				world.addObject(crashActor, this.getRight() - tolerance / 2, this.getY());
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
