package me.leotuet.utils;

public class Location {

	public int x;
	public int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "x-coordinate: " + this.x + "\n" + "y-coordinate: " + this.y;
	}

}
