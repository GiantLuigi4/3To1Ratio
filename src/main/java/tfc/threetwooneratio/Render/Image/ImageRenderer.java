package tfc.threetwooneratio.Render.Image;

import net.minecraft.client.renderer.texture.NativeImage;

import java.util.Date;

public class ImageRenderer {
	int x=0;
	int y=0;
	UnrenderedImage source;
	public NativeImage dest;
	
	public ImageRenderer(UnrenderedImage source) {
		this.source = source;
		this.dest=new NativeImage(source.width,source.pixels/source.width,true);
	}
	
	public boolean reRender() {
		try {
			Date started=new Date();
			while (new Date().getTime()==started.getTime()) {
				if (x==source.width) {
					y++;
					x=0;
				}
				dest.setPixelRGBA(x,y,source.getRGB(x,y));
				x++;
			}
		} catch (Exception err) {
		}
		return (x+(y*dest.getWidth()))==(source.width*(source.pixels/source.width));
	}
}
