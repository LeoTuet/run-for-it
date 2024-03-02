package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Bean;
import me.leotuet.actors.GameMap;

public class GameWorld extends World {

	public GameWorld(GameMap map, Bean player) {
		super(map.getMapWidth(), map.getMapHeight(), 1, false);
		this.addObject(map, map.getMapWidth() / 2, map.getMapHeight() / 2);
		map.render();

		this.addObject(player, getWidth() / 2, getHeight() / 2);

	}

}
