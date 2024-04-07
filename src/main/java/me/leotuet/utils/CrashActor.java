package me.leotuet.utils;

import greenfoot.Actor;

public class CrashActor extends Actor {

	private int width;
	private int height;

	public CrashActor() {
		// this.getImage().setTransparency(0);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.getImage().scale(this.width, this.height);
	}

	@SuppressWarnings("unchecked")
	public <T extends BoundingActor> T getIntersecting(Class<T> actor) {
		return (T) this.getOneIntersectingObject(actor);
	}

	public void remove() {
		this.getWorld().removeObject(this);
	}

}
