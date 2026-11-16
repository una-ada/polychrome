package dev.xn__z7x.polychrome;

import dev.xn__z7x.polychrome.block.ResourceProvider;
import dev.xn__z7x.polychrome.item.PolychromeItems;
import dev.xn__z7x.polychrome.sound.PolychromeSounds;
import net.blay09.mods.balm.api.Balm;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.blay09.mods.balm.core.BalmRegistrars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.xn__z7x.polychrome.block.PolychromeBlocks;

public class Polychrome{

  public static final Logger logger = LoggerFactory.getLogger(Polychrome.class);

  public static final String MOD_ID = "polychrome";

  public static ResourceLocation id(String path){
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
  }

  public static PolychromeConfig config(){
    return Balm.config().getActiveConfig(PolychromeConfig.class);
  }

  public static void initialize(BalmRegistrars registrars){
    Balm.config().registerConfig(PolychromeConfig.class);

    registrars.registrar(Registries.SOUND_EVENT, PolychromeSounds::initialize);
    registrars.blocks(PolychromeBlocks::initialize);
    registrars.items(PolychromeItems::initialize);
    registrars.creativeModeTabs(PolychromeItems::initialize);

    RegHelper.registerDynamicResourceProvider(new ResourceProvider());
  }

}
