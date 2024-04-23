package me.leotuet.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;

public class MapParser {

	public MapParser() {
	}

	public JSONArray parseDefaultMap() throws IOException, URISyntaxException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("maps/default.json");
		String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
		return new JSONArray(text);
	}

}
