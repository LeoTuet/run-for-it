package me.leotuet.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Bean extends Actor {

	public Bean() {
		this.getImage().scale(64, 64);
	}

	public void act() {
		move();
	}

	public void move() {
		if (Greenfoot.isKeyDown("up")) {
			this.setLocation(getX(), getY() - 2);
		}

		if (Greenfoot.isKeyDown("down") && !isTouchingBlock()) {
			this.setLocation(getX(), getY() + 2);
		}

		if (Greenfoot.isKeyDown("right")) {
			this.setLocation(getX() + 2, getY());
		}

		if (Greenfoot.isKeyDown("left")) {
			this.setLocation(getX() - 2, getY());
		}
	}

	public boolean isTouchingBlock() {
		return this.isTouching(Block.class);
	}
}
