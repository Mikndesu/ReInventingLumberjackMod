package com.github.mikndesu.re_lumberjack.config;

import java.util.ArrayList;
import java.util.List;

import com.github.mikndesu.re_lumberjack.ReLumberjack;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ReLumberjack.MODID)
public class ReLumberjackConfig implements ConfigData {
    private final List<String> registryNameList = new ArrayList<>();
}
