package me.leotuet.utils;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class PhantomActor extends Actor {

	private int width;
	private int height;

	public void act() {
	};

	public PhantomActor() {
		this.getImage().setTransparency(0);
	}

	public PhantomActor(String text) {
		this.setImage(new GreenfootImage(text, 20, Color.GRAY, new Color(0, 0, 0, 0)));
	}

	public PhantomActor(String backgroundPath, int width, int height) {
		var image = new GreenfootImage(backgroundPath);
		this.setImage(image);
		this.setSize(width, height);
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
