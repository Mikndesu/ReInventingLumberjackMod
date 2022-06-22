package com.github.mikndesu.re_lumberjack.config;

import java.util.ArrayList;
import java.util.List;

import com.github.mikndesu.re_lumberjack.ReLumberjack;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ReLumberjack.MODID)
public class ReLumberjackConfig implements ConfigData {
    @RequiresRestart
    @Comment("Changes will be applied to your client after restarting.")
    public List<String> registryNameList = new ArrayList<>();
}
