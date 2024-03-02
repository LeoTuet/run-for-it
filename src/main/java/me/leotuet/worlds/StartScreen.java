package me.leotuet.worlds;

import greenfoot.Greenfoot;
import greenfoot.World;
import me.leotuet.parser.MapParser;

public class StartScreen extends World {

	public StartScreen() {
		super(600, 400, 1);

		var mapParser = new MapParser();
		try {
			var gameMap = mapParser.parseDefaultMap();
			var gameWorld = new GameWorld(gameMap);
			Greenfoot.setWorld(gameWorld);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
