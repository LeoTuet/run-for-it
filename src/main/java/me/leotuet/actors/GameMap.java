package me.leotuet.actors;

import java.util.ArrayList;

import org.json.JSONArray;

import greenfoot.Actor;
import me.leotuet.utils.BoundingActor;
import me.leotuet.utils.Direction;
import me.leotuet.worlds.GameWorld;

public class GameMap extends Actor {

	public static final int VIEWPORT_BLOCK_WIDTH = 16;
	public static final int VIEWPORT_BLOCK_HEIGHT = 9;

	private JSONArray mapArray;
	private Player player;
	private ArrayList<BoundingActor[]> map;
	private Background background;

	public GameMap(JSONArray mapArray, Player player) {
		this.getImage().setTransparency(0);
		this.mapArray = mapArray;
		this.player = player;
	}

	public void act() {
		if (player.getFreezeDirection() == Direction.ALL) {
			return;
		}

		player.setFreezeDirection(Direction.NONE);

		// move map instead of player
		if (shouldMoveMapLeft() && player.isMovingLeft() && player.isAllowedToMove(Direction.LEFT)) {
			player.setFreezeDirection(Direction.LEFT);
			moveMap(-player.getMovementSpeed());
			if (this.background != null) {
				this.background.move(player.getMovementSpeed());
			}
		}

		if (shouldMoveMapRight() && player.isMovingRight() && player.isAllowedToMove(Direction.RIGHT)) {
			player.setFreezeDirection(Direction.RIGHT);
			moveMap(player.getMovementSpeed());
			if (this.background != null) {
				this.background.move(-player.getMovementSpeed());
			}
		}

		// prevent player from moving out of bounds
		if (isActorOnLeftEdge(player, player.getMovementSpeed())) {
			player.setFreezeDirection(Direction.LEFT);
		}

		if (isBoundActorOnRightEdge(player, player.getMovementSpeed())) {
			player.setFreezeDirection(Direction.RIGHT);
		}
	}

	public void loadMap() {
		map = new ArrayList<BoundingActor[]>();
		// TODO: change to render method which only renders the blocks that are visible
		for (int columnIndex = 0; columnIndex < this.mapArray.length(); columnIndex++) {
			renderColumn(columnIndex);
		}
	}

	public int getViewportHeight() {
		return VIEWPORT_BLOCK_HEIGHT * GameWorld.BLOCK_SIZE;
	}

	public int getViewportWidth() {
		return VIEWPORT_BLOCK_WIDTH * GameWorld.BLOCK_SIZE;
	}

	public int getMapWidth() {
		return mapArray.length() * GameWorld.BLOCK_SIZE;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	private void renderColumn(int columnIndex) {
		var column = mapArray.getJSONArray(columnIndex);
		var blockColumn = new BoundingActor[VIEWPORT_BLOCK_HEIGHT];
		var world = this.getWorld();

		for (int rowIndex = 0; rowIndex < VIEWPORT_BLOCK_HEIGHT; rowIndex++) {
			var blockType = column.getInt(rowIndex);

			switch (blockType) {
				case 1:
					blockColumn[rowIndex] = new Block();
					break;
				case 2:
					blockColumn[rowIndex] = new Gate();
					break;
				case 3:
					blockColumn[rowIndex] = new Enemy();
					break;
				case 4:
					blockColumn[rowIndex] = new PowerUp();
					break;
			}

			if (blockColumn[rowIndex] != null) {
				world.addObject(blockColumn[rowIndex], getWorldPlacement(columnIndex),
						getWorldPlacement(rowIndex));
			}

		}

		map.add(blockColumn);
	}

	private int getWorldPlacement(int index) {
		return index * GameWorld.BLOCK_SIZE + GameWorld.HALF_BLOCK_SIZE;
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
		return player.getX() < GameWorld.BLOCK_SIZE * 2 && isEdgeOutOfView;
	}

	private BoundingActor getNonNullBlock(BoundingActor[] blocks) {
		for (var block : blocks) {
			if (block != null) {
				return block;
			}
		}
		return null;
	}

	private void moveMap(int direction) {
		for (var column : map) {
			for (var actor : column) {
				if (actor != null && actor.getWorld() != null) {
					actor.setLocation(actor.getX() - direction, actor.getY());
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
