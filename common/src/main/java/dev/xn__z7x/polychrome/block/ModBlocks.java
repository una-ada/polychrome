package dev.xn__z7x.polychrome.block;

import dev.xn__z7x.polychrome.api.Materials;
import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import java.util.HashMap;
import java.util.Map;

public class ModBlocks{
  public static Map<String, DeferredBlock>
    flutedColumns = new HashMap<>(),
    persianBracketsBull = new HashMap<>(),
    persianVolutes = new HashMap<>(),
    persianCapitals = new HashMap<>();

  public static void initialize(BalmBlockRegistrar blocks){
    final String[] concreteAndStones = Materials.list(Materials.CONCRETE, Materials.STONES);
    for(String material : concreteAndStones){
      flutedColumns.put(material, blocks.register(material + "_fluted_colum", Column::new, it -> it).withDefaultItem().asDeferredBlock());
      persianBracketsBull.put(material, blocks.register(material + "_persian_bracket_bull", PersianBracket::new, it -> it).withDefaultItem().asDeferredBlock());
      persianVolutes.put(material, blocks.register(material + "_persian_volute", PersianVolute::new, it -> it).withDefaultItem().asDeferredBlock());
      persianCapitals.put(material, blocks.register(material + "_persian_capital", PersianCapital::new, it -> it).withDefaultItem().asDeferredBlock());
    }
  }
}
