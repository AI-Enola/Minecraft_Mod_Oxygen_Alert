package com.main.oxygenalert;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Sound {

	private RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(OxygenAlert.MODID, name)));
    }
    private final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OxygenAlert.MODID);
    public final RegistryObject<SoundEvent> LOW_OXYGEN = registerSoundEvent("low_oxygen_sound");
    
    
    public void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
