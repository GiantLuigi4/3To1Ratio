package tfc.threetwooneratio.Render.Image;

import net.minecraft.client.renderer.texture.NativeImage;

import java.util.Date;

public class ImageUnrenderer {
	int x=0;
	int y=0;
	NativeImage source;
	public UnrenderedImage dest;
	
	public ImageUnrenderer(NativeImage source) {
		this.source = source;
		this.dest=new UnrenderedImage(source.getWidth(),source.getHeight());
	}
	
	public boolean deUnrender() {
		Date started=new Date();
		while (new Date().getTime()==started.getTime()) {
			if (x==dest.width) {
				y++;
				x=0;
			}
			dest.setRGB(x,y,source.getPixelRGBA(x,y));
			x++;
		}
		return (x+(y*dest.width))>=(source.getWidth()*source.getHeight());
	}
	
	public void close() {
		source=null;
	}
}
