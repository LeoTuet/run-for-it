package me.leotuet.utils;

import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.World;

public class Menu extends Actor {

	PhantomActor menu;
	ArrayList<Button> buttons = new ArrayList<Button>();

	public Menu(String imagePath, Button... buttons) {
		this.menu = new PhantomActor(imagePath, 350, 480);
		for (Button button : buttons) {
			this.buttons.add(button);
		}
	}

	public void addButton(Button button) {
		this.buttons.add(button);
	}

	public void openMenu(World world, int buttonPlacement) {
		world.addObject(menu, world.getWidth() / 2, world.getHeight() / 2);
		for (int i = 0; i < buttons.size(); i++) {
			var offset = i == 0 ? buttonPlacement : 100;
			buttons.get(i).addToWorld(world, menu.getX(), menu.getY() + offset);
		}
	}

	public void closeMenu() {
		for (Button button : buttons) {
			button.remove();
		}
		menu.remove();
	}

}
