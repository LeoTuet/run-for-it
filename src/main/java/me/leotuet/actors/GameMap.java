package me.leotuet.actors;

import java.util.ArrayList;

import org.json.JSONArray;

import greenfoot.Actor;

public class GameMap extends Actor {

	private static final int VIEWPORT_BLOCK_WIDTH = 16;
	private static final int VIEWPORT_BLOCK_HEIGHT = 9;

	private JSONArray mapArray;
	private Bean player;
	private ArrayList<Block[]> map;

	public GameMap(JSONArray mapArray, Bean player) {
		this.mapArray = mapArray;
		this.player = player;
		this.getImage().scale(getMapWidth(), getMapHeight());
	}

	public void act() {
		player.preventMove(Direction.NONE);

		if (shouldMoveMapLeft() && player.isMovingLeft()) {
			player.preventMove(Direction.LEFT);
			moveMap(-player.getMovementSpeed());
		}

		if (shouldMoveMapRight() && player.isMovingRight()) {
			player.preventMove(Direction.RIGHT);
			moveMap(player.getMovementSpeed());
		}
	}

	public void render() {
		map = new ArrayList<Block[]>();
		// TODO: change to render method which only renders the blocks that are visible
		for (int columnIndex = 0; columnIndex < this.mapArray.length(); columnIndex++) {
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
		return index * Block.BLOCK_SIZE + Block.HALF_BLOCK_SIZE;
	}

	public int getMapHeight() {
		return VIEWPORT_BLOCK_HEIGHT * Block.BLOCK_SIZE;
	}

	public int getMapWidth() {
		return VIEWPORT_BLOCK_WIDTH * Block.BLOCK_SIZE;
	}

	public boolean shouldMoveMapRight() {
		var block = getNonNullBlock(map.get(map.size() - 1));
		var isOnEdge = block.getX() <= getMapWidth() - Block.HALF_BLOCK_SIZE;
		return player.getX() > getMapWidth() - 2 * Block.BLOCK_SIZE && !isOnEdge;
	}

	public boolean shouldMoveMapLeft() {
		var block = getNonNullBlock(map.get(0));
		var isOnEdge = block.getX() >= Block.HALF_BLOCK_SIZE;
		return player.getX() < Block.BLOCK_SIZE * 2 && !isOnEdge;
	}

	private Block getNonNullBlock(Block[] blocks) {
		for (var block : blocks) {
			if (block != null) {
				return block;
			}
		}
		return null;
	}

	private void moveMap(int direction) {
		for (var column : map) {
			for (var block : column) {
				if (block != null) {
					block.setLocation(block.getX() - direction, block.getY());
				}
			}
		}
	}

}
