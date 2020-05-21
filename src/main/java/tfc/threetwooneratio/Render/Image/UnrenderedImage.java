package tfc.threetwooneratio.Render.Image;

import tfc.threetwooneratio.Render.ColorHelper;

public class UnrenderedImage {
	int width;
	int pixels;
	ColorHelper[] colors;
	
	public UnrenderedImage(int width,int height) {
		this.width = width;
		this.pixels=width*height;
		colors=new ColorHelper[pixels];
	}
	
	public void setColor(int x,int y,ColorHelper color) {
		colors[x+(y*width)]=color;
	}
	
	public ColorHelper getColor(int x,int y) {
		return colors[x+(y*width)];
	}
	
	public void setRGB(int x,int y,int color) {
		colors[x+(y*width)]=new ColorHelper(color);
	}
	
	public int getRGB(int x,int y) {
		return colors[x+(y*width)].getRGB();
	}
}
