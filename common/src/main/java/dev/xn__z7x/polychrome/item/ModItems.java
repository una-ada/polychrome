package dev.xn__z7x.polychrome.item;

import dev.xn__z7x.polychrome.block.PolychromeBlocks;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.minecraft.network.chat.Component;
import dev.xn__z7x.polychrome.Polychrome;
import static dev.xn__z7x.polychrome.Polychrome.id;

public class ModItems{

  public static void initialize(BalmItemRegistrar items){

  }

  public static void initialize(BalmCreativeModeTabRegistrar creativeModeTabs){
    creativeModeTabs.register(Polychrome.MOD_ID, builder ->
      builder.title(Component.translatable(id(Polychrome.MOD_ID).toLanguageKey("itemGroup")))
        .icon(() -> PolychromeBlocks.persianColumnVolutes.get("quartz").createStack())
        .displayItems((displayParameters, output) -> {
          for(var blocks : PolychromeBlocks.blocks){
            blocks.forEachBlock(block -> output.accept(block));
          }
        })
    );
  }

}
