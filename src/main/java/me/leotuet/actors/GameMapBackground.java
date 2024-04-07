package me.leotuet.actors;

import greenfoot.Actor;

public class GameMapBackground extends Actor {

	public GameMapBackground(GameMap map, int viewportMultiple) {
		this.getImage().scale(map.getMapWidth() * viewportMultiple, map.getMapHeight());
	}

	public void move() {
		this.setLocation(this.getX() - 1, this.getY());
	}

}
