package dev.xn__z7x.polychrome.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;

public class Materials{
  public static String[]
    GOLD       = {"gold"},
    CONCRETE   = {"concrete"},
    COPPER     = {"copper", "exposed_copper", "weathered_copper", "oxidized_copper"},
    POLYCHROME = {"polychrome"},
    STONES     = {"andesite", "stone", "tuff", "deepslate", "sandstone", "quartz"},
    WOODS      = {"birch", "oak", "spruce", "dark_oak", "mangrove"};

  public static String[] list(String[]... materials){
    return Arrays.stream(materials).flatMap(Arrays::stream).toArray(String[]::new);
  }

  public static Block asBlock(String material){
    return switch(material){
      case "gold"             -> Blocks.GOLD_BLOCK;
      case "concrete"         -> Blocks.LIGHT_GRAY_CONCRETE;
      case "copper"           -> Blocks.COPPER_BLOCK;
      case "exposed_copper"   -> Blocks.EXPOSED_COPPER;
      case "weathered_copper" -> Blocks.WEATHERED_COPPER;
      case "oxidized_copper"  -> Blocks.OXIDIZED_COPPER;
      case "andesite"         -> Blocks.ANDESITE;
      case "stone"            -> Blocks.STONE;
      case "tuff"             -> Blocks.TUFF;
      case "deepslate"        -> Blocks.DEEPSLATE;
      case "sandstone"        -> Blocks.SANDSTONE;
      case "quartz"           -> Blocks.QUARTZ_BLOCK;
      case "birch"            -> Blocks.BIRCH_LOG;
      case "oak"              -> Blocks.OAK_LOG;
      case "spruce"           -> Blocks.SPRUCE_LOG;
      case "dark_oak"         -> Blocks.DARK_OAK_LOG;
      case "mangrove"         -> Blocks.MANGROVE_LOG;
      default                 -> Blocks.STONE;
    };
  }

  public static Item asItem(String material){
    return asBlock(material).asItem();
  }

  public static String asString(String material){
    return switch(material){
      case "gold"             -> "Gilded";
      case "concrete"         -> "Concrete";
      case "copper"           -> "Copper";
      case "exposed_copper"   -> "Exposed Copper";
      case "weathered_copper" -> "Weathered Copper";
      case "oxidized_copper"  -> "Oxidized Copper";
      case "andesite"         -> "Andesite";
      case "stone"            -> "Stone";
      case "tuff"             -> "Tuff";
      case "deepslate"        -> "Deepslate";
      case "sandstone"        -> "Sandstone";
      case "quartz"           -> "Quartz";
      case "birch"            -> "Birch";
      case "oak"              -> "Oak";
      case "spruce"           -> "Spruce";
      case "dark_oak"         -> "Dark Oak";
      case "mangrove"         -> "Mangrove";
      default                 -> "";
    };
  }
}
