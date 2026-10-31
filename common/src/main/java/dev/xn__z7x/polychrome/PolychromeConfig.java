package dev.xn__z7x.polychrome;

import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;
import net.blay09.mods.balm.api.config.reflection.NestedType;

import java.util.List;

@Config(Polychrome.MOD_ID)
public class PolychromeConfig{

  @Comment("This is an example int property")
  public int exampleInt = 1234;

  @NestedType(String.class)
  @Comment("This is an example string list property")
  public List<String> exampleStringList = List.of("Hello", "World");
}
