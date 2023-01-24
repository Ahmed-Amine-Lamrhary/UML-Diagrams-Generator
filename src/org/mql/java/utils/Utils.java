package org.mql.java.utils;

import java.awt.Color;

public class Utils {

	public static Color rgbaColor(Color color, int opacity) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}
	
	public static Color rgbColor(int r, int g, int b) {
		return new Color(r, g, b);
	}
	
	public static String getPrintableTitle(String title) {
		String out = "-".repeat(title.length()) + "\n";
		out += title + "\n";
		out += "-".repeat(title.length()) + "\n\n";
		
		return out;
	}
	
	public static String getPrintableSubtitle(String title) {
		String out = title + "\n";
		out += "-".repeat(title.length()) + "\n\n";
		
		return out;
	}
}
