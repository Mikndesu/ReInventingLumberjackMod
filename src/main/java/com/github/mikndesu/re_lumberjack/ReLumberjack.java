package com.github.mikndesu.re_lumberjack;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.mikndesu.re_lumberjack.config.ReLumberjackConfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.block.state.BlockState;

public class ReLumberjack implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("ReLumberJack/Main");
    public static final String MODID = "re_lumberjack";
    public static List<BlockState> acceptedBlocks = new ArrayList<>();

    @Override
    public void onInitialize() {
        AutoConfig.register(ReLumberjackConfig.class, JanksonConfigSerializer::new);
    }
    
}
