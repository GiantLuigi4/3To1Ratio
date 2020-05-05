package tfc.threetwooneratio;

import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class Renderer extends JFrame {
    ArrayList<BufferedImage> bufferedImages=new ArrayList<>();
    ArrayList<NativeImage> images=new ArrayList<>();
    Color color1;
    Color color2;
    Date lastFrame=new Date();
    Component display=new Component();
    public class Component extends JComponent {
        @Override
        public void paint(Graphics g1) {
//            super.paint(g);
            try {
                BufferedImage bimig=bufferedImages.get(frame);
//                ((Graphics2D)g1).setPaint(new TexturePaint(bimig,new Rectangle2D.Double(0,0,bimig.getWidth(),bimig.getHeight())));
                g1.drawImage(bimig.getScaledInstance(bimig.getWidth(),bimig.getHeight(),BufferedImage.SCALE_SMOOTH),0,0,null);
//                g1.drawRect(0,0,bimig.getWidth(),bimig.getHeight());
            } catch (Exception err) {}
            if (lastFrame.getTime()+32<=new Date().getTime()) {
                frame+=1;
                lastFrame=new Date();
            }
        }

        Graphics g=null;

        public void paint(String r) {
            if (g==null) {
                g=this.getGraphics();
            }
            g.setColor(new Color(0xFFFFFF));
            g.fillRect(0,0,1000,1000);
            g.setColor(new Color(0x000000));
            g.drawString(r,15,15);
        }

        @Override
        public boolean isOptimizedDrawingEnabled() {
            return true;
        }
    };

    public BufferedImage render(int frame,BufferedImage bimig) {
//        Graphics2D g=bimig.createGraphics();
//        test test=null;
        color1=new Color(0);
        try {
            NativeImage image=null;
            NativeImage image2=null;
            try {
                image=images.get(frame);
                image2=images.get(frame-1);
            } catch (Exception err) {
                try {
                    image2=images.get(frame);
                } catch (Exception err2) {}
            }
//            test=new test(bimig.getWidth(),bimig.getHeight(),bimig.getType(),image,image2);
//            g=(Graphics2D)test.getGraphics();
//            g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,color1);
            int x=0;
            int y=0;
            for (x = 0; x < image.getWidth(); x += 16) {
                for (y = 0; y < image.getWidth(); y += 16) {
                    try {
                        draw(x,y,bimig,image,image2);
                    } catch (Exception err) {
                        for (StackTraceElement element:err.getStackTrace()) {
                            ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
                        }
                    }
                }
            }
//            for (x = 0; x < image.getWidth(); x += 1) {
//                for (y = 0; y < image.getWidth(); y += 1) {
//                    try {
//                        test.setRGB(x,y,test.getRGB(x,y));
//                        draw(x,y,g,image,image2);
//                    } catch (Exception err) {
//                    }
//                }
//            }
        } catch (Exception err) {
            for (StackTraceElement element:err.getStackTrace()) {
                ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
            }
        }
        return bimig;
    }

    public class test extends BufferedImage {
        NativeImage image;
        NativeImage imagetwo;
        public test(int width, int height, int imageType) {
            super(width, height, imageType);
        }
        public test(int width, int height, int imageType,NativeImage image1,NativeImage image2) {
            super(width, height, imageType);
            image=image1;
            imagetwo=image2;
            try {
                for (int x=0;x<width;x+=70) {
                    for (int y=0;y<height;y+=1) {
                        try {
                            this.setRGB(x,y,this.getRGB(x,y));
                            this.setRGB(x+1,y,this.getRGB(x+1,y));
                            this.setRGB(x+2,y,this.getRGB(x+2,y));
                            this.setRGB(x+3,y,this.getRGB(x+3,y));
                            this.setRGB(x+4,y,this.getRGB(x+4,y));
                            this.setRGB(x+5,y,this.getRGB(x+5,y));
                            this.setRGB(x+6,y,this.getRGB(x+6,y));
                            this.setRGB(x+7,y,this.getRGB(x+7,y));
                            this.setRGB(x+8,y,this.getRGB(x+8,y));
                            this.setRGB(x+9,y,this.getRGB(x+9,y));
                            this.setRGB(x+10,y,this.getRGB(x+10,y));
                            this.setRGB(x+11,y,this.getRGB(x+11,y));
                            this.setRGB(x+12,y,this.getRGB(x+12,y));
                            this.setRGB(x+13,y,this.getRGB(x+13,y));
                            this.setRGB(x+14,y,this.getRGB(x+14,y));
                            this.setRGB(x+15,y,this.getRGB(x+15,y));
                            this.setRGB(x+16,y,this.getRGB(x+16,y));
                            this.setRGB(x+17,y,this.getRGB(x+17,y));
                            this.setRGB(x+18,y,this.getRGB(x+18,y));
                            this.setRGB(x+19,y,this.getRGB(x+19,y));
                            this.setRGB(x+20,y,this.getRGB(x+20,y));
                            this.setRGB(x+21,y,this.getRGB(x+21,y));
                            this.setRGB(x+22,y,this.getRGB(x+22,y));
                            this.setRGB(x+23,y,this.getRGB(x+23,y));
                            this.setRGB(x+24,y,this.getRGB(x+24,y));
                            this.setRGB(x+25,y,this.getRGB(x+25,y));
                            this.setRGB(x+26,y,this.getRGB(x+26,y));
                            this.setRGB(x+27,y,this.getRGB(x+27,y));
                            this.setRGB(x+28,y,this.getRGB(x+28,y));
                            this.setRGB(x+29,y,this.getRGB(x+29,y));
                            this.setRGB(x+30,y,this.getRGB(x+30,y));
                            this.setRGB(x+31,y,this.getRGB(x+31,y));
                            this.setRGB(x+32,y,this.getRGB(x+32,y));
                            this.setRGB(x+33,y,this.getRGB(x+33,y));
                            this.setRGB(x+34,y,this.getRGB(x+34,y));
                            this.setRGB(x+35,y,this.getRGB(x+35,y));
                            this.setRGB(x+36,y,this.getRGB(x+36,y));
                            this.setRGB(x+37,y,this.getRGB(x+37,y));
                            this.setRGB(x+38,y,this.getRGB(x+38,y));
                            this.setRGB(x+39,y,this.getRGB(x+39,y));
                            this.setRGB(x+40,y,this.getRGB(x+40,y));
                            this.setRGB(x+41,y,this.getRGB(x+41,y));
                            this.setRGB(x+42,y,this.getRGB(x+42,y));
                            this.setRGB(x+43,y,this.getRGB(x+43,y));
                            this.setRGB(x+44,y,this.getRGB(x+44,y));
                            this.setRGB(x+45,y,this.getRGB(x+45,y));
                            this.setRGB(x+46,y,this.getRGB(x+46,y));
                            this.setRGB(x+47,y,this.getRGB(x+47,y));
                            this.setRGB(x+48,y,this.getRGB(x+48,y));
                            this.setRGB(x+49,y,this.getRGB(x+49,y));
                            this.setRGB(x+50,y,this.getRGB(x+50,y));
                            this.setRGB(x+51,y,this.getRGB(x+51,y));
                            this.setRGB(x+52,y,this.getRGB(x+52,y));
                            this.setRGB(x+53,y,this.getRGB(x+53,y));
                            this.setRGB(x+54,y,this.getRGB(x+54,y));
                            this.setRGB(x+55,y,this.getRGB(x+55,y));
                            this.setRGB(x+56,y,this.getRGB(x+56,y));
                            this.setRGB(x+57,y,this.getRGB(x+57,y));
                            this.setRGB(x+58,y,this.getRGB(x+58,y));
                            this.setRGB(x+59,y,this.getRGB(x+59,y));
                            this.setRGB(x+60,y,this.getRGB(x+60,y));
                            this.setRGB(x+61,y,this.getRGB(x+61,y));
                            this.setRGB(x+62,y,this.getRGB(x+62,y));
                            this.setRGB(x+63,y,this.getRGB(x+63,y));
                            this.setRGB(x+64,y,this.getRGB(x+64,y));
                            this.setRGB(x+65,y,this.getRGB(x+65,y));
                            this.setRGB(x+66,y,this.getRGB(x+66,y));
                            this.setRGB(x+67,y,this.getRGB(x+67,y));
                            this.setRGB(x+68,y,this.getRGB(x+68,y));
                            this.setRGB(x+69,y,this.getRGB(x+69,y));
                        } catch (Exception err) {
//                            for (StackTraceElement element:err.getStackTrace()) {
////                                ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
////                            }
                        }
                    }
                }
            } catch (Exception err) {
                for (StackTraceElement element:err.getStackTrace()) {
                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
                }
            }
        }
        public test(int width, int height, int imageType, IndexColorModel cm) {
            super(width, height, imageType, cm);
        }
        public test(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
            super(cm, raster, isRasterPremultiplied, properties);
        }
        @Override
        public int getRGB(int x, int y) {
            try {
                color1=new Color(image.getPixelRGBA(x,y));
                color2=new Color(imagetwo.getPixelRGBA(x,y));
                color1=new Color(
                        (int)MathHelper.lerp(0.025f,color1.getBlue(),color2.getBlue()),
                        (int)MathHelper.lerp(0.025f,color1.getGreen(),color2.getGreen()),
                        (int)MathHelper.lerp(0.025f,color1.getRed(),color2.getRed())
                );

                //antialising
//                float blendFactor=0.3f;
//                antialiasingResult result=antialiasing(color1,x,y-1,image,blendFactor);
//                color1=result.color;
//                blendFactor=result.blend;
//                result=antialiasing(color1,x,y-2,image,blendFactor);
//                color1=result.color;
//
//                blendFactor=0.3f;
//                result=antialiasing(color1,x+1,y,image,blendFactor);
//                color1=result.color;
//                blendFactor=result.blend;
//                result=antialiasing(color1,x+2,y,image,blendFactor);
//                color1=result.color;
//
//                blendFactor=0.3f;
//                result=antialiasing(color1,x-1,y,image,blendFactor);
//                color1=result.color;
//                blendFactor=result.blend;
//                result=antialiasing(color1,x-2,y,image,blendFactor);
//                color1=result.color;
//
//                blendFactor=0.3f;
//                result=antialiasing(color1,x,y+1,image,blendFactor);
//                color1=result.color;
//                blendFactor=result.blend;
//                result=antialiasing(color1,x,y+2,image,blendFactor);
//                color1=result.color;
            } catch (Exception err) {
//                for (StackTraceElement element:err.getStackTrace()) {
//                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
//                }
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,"err:"+err.getMessage());
            }
            return color1.getRGB();
        }
        @Override
        public Image getScaledInstance(int width, int height, int hints) {
//            if (hints==BufferedImage.SCALE_FAST||BufferedImage.SCALE_AREA_AVERAGING) {
//
//            }
            return super.getScaledInstance(width, height, hints);
        }
    }

    public void draw(int x, int y, BufferedImage img1, NativeImage img, NativeImage imgtwo) {
        drawPartialRow(x,y,img1,img,imgtwo);
        drawPartialRow(x,y+1,img1,img,imgtwo);
        drawPartialRow(x,y+2,img1,img,imgtwo);
        drawPartialRow(x,y+3,img1,img,imgtwo);
        drawPartialRow(x,y+4,img1,img,imgtwo);
        drawPartialRow(x,y+5,img1,img,imgtwo);
        drawPartialRow(x,y+6,img1,img,imgtwo);
        drawPartialRow(x,y+7,img1,img,imgtwo);
        drawPartialRow(x,y+8,img1,img,imgtwo);
        drawPartialRow(x,y+9,img1,img,imgtwo);
        drawPartialRow(x,y+10,img1,img,imgtwo);
        drawPartialRow(x,y+11,img1,img,imgtwo);
        drawPartialRow(x,y+12,img1,img,imgtwo);
        drawPartialRow(x,y+13,img1,img,imgtwo);
        drawPartialRow(x,y+14,img1,img,imgtwo);
        drawPartialRow(x,y+15,img1,img,imgtwo);
//        drawPixel(x,y,4,2,img1,img);
//        drawPixel(x,y,8,2,img1,img);
//        drawPixel(x+2,y,1,2,img1,img);
//        drawPixel(x,y+2,1,3,img1,img);
//        drawPixel(x+1,y+2,2,2,img1,img);
//        drawPixel(x+3,y+0,2,3,img1,img);
//        drawPixel(x+6,y+0,4,3,img1,img);
//        drawPixel(x+5,y+2,3,2,img1,img);
//        drawPixel(x+0,y+4,3,2,img1,img);
//        drawPixel(x+2,y+3,2,2,img1,img);
//        drawPixel(x+0,y+4,1,3,img1,img);
//        drawPixel(x+4,y+3,1,3,img1,img);
//        drawPixel(x+5,y+3,3,2,img1,img);
//        drawPixel(x+7,y+4,3,2,img1,img);
//        drawPixel(x+0,y+5,3,3,img1,img);
//        drawPixel(x+3,y+5,3,3,img1,img);
//        drawPixel(x+6,y+5,1,3,img1,img);
//        drawPixel(x+7,y+6,1,2,img1,img);
    }
    public void drawPartialRow(int x, int y, BufferedImage img1, NativeImage img, NativeImage imgtwo) {
        drawPixel(x+0,y,1,1,img1,img,imgtwo);
        drawPixel(x+1,y,1,1,img1,img,imgtwo);
        drawPixel(x+2,y,1,1,img1,img,imgtwo);
        drawPixel(x+3,y,1,1,img1,img,imgtwo);
        drawPixel(x+4,y,1,1,img1,img,imgtwo);
        drawPixel(x+5,y,1,1,img1,img,imgtwo);
        drawPixel(x+6,y,1,1,img1,img,imgtwo);
        drawPixel(x+7,y,1,1,img1,img,imgtwo);
        drawPixel(x+8,y,1,1,img1,img,imgtwo);
        drawPixel(x+9,y,1,1,img1,img,imgtwo);
        drawPixel(x+10,y,1,1,img1,img,imgtwo);
        drawPixel(x+11,y,1,1,img1,img,imgtwo);
        drawPixel(x+12,y,1,1,img1,img,imgtwo);
        drawPixel(x+13,y,1,1,img1,img,imgtwo);
        drawPixel(x+14,y,1,1,img1,img,imgtwo);
        drawPixel(x+15,y,1,1,img1,img,imgtwo);
    }
    public void drawPixel(int x,int y,int w,int h,BufferedImage img1,NativeImage image,NativeImage imagetwo) {
        try {
            color1=new Color(image.getPixelRGBA(x,y));
            color2=new Color(imagetwo.getPixelRGBA(x,y));
            color1=new Color(
                    (int)MathHelper.lerp(0.025f,color1.getBlue(),color2.getBlue()),
                    (int)MathHelper.lerp(0.025f,color1.getGreen(),color2.getGreen()),
                    (int)MathHelper.lerp(0.025f,color1.getRed(),color2.getRed())
            );
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,color1);

            //antialising
            try {
                float blendFactor=0.3f;
                antialiasingResult result=antialiasing(color1,x,y-1,image,blendFactor);
                color1=result.color;
                blendFactor=result.blend;
                result=antialiasing(color1,x,y-2,image,blendFactor);
                color1=result.color;

                blendFactor=0.3f;
                result=antialiasing(color1,x+1,y,image,blendFactor);
                color1=result.color;
                blendFactor=result.blend;
                result=antialiasing(color1,x+2,y,image,blendFactor);
                color1=result.color;

                blendFactor=0.3f;
                result=antialiasing(color1,x-1,y,image,blendFactor);
                color1=result.color;
                blendFactor=result.blend;
                result=antialiasing(color1,x-2,y,image,blendFactor);
                color1=result.color;

                blendFactor=0.3f;
                result=antialiasing(color1,x,y+1,image,blendFactor);
                color1=result.color;
                blendFactor=result.blend;
                result=antialiasing(color1,x,y+2,image,blendFactor);
                color1=result.color;
            } catch (Exception err) {
                for (StackTraceElement element:err.getStackTrace()) {
                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
                }
            }

//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,color1);
            img1.setRGB(x,y,color1.getRGB());
        } catch (Exception err) {
            for (StackTraceElement element:err.getStackTrace()) {
                ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
            }
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,"err:"+err.getMessage());
        }
    }
    public antialiasingResult antialiasing(Color source,int x,int y,NativeImage image,float blend) {
        Color color2=new Color(image.getPixelRGBA(x,y));
        blend=((
                getOffsetPercent(color2.getBlue(),source.getRed())+
                getOffsetPercent(color2.getGreen(),source.getGreen())+
                getOffsetPercent(color2.getRed(),source.getBlue())+
                blend)/12);
        source=new Color(
                (int)MathHelper.lerp(blend,source.getRed(),color2.getBlue()),
                (int)MathHelper.lerp(blend,source.getGreen(),color2.getGreen()),
                (int)MathHelper.lerp(blend,source.getBlue(),color2.getRed())
        );
        return new antialiasingResult(blend,source);
    }
    private static class antialiasingResult {
        float blend;
        Color color;
        public antialiasingResult(float blend, Color color) {
            this.blend = blend;
            this.color = color;
        }
    }
    public float getOffsetPercent(int num1,int num2) {
        return num1>=num2 ? (float)num2/(float)num1 : (float)num1/(float)num2;
    }
    public Renderer(ArrayList<NativeImage> images) throws HeadlessException {
        super("Render");
        this.setVisible(true);
        this.images=images;
        try {
            width=images.get(0).getWidth();
            height=images.get(0).getHeight();
            this.setSize(width,height+90);
            this.add(display);
        } catch (Exception err) {}
        for (int i=0;i<images.size();i++) {
            NativeImage image=images.get(i);
//            BufferedImage img=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
//            int y=0;
////            getPixels(0,0,image.getWidth()-1,image.getHeight()-1,img,image);
////            Color color=null;
////            for (int x=0;x<image.getWidth();x+=14) {
////                for (y=0;y<image.getWidth();y+=14) {
////                    try {
////                        getPixels(x,y,x+14,y+14,img,image);
////                        ThreeTwoOneRatio.LOGGER.log(Level.INFO,"h");
////                    } catch (Exception err) {}
////                }
////            }
            if (this.isVisible()) {
                try {
//                BufferedImage img = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
//                img.createGraphics();
//                img = render(i,img);
                    test img = new test(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB,image,images.get(i-1));
                    display.paint("Frames rendered:"+(i+1)+"/"+(images.size()));
                    bufferedImages.add(img);
//                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,bufferedImages.size());
                    if (Runtime.getRuntime().freeMemory()<=(Runtime.getRuntime().maxMemory()/12)) {
                        Runtime.getRuntime().gc();
                    }
                } catch (Exception err) {
                    for (StackTraceElement element:err.getStackTrace()) {
                        ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
                    }
                }
            }
        }
        this.images=images;
    }
    int frame=-10;
    int width;
    int height;

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);
        width=d.width;
        height=d.height;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.width=width;
        this.height=height;
    }

    public void getPixels(int minx,int miny,int maxx,int maxy,BufferedImage g,NativeImage img) {
        Pixel pxl=getPixel(minx,miny,img).paint(g);
//        ThreeTwoOneRatio.LOGGER.log(Level.INFO,minx+","+miny+","+maxx+","+maxy);
        getNextPixel(minx,miny,pxl,maxx,maxy,g,img).paint(g);
    }
    public Pixel getNextPixel(int minx,int miny,Pixel pixel,int maxx,int maxy,BufferedImage g,NativeImage img) {
        Pixel pixel1;
        if (minx<=maxx) {
            pixel1=getPixel(pixel.x+1,pixel.y,img);
        } else if (pixel.y<=maxy) {
            pixel1=getPixel(minx,pixel.y+1,img);
        } else {
            return null;
        }
        getNextPixel(minx,miny,pixel1,maxx,maxy,g,img);
        return pixel1.paint(g);
    }
    public Pixel getPixel(int minx,int miny,NativeImage img) {
        return new Pixel(minx,miny,img);
    }
    private static class Pixel {
        int x;
        int y;
        int c;

        public Pixel(int x, int y,NativeImage img) {
            this.x = x;
            this.y = y;
            c=img.getPixelRGBA(x,y);
        }

        public Pixel paint(BufferedImage g) {
//            g.setColor(color);
//            g.fillRect(x,y,4,4);
            if (c>=16777215) {
                c = 16777215;
            } else {
                g.getGraphics().setColor(new Color(c));
            }
            g.getGraphics().fillRect(x,y,1,1);
//                g.setRGB(x,y,0);
            g.setRGB(x,y,c);
            return this;
        }
    }
}
