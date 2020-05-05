package tfc.threetwooneratio;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "threetwooneratio")
public class EventPauserServer {
    @SubscribeEvent
    public static void onTick(TickEvent.WorldTickEvent event) {
        //Wait for client to render 3 frames
        CommonVariables.paused=false;
        if (CommonVariables.rec) {
            if (CommonVariables.ingame) {
                int i=0;
                while (CommonVariables.Renders< CommonVariables.RendersPerTick) {
                    if (i>=1280000) {
                        CommonVariables.Renders=3;
                    }
                    i++;
//                ThreeTwoOneRatio.LOGGER.log(Level.INFO,"ServerPaused:"+Renders);
//                ThreeTwoOneRatio.LOGGER.log(Level.INFO,"ServerPaused:"+Ticks);
                }
                CommonVariables.Ticks+=1;
//            ThreeTwoOneRatio.LOGGER.log(Level.INFO,"ServerNotPaused:"+Ticks);
                if (CommonVariables.Ticks>=1) {
                    CommonVariables.Renders=0;
                    CommonVariables.Ticks=0;
                }
            }
        }
    }
}
