package tfc.threetwooneratio;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ScreenShotHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.lwjgl.glfw.GLFW;
import tfc.threetwooneratio.Render.Image.ImageRenderer;
import tfc.threetwooneratio.Render.Image.ImageUnrenderer;

import java.util.Date;

import static tfc.threetwooneratio.ThreeTwoOneRatio.uimgs;
import static tfc.threetwooneratio.ThreeTwoOneRatio.uimgsw;

@Mod.EventBusSubscriber(modid = "threetwooneratio",value=Dist.CLIENT)
public class EventPauserClient {
    public static KeyBinding start=new KeyBinding("StartRec", GLFW.GLFW_KEY_R,"3To1Ratio");
    public static KeyBinding playback =new KeyBinding("PlaybackRec", GLFW.GLFW_KEY_B,"3To1Ratio");
    public static KeyBinding clearRec=new KeyBinding("ClearRec", GLFW.GLFW_KEY_C,"3To1Ratio");
    public static KeyBinding pauseRec=new KeyBinding("PauseRec", GLFW.GLFW_KEY_Y,"3To1Ratio");
    public static boolean isPlaying=false;
    
    @SubscribeEvent
    public static void keyPress(InputEvent.KeyInputEvent event) {
        try {
            if (!(Minecraft.getInstance().currentScreen instanceof ChatScreen)) {
                int key=event.getKey();
                int keystart=start.getKey().getKeyCode();
                int keyplayback= playback.getKey().getKeyCode();
                int keypause= pauseRec.getKey().getKeyCode();
                int keyclear= clearRec.getKey().getKeyCode();
                if (key==keystart) {
                    CommonVariables.rec=true;
                    CommonVariables.stop=false;
                } else if (key==keyplayback) {
                    CommonVariables.rec=false;
                    CommonVariables.stop=true;
                    isPlaying=true;
                    if (CommonVariables.frames>=1) {
                        if (!Minecraft.getInstance().isGamePaused()) {
                            isPlaying=true;
//                            try {
//                                Renderer renderer=new Renderer(ThreeTwoOneRatio.imgs);
//                                while ((renderer.frame<ThreeTwoOneRatio.imgs.size())&&renderer.isVisible()) {
//                                    renderer.display.repaint();
//                                }
//                                renderer.dispose();
//                                Runtime.getRuntime().gc();
//                            } catch (Exception err) {
//                                for (StackTraceElement element:err.getStackTrace()) {
//                                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,""+element);
//                                }
//                            }
                        }
                    }
                } else if (key==keypause) {
                    CommonVariables.rec=true;
                    CommonVariables.stop=false;
                } else if (key==keyclear) {
                    CommonVariables.rec=true;
                    CommonVariables.stop=false;
                    for (NativeImage image:ThreeTwoOneRatio.imgs) {
                        image.close();
                        image=null;
                    }
                    ThreeTwoOneRatio.imgs.clear();
                    uimgs.clear();
                    uimgsw.clear();
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

    static NewRenderer renderer;
    
    @SubscribeEvent
    public static void onRender(RenderWorldLastEvent event) {
        //Wait for client to render 3 frames
        if (isPlaying) {
            CommonVariables.rec=false;
        }
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
    
    @SubscribeEvent
    public static void RenderHUD(RenderGameOverlayEvent event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.TEXT)) {
            RenderSystem.pushMatrix();
            RenderSystem.scalef(0.1f,0.1f,0.1f);
            try {
                renderer.renderNativeImage(ThreeTwoOneRatio.imgs.get(ThreeTwoOneRatio.imgs.size()-1));
            } catch (Exception err) {
//                Minecraft mc = Minecraft.getInstance();
//                NativeImage nativeImage = ScreenShotHelper.createScreenshot(mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight, mc.getFramebuffer());
                try {
                    renderer=new NewRenderer(ThreeTwoOneRatio.imgs.get(0));
                } catch (Exception err2) {}
            }
            RenderSystem.popMatrix();
        }
        try {
            try {
                for (int i = 0; i<uimgsw.size(); i++) {
                    if (uimgsw.get(i).deUnrender()) {
                        ThreeTwoOneRatio.uimgs.add(uimgsw.get(i).dest);
                        ThreeTwoOneRatio.imgs.remove(i);
                        ThreeTwoOneRatio.uimgsw.remove(i);
                        i--;
                    }
                    ThreeTwoOneRatio.LOGGER.log(Level.INFO,"hi");
                }
            } catch (Exception err) {}
            if (uimgsw.size()<=10) {
                uimgsw.add(new ImageUnrenderer(ThreeTwoOneRatio.imgs.get(0)));
            }
        } catch (Exception err) {
        }
        if (isPlaying) {
            try {
                ImageRenderer rerenderer=new ImageRenderer(uimgs.get(0));
                while (!rerenderer.reRender()) {
                }
                renderer.renderNativeImage(rerenderer.dest);
            } catch (Exception err) {
                isPlaying=false;
            }
        }
    }
}
