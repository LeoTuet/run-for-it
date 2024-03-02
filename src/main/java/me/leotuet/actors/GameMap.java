package me.leotuet.actors;

import java.util.ArrayList;

import org.json.JSONArray;

import greenfoot.Actor;

public class GameMap extends Actor {

	private static final int VIEWPORT_BLOCK_WIDTH = 16;
	private static final int VIEWPORT_BLOCK_HEIGHT = 9;

	private JSONArray mapArray;
	private ArrayList<Block[]> map;

	public GameMap(JSONArray mapArray) {
		this.mapArray = mapArray;
	}

	public void render() {
		map = new ArrayList<Block[]>();
		for (int columnIndex = 0; columnIndex < VIEWPORT_BLOCK_WIDTH; columnIndex++) {
			renderColumn(columnIndex);
		}
	}

	public void renderColumn(int columnIndex) {
		var column = mapArray.getJSONArray(columnIndex);
		var blockColumn = new Block[VIEWPORT_BLOCK_HEIGHT];

		for (int rowIndex = 0; rowIndex < VIEWPORT_BLOCK_HEIGHT; rowIndex++) {
			var blockType = column.getInt(rowIndex);
			if (blockType == 1) {
				blockColumn[rowIndex] = new Block();
				var world = this.getWorld();

				world.addObject(blockColumn[rowIndex], getWorldPlacement(columnIndex),
						getWorldPlacement(rowIndex));
			}
		}

		map.add(blockColumn);
	}

	private int getWorldPlacement(int index) {
		return index * Block.BLOCK_SIZE + Block.BLOCK_SIZE / 2;
	}

	public int getMapHeight() {
		return VIEWPORT_BLOCK_HEIGHT * Block.BLOCK_SIZE;
	}

	public int getMapWidth() {
		return VIEWPORT_BLOCK_WIDTH * Block.BLOCK_SIZE;
	}

}
