package me.leotuet.actors;

import greenfoot.Actor;

public class Block extends Actor {

	public static final int BLOCK_SIZE = 64;

	public Block() {
		this.getImage().scale(BLOCK_SIZE, BLOCK_SIZE);
	}

	public void act() {
	}

}
