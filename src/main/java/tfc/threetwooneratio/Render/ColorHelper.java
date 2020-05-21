package tfc.threetwooneratio.Render;

public class ColorHelper {
	int value;
	public ColorHelper(int rgb) {
		value = 0xff000000 | rgb;
	}
	public ColorHelper(int rgba, boolean hasalpha) {
		if (hasalpha) { value = rgba; } else { value = 0xff000000 | rgba; }
	}
	public int getRGB() {
		return value;
	}
	public int getRed() {
		return (getRGB() >> 16) & 0xFF;
	}
	public int getGreen() {
		return (getRGB() >> 8) & 0xFF;
	}
	public int getBlue() {
		return (getRGB() >> 0) & 0xFF;
	}
	public int getAlpha() {
		return (getRGB() >> 24) & 0xff;
	}
}
