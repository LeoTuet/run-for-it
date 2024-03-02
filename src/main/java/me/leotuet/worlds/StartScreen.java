package me.leotuet.worlds;

import greenfoot.Greenfoot;
import greenfoot.World;
import me.leotuet.actors.Bean;
import me.leotuet.actors.GameMap;
import me.leotuet.parser.MapParser;

public class StartScreen extends World {

	public StartScreen() {
		super(0, 0, 0);

		try {
			var bean = new Bean();
			var mapParser = new MapParser();
			var map = new GameMap(mapParser.parseDefaultMap(), bean);
			var gameWorld = new GameWorld(map, bean);
			Greenfoot.setWorld(gameWorld);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
