package dev.xn__z7x.polychrome.item;

import dev.xn__z7x.polychrome.block.ModBlocks;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import dev.xn__z7x.polychrome.Polychrome;

import static dev.xn__z7x.polychrome.Polychrome.id;

public class ModItems{

  public static void initialize(BalmItemRegistrar items){

  }

  public static void initialize(BalmCreativeModeTabRegistrar creativeModeTabs){
    creativeModeTabs.register(Polychrome.MOD_ID, builder ->
      builder.title(Component.translatable(id(Polychrome.MOD_ID).toLanguageKey("itemGroup")))
        .icon(() -> ModBlocks.persianVolutes.get("quartz").createStack())
        .displayItems((displayParameters, output) -> {
          ModBlocks.flutedColumns.forEach((material, block) -> { output.accept(block); });
          ModBlocks.persianBracketsBull.forEach((material, block) -> { output.accept(block); });
          ModBlocks.persianVolutes.forEach((material, block) -> { output.accept(block); });
          ModBlocks.persianCapitals.forEach((material, block) -> { output.accept(block); });
        })
    );
  }

}
