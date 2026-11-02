package dev.xn__z7x.polychrome.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;

public class ModBlocks{

  public static DeferredBlock quartzPersianVolute;

  public static void initialize(BalmBlockRegistrar blocks){
    quartzPersianVolute = blocks.register("quartz_persian_volute", PersianVolute::new, it -> it.strength(1.5f)).withDefaultItem().asDeferredBlock();
  }

}
