package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.api.Materials;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import java.util.HashMap;
import java.util.Map;

public class ModBlocks{
  public static DeferredBlock
    quartzPersianVolute;
  public static Map<String, DeferredBlock> persianBracketsBull = new HashMap<>();

  public static void initialize(BalmBlockRegistrar blocks){
    quartzPersianVolute = blocks.register("quartz_persian_volute", PersianVolute::new, it -> it.strength(1.5f)).withDefaultItem().asDeferredBlock();

    final String[] persianBracketBullMaterials = Materials.list(Materials.CONCRETE, Materials.STONES);
    for(String material : persianBracketBullMaterials){
      persianBracketsBull.put(material, blocks.register(material + "_persian_bracket_bull", PersianBracket::new, it -> it).withDefaultItem().asDeferredBlock());
    }
  }
}
