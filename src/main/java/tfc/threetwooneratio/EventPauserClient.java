package tfc.threetwooneratio;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ScreenShotHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;

import java.util.Date;

@Mod.EventBusSubscriber(modid = "threetwooneratio",value=Dist.CLIENT)
public class EventPauserClient {
    public static KeyBinding start=new KeyBinding("StartRec", GLFW.GLFW_KEY_R,"3To1Ratio");
    public static KeyBinding stop=new KeyBinding("StopRec", GLFW.GLFW_KEY_Y,"3To1Ratio");

    @SubscribeEvent
    public static void keyPress(InputEvent.KeyInputEvent event) {
        try {
            if (!(Minecraft.getInstance().currentScreen instanceof ChatScreen)) {
                int key=event.getKey();
                int keystart=start.getKey().getKeyCode();
                int keystop=stop.getKey().getKeyCode();
//        ThreeTwoOneRatio.LOGGER.log(Level.INFO,key);
//        ThreeTwoOneRatio.LOGGER.log(Level.INFO,keystart);
//        ThreeTwoOneRatio.LOGGER.log(Level.INFO,keystop);
                if (key==keystart) {
                    CommonVariables.rec=true;
                    CommonVariables.stop=false;
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,true);
                } else if (key==keystop) {
                    CommonVariables.rec=false;
                    CommonVariables.stop=true;
                    if (CommonVariables.frames>=1) {
//                ThreeTwoOneRatio.LOGGER.log(Level.INFO,CommonVariables.frames);
//                        Minecraft.getInstance().currentScreen=new ChatScreen("h");
                        if (!Minecraft.getInstance().isGamePaused()) {
//                            if (CommonVariables.stop) {
                                try {
                                    Renderer renderer=new Renderer(ThreeTwoOneRatio.imgs);
//                        renderer.setVisible(true);
                                    while ((renderer.frame<ThreeTwoOneRatio.imgs.size())&&renderer.isVisible()) {
//                            ThreeTwoOneRatio.LOGGER.log(Level.INFO,renderer.frame);
//                            ThreeTwoOneRatio.LOGGER.log(Level.INFO,ThreeTwoOneRatio.imgs.size());
                                        renderer.display.repaint();
//                            renderer.update(renderer.display.getGraphics());
                                    }
                                    renderer.dispose();
                                    Runtime.getRuntime().gc();
                                } catch (Exception err) {
                                    for (StackTraceElement element:err.getStackTrace()) {
                                        ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
                                    }
                                }
                            }
//                        }
//                        ThreeTwoOneRatio.LOGGER.log(Level.INFO,"h");
                    }
                }
            }
        } catch (Exception err) {
            for (StackTraceElement element:err.getStackTrace()) {
                ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
            }
        }
    }

    @SubscribeEvent
    public static void onJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            CommonVariables.ingame=true;
        }
    }

    static float averageRenderTime=0;
    static Date lastRender=new Date();

    @SubscribeEvent
    public static void onRender(RenderWorldLastEvent event) {
        //Wait for client to render 3 frames
        long currentRenderTime=new Date().getTime()-lastRender.getTime();
        if (Minecraft.getInstance().isGamePaused()) {
            lastRender=new Date();
        }
        if (!CommonVariables.rec) {
            averageRenderTime+=new Date().getTime()-lastRender.getTime();
            averageRenderTime/=2;
            lastRender=new Date();
        }
        if (Minecraft.getInstance().isIntegratedServerRunning()) {
            if (CommonVariables.rec) {
                try {
                    if (CommonVariables.ingame) {
                        if (!Minecraft.getInstance().player.equals(null)) {
                            if (!Minecraft.getInstance().isGamePaused()) {
                                int i=0;
                                while (CommonVariables.Renders>=CommonVariables.RendersPerTick) {
                                    if (i>=1280000) {
                                        CommonVariables.Renders=0;
                                    }
                                    i++;
                                    lastRender=new Date();
//                            ThreeTwoOneRatio.LOGGER.log(Level.INFO,"ClientPaused:"+Renders);
//                            ThreeTwoOneRatio.LOGGER.log(Level.INFO,"ClientPaused:"+Ticks);
                                }
                                CommonVariables.Renders+=1;
                            }
                        } else {
                            CommonVariables.ingame=false;
                        }
                    }
                } catch (Exception err) {
                    CommonVariables.ingame=false;
                }
            }
        }
        if (CommonVariables.rec) {
            averageRenderTime+=new Date().getTime()-lastRender.getTime();
            averageRenderTime/=2;
            lastRender=new Date();
            float tolerance=((currentRenderTime*10)+(averageRenderTime))/11;
            if (!Minecraft.getInstance().isGamePaused()) {
                if (CommonVariables.lastScreenShot.getTime()+(tolerance-16)<=new Date().getTime()) {
                    try {
                        Minecraft mc = Minecraft.getInstance();
                        NativeImage nativeImage = ScreenShotHelper.createScreenshot(mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight, mc.getFramebuffer());
                        ThreeTwoOneRatio.imgs.add(nativeImage);
//                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,CommonVariables.frames);
                        CommonVariables.frames += 1;
                    } catch (Exception err) {
//                        ThreeTwoOneRatio.LOGGER.log(Level.INFO,err.getCause());
                    }
                }
            }
        }
    }
}
