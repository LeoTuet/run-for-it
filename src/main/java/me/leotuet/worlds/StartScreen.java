package me.leotuet.worlds;

import greenfoot.Greenfoot;
import greenfoot.World;
import me.leotuet.actors.Player;
import me.leotuet.actors.GameMap;
import me.leotuet.utils.MapParser;

public class StartScreen extends World {

	public StartScreen() {
		super(1, 1, 1);

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
