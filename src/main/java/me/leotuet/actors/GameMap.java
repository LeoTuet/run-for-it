package me.leotuet.actors;

import java.util.ArrayList;

import org.json.JSONArray;

import greenfoot.Actor;
import me.leotuet.utils.BoundingActor;
import me.leotuet.utils.Direction;

public class GameMap extends Actor {

	public static final int VIEWPORT_BLOCK_WIDTH = 16;
	public static final int VIEWPORT_BLOCK_HEIGHT = 9;

	private JSONArray mapArray;
	private Player player;
	private ArrayList<Block[]> map;
	private Background background;

	public GameMap(JSONArray mapArray, Player player) {
		this.mapArray = mapArray;
		this.player = player;
	}

	public void loadMap() {
		var world = this.getWorld();
		this.loadFullMapArray();
		this.background = new Background(world, "./images/background.png", this.getViewportWidth() * 2, this.getMapWidth());
		world.addObject(background, 0, 0);
	}

	public void act() {
		player.setFreeze(Direction.NONE);

		// move map instead of player
		if (shouldMoveMapLeft() && player.isMovingLeft() && player.isAllowedToMove(Direction.LEFT)) {
			player.setFreeze(Direction.LEFT);
			moveMap(-player.getMovementSpeed());
			this.background.move(player.getMovementSpeed());
		}

		if (shouldMoveMapRight() && player.isMovingRight() && player.isAllowedToMove(Direction.RIGHT)) {
			player.setFreeze(Direction.RIGHT);
			moveMap(player.getMovementSpeed());
			this.background.move(-player.getMovementSpeed());
		}

		// prevent player from moving out of bounds
		if (isActorOnLeftEdge(player, player.getMovementSpeed())) {
			player.setFreeze(Direction.LEFT);
		}

		if (isBoundActorOnRightEdge(player, player.getMovementSpeed())) {
			player.setFreeze(Direction.RIGHT);
		}
	}

	public int getViewportHeight() {
		return VIEWPORT_BLOCK_HEIGHT * Block.BLOCK_SIZE;
	}

	public int getViewportWidth() {
		return VIEWPORT_BLOCK_WIDTH * Block.BLOCK_SIZE;
	}

	public int getMapWidth() {
		return map.size() * Block.BLOCK_SIZE;
	}

	private void loadFullMapArray() {
		map = new ArrayList<Block[]>();
		// TODO: change to render method which only renders the blocks that are visible
		for (int columnIndex = 0; columnIndex < this.mapArray.length(); columnIndex++) {
			renderColumn(columnIndex);
		}
	}

	private void renderColumn(int columnIndex) {
		var column = mapArray.getJSONArray(columnIndex);
		var blockColumn = new Block[VIEWPORT_BLOCK_HEIGHT];
		var world = this.getWorld();

		for (int rowIndex = 0; rowIndex < VIEWPORT_BLOCK_HEIGHT; rowIndex++) {
			var blockType = column.getInt(rowIndex);
			if (blockType == 1) {
				blockColumn[rowIndex] = new Block();
				world.addObject(blockColumn[rowIndex], getWorldPlacement(columnIndex),
						getWorldPlacement(rowIndex));
			}
		}

		map.add(blockColumn);
	}

	private int getWorldPlacement(int index) {
		return index * Block.BLOCK_SIZE + Block.HALF_BLOCK_SIZE;
	}

	private boolean shouldMoveMapRight() {
		var world = this.getWorld();
		var block = getNonNullBlock(map.get(map.size() - 1));
		// Movement speed needed as tolerance
		var isEdgeOutOfView = isBoundActorOnRightEdge(block, player.getMovementSpeed());
		return player.getX() > world.getWidth() / 2 && isEdgeOutOfView;
	}

	private boolean shouldMoveMapLeft() {
		var block = getNonNullBlock(map.get(0));
		var isEdgeOutOfView = isActorOnLeftEdge(block, player.getMovementSpeed());
		return player.getX() < Block.BLOCK_SIZE * 2 && isEdgeOutOfView;
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

	private <T extends BoundingActor> boolean isBoundActorOnRightEdge(T actor, int tolerance) {
		return actor.getX() >= getViewportWidth() - actor.getHalfSizeX() / 2 + tolerance;
	}

	private <T extends BoundingActor> boolean isActorOnLeftEdge(T actor, int tolerance) {
		return actor.getX() <= actor.getHalfSizeX() - tolerance;
	}

}
