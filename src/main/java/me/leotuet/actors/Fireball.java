package me.leotuet.actors;

import me.leotuet.utils.BoundingActor;

public class Fireball extends BoundingActor {

	private static final long MAX_TRAVEL_DISTANCE = 1000;

	private int speed;
	private int travelDistance = 0;

	public Fireball(int speed) {
		super(32);
		this.speed = speed;
	}

	public void act() {
		move();
	}

	private void move() {
		if (this.isTouching(Block.class) || travelDistance >= MAX_TRAVEL_DISTANCE) {
			getWorld().removeObject(this);
			return;
		}

		var enemy = getOneIntersectingObject(Enemy.class);
		if (enemy != null) {
			this.getWorld().removeObject(enemy);
			this.getWorld().removeObject(this);
			return;
		}

		setLocation(getX() + this.speed, getY());
		travelDistance += this.speed;

	}

}
