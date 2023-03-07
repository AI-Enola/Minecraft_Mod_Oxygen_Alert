package com.main.oxygenalert;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OxygenAlert.MODID)
public class OxygenAlert
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "oxygenalert";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private Sound _sound; // Call Sound class
    private boolean oxygenAlert = false;
    
    
    public OxygenAlert()
    {
    	_sound = new Sound();
    	_sound.register(FMLJavaModLoadingContext.get().getModEventBus());
    	
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register the sound event
        
    }
  

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent event) {
        if (event.getEntity() instanceof Player) {
        	
        	Player player = (Player) event.getEntity();
    		int minAir = player.getMaxAirSupply() - ((player.getMaxAirSupply() * 75) / 100 );
    		
    		if(player.getAirSupply() < minAir && !oxygenAlert) {
    			oxygenAlert = true;
    			player.playSound(_sound.LOW_OXYGEN.get(), 1f, 1f);
    			
    		} else if (player.getAirSupply() > minAir && oxygenAlert){
    			oxygenAlert = false;
    		}
        }
    }
}
