package com.oierbravo.watercondenser;

import com.mojang.logging.LogUtils;
import com.oierbravo.watercondenser.block.ModBlocks;
import com.oierbravo.watercondenser.config.ModConfigCommon;
import com.oierbravo.watercondenser.entity.ModBlockEntities;
import com.oierbravo.watercondenser.entity.WatercondenserBlockEntity;
import com.oierbravo.watercondenser.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WaterCondenser.MOD_ID)
public class WaterCondenser
{
    public static final String MOD_ID = "watercondenser";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public WaterCondenser()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        ModBlocks.register(eventBus);
        ModItems.register(eventBus);
        ModBlockEntities.register(eventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigCommon.SPEC, "watercondenser-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerAboutToStart(final ServerAboutToStartEvent event) {
        WatercondenserBlockEntity.verifyConfig(LOGGER);
    }

}
