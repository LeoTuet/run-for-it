package me.leotuet.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

public class MapParser {

	public MapParser() {
	}

	public JSONArray parseDefaultMap() throws IOException, URISyntaxException {
		var url = MapParser.class.getClassLoader().getResource("maps/default.json");
		var content = Files.readString(Paths.get(url.toURI())).trim();
		return new JSONArray(content);
	}

}
