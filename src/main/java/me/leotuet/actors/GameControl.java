package me.leotuet.actors;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import javafx.scene.input.KeyCode;
import me.leotuet.utils.MapParser;
import me.leotuet.utils.Button;
import me.leotuet.utils.Direction;
import me.leotuet.utils.KeyHelper;
import me.leotuet.utils.Menu;
import me.leotuet.worlds.GameWorld;

public class GameControl extends Actor {
	private boolean isMenuOpen = false;
	private Player player;
	private Menu controlMenu;

	public GameControl(Player player) {
		this.getImage().setTransparency(0);
		this.player = player;
	}

	public void act() {
		handleGameOver();
		handleWin();
		if (KeyHelper.isKeyPressedDown(KeyCode.ESCAPE, "escape")) {
			if (!isMenuOpen) {
				openControlMenu();
			} else if (this.controlMenu != null) {
				closeMenu(this.controlMenu);
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
		if (player.getY() >= this.getWorld().getHeight() + player.getHalfSizeY() || player.isTouching(Enemy.class)) {
			var gameOverScreen = new Menu("images/game-over.png", this.createRestartButton());
			this.openMenu(gameOverScreen, 50);
		}
	}

	private void handleWin() {
		if (player.isIntersecting(Direction.RIGHT, player.getMovementSpeed(), Gate.class)) {
			var winMenu = new Menu("images/win.png", this.createRestartButton());
			this.openMenu(winMenu, 0);
		}
	}

	private void openControlMenu() {
		var restartButton = this.createRestartButton();
		this.controlMenu = new Menu("images/menu.png", restartButton);
		var cancelButton = new Button("Cancel") {
			public void onClick() {
				closeMenu(controlMenu);
			}
		};
		controlMenu.addButton(cancelButton);
		this.openMenu(this.controlMenu, 0);
	}

	private void closeMenu(Menu menu) {
		isMenuOpen = false;
		this.player.setFreezeDirection(Direction.NONE);
		menu.closeMenu();
	}

	private void openMenu(Menu menu, int offset) {
		isMenuOpen = true;
		this.player.setFreezeDirection(Direction.ALL);
		menu.openMenu(this.getWorld(), offset);
	}

	private Button createRestartButton() {
		return new Button("Restart") {
			public void onClick() {
				resetWorld();
			}
		};
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
