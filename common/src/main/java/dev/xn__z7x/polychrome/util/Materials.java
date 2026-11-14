package dev.xn__z7x.polychrome.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.Map;

import static java.util.Map.entry;

public class Materials{
  public static String[]
    NONE       = {""},
    GOLD       = {"gold"},
    COPPER     = {"copper", "exposed_copper", "weathered_copper", "oxidized_copper"},
    POLYCHROME = {"polychrome"},
    STONES     = {"sandstone", "stone", "deepslate", "tuff", "dripstone", "blackstone", "quartz"},
    WOODS      = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "cherry", "bamboo"};

  public static Map<String, Block> blockMap = Map.ofEntries(
    entry("gold", Blocks.GOLD_BLOCK),

    entry("copper", Blocks.COPPER_BLOCK),
    entry("exposed_copper", Blocks.EXPOSED_COPPER),
    entry("weathered_copper", Blocks.WEATHERED_COPPER),
    entry("oxidized_copper", Blocks.OXIDIZED_COPPER),

    entry("polychrome", Blocks.CYAN_WOOL),

    entry("sandstone", Blocks.SANDSTONE),
    entry("stone", Blocks.STONE),
    entry("deepslate", Blocks.DEEPSLATE),
    entry("tuff", Blocks.TUFF),
    entry("dripstone", Blocks.DRIPSTONE_BLOCK),
    entry("blackstone", Blocks.BLACKSTONE),
    entry("quartz", Blocks.QUARTZ_BLOCK),

    entry("oak", Blocks.STRIPPED_OAK_LOG),
    entry("spruce", Blocks.STRIPPED_SPRUCE_LOG),
    entry("birch", Blocks.STRIPPED_BIRCH_LOG),
    entry("jungle", Blocks.STRIPPED_JUNGLE_LOG),
    entry("acacia", Blocks.STRIPPED_ACACIA_LOG),
    entry("dark_oak", Blocks.STRIPPED_DARK_OAK_LOG),
    entry("mangrove", Blocks.STRIPPED_MANGROVE_LOG),
    entry("cherry", Blocks.STRIPPED_CHERRY_LOG),
    entry("bamboo", Blocks.BAMBOO_MOSAIC)
  );

  public static String[] list(String[]... materials){
    return Arrays.stream(materials).flatMap(Arrays::stream).toArray(String[]::new);
  }

  public static String[] all(){
    return list(GOLD, COPPER, STONES, WOODS);
  }

  public static Block asBlock(String material){
    return blockMap.get(material);
  }

  public static Item asItem(String material){
    return asBlock(material).asItem();
  }

  public static String asString(String material){
    if(material == null || material.isEmpty()) return material;
    String[] words = material.split("_");
    StringBuilder result = new StringBuilder();
    for(String word : words)
      if(!word.isEmpty()) result
        .append(Character.toUpperCase(word.charAt(0)))
        .append(word.substring(1))
        .append(" ");
    return result.toString().trim();
  }

}
