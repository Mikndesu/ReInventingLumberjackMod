package com.github.mikndesu.re_lumberjack.asm.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.mikndesu.re_lumberjack.ReLumberjack;
import com.google.common.collect.ImmutableList;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "playerDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/item/ItemStack;)V", at = @At("RETURN"), cancellable = true)
    private void inject(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack is, CallbackInfo ci) {
        if(ReLumberjack.acceptedBlocks.stream().noneMatch(s->s.equals(blockState))) {
            return;
        }
        ItemStack itemStack = new ItemStack(blockState.getBlock().asItem());
        int drops = removeAndComputeLogDrops(0, blockState, blockPos, level);
        for(int i = 0; i < drops; i++) {
            Block.dropResources(blockState, level, blockPos, blockEntity, player, itemStack);
        }
    }

    private int removeAndComputeLogDrops(int drops, BlockState blockState, BlockPos blockPos, Level level) {
        if(drops >= 2000) {
            return drops;
        }
        List<Vec3i> searchVectorDirections = ImmutableList.of(new Vec3i(1,0,0),new Vec3i(-1,0,0), new Vec3i(0, 1, 0), new Vec3i(0, -1, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1));
        for(var direction:searchVectorDirections) {
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
            mutablePos.set(blockPos).move(direction);
            BlockState bs = level.getBlockState(mutablePos).getBlock().defaultBlockState();
            if(bs.equals(blockState)) {
                level.setBlockAndUpdate(mutablePos, Blocks.AIR.defaultBlockState());
                drops = removeAndComputeLogDrops(drops+1, blockState, mutablePos, level);
            }
        }
        return drops;
    }
}
