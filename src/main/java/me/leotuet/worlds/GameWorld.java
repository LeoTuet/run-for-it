package me.leotuet.worlds;

import greenfoot.World;
import me.leotuet.actors.Player;
import me.leotuet.utils.PhantomActor;
import me.leotuet.actors.Block;
import me.leotuet.actors.GameMap;

public class GameWorld extends World {

	public GameWorld(GameMap map, Player player) {
		super(map.getViewportWidth(), map.getViewportHeight(), 1, false);
		this.setPaintOrder(Player.class, Block.class, PhantomActor.class);

		this.addObject(player, Block.BLOCK_SIZE, Block.BLOCK_SIZE);
		this.addObject(map, map.getViewportWidth() / 2, map.getViewportHeight() / 2);
		map.loadMap();
	}
}
