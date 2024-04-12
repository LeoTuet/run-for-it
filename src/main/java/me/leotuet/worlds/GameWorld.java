package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Player;
import me.leotuet.actors.GameMap;
import me.leotuet.actors.Background;
import me.leotuet.actors.GameControl;

public class GameWorld extends World {

	public static final int BLOCK_SIZE = 64;
	public static final int HALF_BLOCK_SIZE = BLOCK_SIZE / 2;

	public GameWorld(GameMap map, Player player) {
		super(map.getViewportWidth(), map.getViewportHeight(), 1, false);
		var background = new Background(this, "./images/background.png", map.getViewportWidth() * 2, map.getMapWidth());
		this.addObject(background, 0, 0);
		map.setBackground(background);

		this.addObject(player, BLOCK_SIZE, this.getHeight() - BLOCK_SIZE - player.getHalfSizeY());
		this.addObject(map, map.getViewportWidth() / 2, map.getViewportHeight() / 2);

		var GameControl = new GameControl(player);
		this.addObject(GameControl, 0, 0);
		map.loadMap();
	}
}
