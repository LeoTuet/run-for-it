package me.leotuet.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

import me.leotuet.actors.GameMap;

public class MapParser {

	public MapParser() {
	}

	public GameMap parseDefaultMap() throws IOException, URISyntaxException {
		var url = MapParser.class.getClassLoader().getResource("maps/default.json");
		var content = Files.readString(Paths.get(url.toURI())).trim();
		var jsonArray = new JSONArray(content);
		return new GameMap(jsonArray);
	}

}
