package dev.xn__z7x.polychrome.item;

import dev.xn__z7x.polychrome.block.PolychromeBlocks;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import dev.xn__z7x.polychrome.Polychrome;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import static dev.xn__z7x.polychrome.Polychrome.id;

public class PolychromeItems{

  public static DeferredItem stateOfDisplacement;

  public static void initialize(BalmItemRegistrar items){
    stateOfDisplacement = items.register("music_disc_displacement", Item::new, it -> it.stacksTo(1).jukeboxPlayable(
      ResourceKey.create(Registries.JUKEBOX_SONG, Polychrome.id("music_disc_displacement"))
    )).asDeferredItem();
  }

  public static void initialize(BalmCreativeModeTabRegistrar creativeModeTabs){
    creativeModeTabs.register(Polychrome.MOD_ID, builder ->
      builder.title(Component.translatable(id(Polychrome.MOD_ID).toLanguageKey("itemGroup")))
        .icon(() -> PolychromeBlocks.persianColumnVolutes.get("quartz").createStack())
        .displayItems((displayParameters, output) -> {

          for(var blocks : PolychromeBlocks.blocks){
            blocks.forEachBlock(block -> output.accept(block));
          }

          output.accept(stateOfDisplacement);
        })
    );
  }

}
