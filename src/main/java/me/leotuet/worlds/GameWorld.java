package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Bean;
import me.leotuet.actors.Block;
import me.leotuet.actors.GameMap;

public class GameWorld extends World {

	public GameWorld(GameMap map, Bean player) {
		super(map.getMapWidth(), map.getMapHeight(), 1, false);

		this.addObject(map, map.getMapWidth() / 2, map.getMapHeight() / 2);
		this.addObject(player, Block.BLOCK_SIZE, Block.BLOCK_SIZE);

		map.render();
	}

}
