package dev.xn__z7x.polychrome.api;

import java.util.Arrays;

public class Materials{
  public static String[]
    GOLD = {"gilded"},
    CONCRETE = {"concrete"},
    COPPER = {"copper", "exposed_copper", "weathered_copper", "oxidized_copper"},
    POLYCHROME = {"polychrome"},
    STONES = {"andesite", "stone", "tuff", "deepslate", "sandstone", "quartz"},
    WOODS = {"birch", "oak", "spruce", "dark_oak", "mangrove"};

  public static String[] list(String[]... materials){
    return Arrays.stream(materials).flatMap(Arrays::stream).toArray(String[]::new);
  }
}
