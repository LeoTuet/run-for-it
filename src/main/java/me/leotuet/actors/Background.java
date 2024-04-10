package me.leotuet.actors;

import java.util.ArrayList;

import greenfoot.Actor;
import greenfoot.World;
import me.leotuet.utils.PhantomActor;

public class Background extends Actor {

	ArrayList<PhantomActor> phantomActors;

	public Background(World world, String backgroundPath, int backgroundWidth, int totalWidth) {
		phantomActors = new ArrayList<>();
		for (int i = 0; i <= totalWidth / backgroundWidth; i++) {
			var phantomActor = new PhantomActor(backgroundPath, backgroundWidth, world.getHeight());
			phantomActors.add(phantomActor);
			world.addObject(phantomActor, backgroundWidth * i, world.getHeight() / 2);
		}
	}

	public void move(int amount) {
		for (int i = 0; i < phantomActors.size(); i++) {
			phantomActors.get(i).move(amount);
		}
	}

}
