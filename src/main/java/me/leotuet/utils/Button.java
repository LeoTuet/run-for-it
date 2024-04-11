package me.leotuet.utils;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Button extends Actor {
	PhantomActor text;

	public abstract void onClick();

	public Button() {
		var image = new GreenfootImage("./images/button.png");
		image.scale(192, 64);
		this.setImage(image);
	}

	public void act() {
		handleClick(this);
	}

	public void setText(String label) {
		if (this.text != null) {
			this.text.remove();
		}

		this.text = new PhantomActor(label) {
			@Override
			public void act() {
				handleClick(this);
			}
		};
		this.text.setSize(150, 40);
		this.getWorld().addObject(text, this.getX(), this.getY());
	}

	public void remove() {
		this.text.remove();
		this.getWorld().removeObject(this);
	}

	private <T extends Actor> void handleClick(T obj) {
		if (Greenfoot.mouseClicked(obj)) {
			this.onClick();
		}
	}
}
