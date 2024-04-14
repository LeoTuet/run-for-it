package me.leotuet.utils;

import java.util.ArrayList;

import greenfoot.World;

public class Menu extends PhantomActor {

	ArrayList<Button> buttons = new ArrayList<Button>();

	public Menu(String imagePath, Button... buttons) {
		super(imagePath, 350, 480);
		for (Button button : buttons) {
			this.buttons.add(button);
		}
	}

	public void addButton(Button button) {
		this.buttons.add(button);
	}

	public void openMenu(World world, int buttonPlacement) {
		world.addObject(this, world.getWidth() / 2, world.getHeight() / 2);
		for (int i = 0; i < buttons.size(); i++) {
			var offset = i == 0 ? buttonPlacement : 100;
			buttons.get(i).addToWorld(world, this.getX(), this.getY() + offset);
		}
	}

	public void closeMenu() {
		for (Button button : buttons) {
			button.remove();
		}
		this.remove();
	}

}
