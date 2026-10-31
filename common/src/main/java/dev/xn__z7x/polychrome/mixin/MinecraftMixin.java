package dev.xn__z7x.polychrome.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.xn__z7x.polychrome.Polychrome;

@Mixin(Minecraft.class)
public class MinecraftMixin{

  @Inject(at = @At("TAIL"), method = "<init>")
  private void init(CallbackInfo info){
    Polychrome.logger.info("Hello from " + Polychrome.MOD_ID);
  }
}
