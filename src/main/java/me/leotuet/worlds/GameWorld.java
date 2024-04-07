package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Player;
import me.leotuet.actors.Block;
import me.leotuet.actors.GameMap;

public class GameWorld extends World {

	public GameWorld(GameMap map, Player player) {
		super(map.getMapWidth(), map.getMapHeight(), 1, false);

		this.addObject(map, map.getMapWidth() / 2, map.getMapHeight() / 2);
		map.renderMap();

		this.addObject(player, Block.BLOCK_SIZE, Block.BLOCK_SIZE);

	}

}
