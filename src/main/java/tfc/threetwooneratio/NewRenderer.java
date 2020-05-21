package tfc.threetwooneratio;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import tfc.threetwooneratio.Render.ColorHelper;
import tfc.threetwooneratio.Render.Image.DynamicTexture;

public class NewRenderer {
    DynamicTexture texture;
    
    public NewRenderer(NativeImage texture) {
        this.texture = new DynamicTexture(texture);
    }
    
    public void renderNativeImage(NativeImage image) {
        RenderSystem.disableTexture();
        float offsetX=8;
        float offsetY=8;
        float darkness=0.25f;
        drawRect(0,0,image.getWidth()+offsetX+offsetX,image.getHeight()+offsetY+offsetY,darkness,darkness,darkness,1);
        int x=0;
        int y=0;
        try {
            NativeImage texture1=new NativeImage(image.getWidth(),image.getHeight(),true);
            texture1.copyImageData(image);
            if (texture1.getHeight()!=texture.getTextureData().getHeight()) {
                texture.close();
                texture=new DynamicTexture(texture1);
            }
            if (texture1.getWidth()!=texture.getTextureData().getWidth()) {
                texture.close();
                texture=new DynamicTexture(texture1);
            }
            texture.setTextureData(texture1);
            texture.updateDynamicTexture();
            RenderSystem.enableTexture();
            drawTexturedRect(offsetX,offsetY,0,0,256,256,image.getWidth(),image.getHeight(),1,1,1,1);
            texture1.close();
        } catch (Exception ignored) {
        }
//        boolean checker=false;
//        try {
//            while (y<image.getHeight()) {
//                while (x<image.getWidth()) {
//                    x+=2;
//                    try {
//                        drawOne(image,x,y,offsetX,offsetY);
//                    } catch (Exception err) {}
//                }
//                y+=1;
//                x=checker?0:1;
//                checker=!checker;
//            }
//        } catch (Exception ignored) {}
//        for (int y=0;y<image.getHeight();y+=1) {
//            for (int x=0;x<image.getWidth();x+=1) {
//                try {
//                    drawOne(image,x,y,offsetX,offsetY);
//                } catch (Exception err) {}
//            }
//            yInterval=16;
//        }
//        RenderSystem.enableTexture();
    }
    public static int draw(NativeImage img,int x,int y,float offsetX,float offsetY) {
        int z=0;
        try {
            z+=drawOne(img,x+z,y,offsetX,offsetY);
            z+=drawOne(img,x+z,y,offsetX,offsetY);
            z+=drawOne(img,x+z,y,offsetX,offsetY);
            z+=drawOne(img,x+z,y,offsetX,offsetY);
        } catch (Exception err) {}
        return z+1;
    }
    public static int drawOne(NativeImage img,int x,int y,float offsetX,float offsetY) {
        ColorHelper helper=new ColorHelper(img.getPixelRGBA(x,y),false);
        drawRect(x+offsetX,y+offsetY,1,1,(helper.getBlue()/255f),(helper.getGreen()/255f),(helper.getRed()/255f),1);
        return 1;
    }
    public static void drawRect(double x,double y,double width,double height,double red,double green,double blue,double alpha) {
        Tessellator tessellator=Tessellator.getInstance();
        BufferBuilder buffer=tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(x,(y+height),0).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos((x+width),(y+height),0).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos((x+width),y,0).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos(x,y,0).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        tessellator.draw();
    }
    public static void drawTexturedRect(double x,double y,double u,double v,double texWidth,double texHeight,double width,double height,double red,double green,double blue,double alpha) {
        Tessellator tessellator=Tessellator.getInstance();
        BufferBuilder buffer=tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(x,(y+height),0).tex((float)((float)u * 0.00390625F), (float)((float)(v + texHeight) * 0.00390625F)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos((x+width),(y+height),0).tex((float)((float)(u + texWidth) * 0.00390625F), (float)((float)(v + texHeight) * 0.00390625F)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos((x+width),y,0).tex((float)((float)(u + texWidth) * 0.00390625F), (float)((float)v * 0.00390625F)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        buffer.pos(x,y,0).tex((float)((float)u * 0.00390625F), (float)((float)v * 0.00390625F)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
    
//        buffer.pos(x,(y+height),0).tex((float)((float)u), (float)((float)(v + texHeight))).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
//        buffer.pos((x+width),(y+height),0).tex((float)((float)(u + texWidth)), (float)((float)(v + texHeight))).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
//        buffer.pos((x+width),y,0).tex((float)((float)(u + texWidth)), (float)((float)v)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
//        buffer.pos(x,y,0).tex((float)((float)u), (float)((float)v)).color((float)red,(float)green,(float)blue,(float)alpha).endVertex();
        
        tessellator.draw();
    }
}
