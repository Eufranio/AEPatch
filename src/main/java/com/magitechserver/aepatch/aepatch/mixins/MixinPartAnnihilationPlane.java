package com.magitechserver.aepatch.aepatch.mixins;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Created by Frani on 17/07/2017.
 */

@Pseudo
@Mixin(targets = "appeng/parts/automation/PartAnnihilationPlane", remap = false)
public abstract class MixinPartAnnihilationPlane {

    @Inject(method = "breakBlockAndStoreItems", at = @At("HEAD"), cancellable = true)
    private void injectFakePlayer(WorldServer w, BlockPos pos, List<ItemStack> items, CallbackInfo ci) {
        BlockEvent.BreakEvent e = new BlockEvent.BreakEvent(w, pos, w.getBlockState(pos), FakePlayerFactory.getMinecraft(w));
        MinecraftForge.EVENT_BUS.post(e);
        if(e.isCanceled()) {
            ci.cancel();
        }
    }

}
