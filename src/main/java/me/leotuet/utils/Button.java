package me.leotuet.utils;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

public abstract class Button extends Actor {
	PhantomActor textActor;
	String text;

	public abstract void onClick();

	public Button(String text) {
		var image = new GreenfootImage("images/button.png");
		image.scale(192, 64);
		this.setImage(image);
		this.text = text;
	}

	public void act() {
		handleClick(this);
	}

	public void addToWorld(World world, int x, int y) {
		world.addObject(this, x, y);
		this.setTextActor(this.text);
	}

	private void setTextActor(String text) {
		if (this.textActor != null) {
			this.textActor.remove();
		}

		this.textActor = new PhantomActor(text) {
			@Override
			public void act() {
				handleClick(this);
			}
		};
		this.textActor.setSize(150, 40);
		this.getWorld().addObject(textActor, this.getX(), this.getY());
	}

	public void remove() {
		this.textActor.remove();
		this.getWorld().removeObject(this);
	}

	private <T extends Actor> void handleClick(T obj) {
		if (Greenfoot.mouseClicked(obj)) {
			this.onClick();
		}
	}
}
