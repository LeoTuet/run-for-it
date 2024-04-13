package me.leotuet.utils;

import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import javafx.scene.input.KeyCode;

public class KeyHelper {

	public static boolean isKeyPressedDown(KeyCode keyCode, String key) {
		var isShiftPressed = Greenfoot.isKeyDown(key);
		WorldHandler.getInstance().getKeyboardManager().keyReleased(keyCode, key);
		return isShiftPressed;
	}
}
