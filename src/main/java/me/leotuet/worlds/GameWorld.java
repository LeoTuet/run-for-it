package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Bean;
import me.leotuet.actors.GameMap;

public class GameWorld extends World {

	public GameWorld(GameMap map) {
		super(map.getMapWidth(), map.getMapHeight(), 1);
		this.addObject(map, 0, 0);
		map.render();

		var bean = new Bean();
		this.addObject(bean, getWidth() / 2, getHeight() / 2);

	}

}
