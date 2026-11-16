package dev.xn__z7x.polychrome.sound;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;

public class PolychromeSounds{
  public static Holder<SoundEvent> displacement;

  public static void initialize(BalmRegistrar.Scoped<SoundEvent> sounds){
    displacement = sounds.register("music_disc_displacement", SoundEvent::createVariableRangeEvent);
  }
}
