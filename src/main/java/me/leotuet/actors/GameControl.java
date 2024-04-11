package me.leotuet.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import me.leotuet.utils.MapParser;
import me.leotuet.utils.PhantomActor;
import me.leotuet.utils.Button;
import me.leotuet.utils.Direction;
import me.leotuet.worlds.GameWorld;

public class GameControl extends Actor {
	private boolean isMenuOpen = false;
	private Player player;

	public GameControl(Player player) {
		this.getImage().setTransparency(0);
		this.player = player;
	}

	public void act() {
		handleGameOver();
		handleWin();
		if (Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("h")) {
			if (!isMenuOpen) {
				openControlMenu();
			}
		}

		if (Greenfoot.isKeyDown("r")) {
			resetWorld();
		}

		if (Greenfoot.isKeyDown("enter") && isMenuOpen) {
			resetWorld();
		}
	}

	private void handleGameOver() {
		if (player.getY() >= this.getWorld().getHeight() + player.getHalfSizeY()) {
			var gameOverScreen = this.openMenu("./images/game-over.png");
			this.createRestartButton(gameOverScreen, 50);
		}
	}

	private void handleWin() {
		if (player.isIntersecting(Direction.RIGHT, player.getMovementSpeed(), Gate.class)) {
			var winMenu = this.openMenu("./images/win.png");
			this.createRestartButton(winMenu, 0);
		}
	}

	private void openControlMenu() {
		var controlMenu = this.openMenu("./images/menu.png");
		var restartButton = this.createRestartButton(controlMenu, 0);
		var cancelButton = new Button() {
			public void onClick() {
				closeMenu(controlMenu, restartButton, this);
			}
		};
		this.getWorld().addObject(cancelButton, controlMenu.getX(), controlMenu.getY() + 100);
		cancelButton.setText("Cancel");
	}

	private void closeMenu(PhantomActor menu, Button... buttons) {
		isMenuOpen = false;
		menu.remove();
		for (var button : buttons) {
			button.remove();
		}
		this.player.setFreezeDirection(Direction.NONE);
	}

	private PhantomActor openMenu(String backgroundPath) {
		isMenuOpen = true;
		this.player.setFreezeDirection(Direction.ALL);
		var world = this.getWorld();
		var menu = new PhantomActor(backgroundPath, 350, 480);
		this.getWorld().addObject(menu, world.getWidth() / 2, world.getHeight() / 2);
		return menu;
	}

	private Button createRestartButton(PhantomActor menu, int offset) {
		var resetButton = new Button() {
			public void onClick() {
				resetWorld();
			}
		};
		this.getWorld().addObject(resetButton, menu.getX(), menu.getY() + offset);
		resetButton.setText("Restart");
		return resetButton;
	}

	private void resetWorld() {
		try {
			var player = new Player();
			var mapParser = new MapParser();
			var map = new GameMap(mapParser.parseDefaultMap(), player);
			var gameWorld = new GameWorld(map, player);
			Greenfoot.setWorld(gameWorld);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
